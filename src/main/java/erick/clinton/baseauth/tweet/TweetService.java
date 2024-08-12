package erick.clinton.baseauth.tweet;

import erick.clinton.baseauth.role.entity.RoleEntity;
import erick.clinton.baseauth.tweet.dto.CreateTweetDto;
import erick.clinton.baseauth.tweet.dto.FeedDto;
import erick.clinton.baseauth.tweet.dto.FeedItemDto;
import erick.clinton.baseauth.tweet.entity.TweetEntity;
import erick.clinton.baseauth.tweet.repository.TweetRepository;
import erick.clinton.baseauth.user.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class TweetService {

    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    public TweetService(TweetRepository tweetRepository,UserRepository userRepository){
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<FeedDto> feed( int page, int pageSize ){
        var tweets = tweetRepository.findAll(PageRequest.of(page,pageSize, Sort.Direction.DESC,"createAtDate"))
                .map(tweet-> new FeedItemDto(tweet.getId(),tweet.getContent(),tweet.getUser().getUsername()));

        return ResponseEntity.ok(new FeedDto(tweets.getContent(), page,pageSize,tweets.getTotalPages(),tweets.getTotalElements()));
    }
    public ResponseEntity<Void> create(CreateTweetDto createTweetDto, JwtAuthenticationToken jwtAuthenticationToken){
        userRepository.findById(UUID.fromString(jwtAuthenticationToken.getName()))
                .ifPresent(user->{
                    var tweet = new TweetEntity();
                    tweet.setUser(user);
                    tweet.setContent(createTweetDto.content());
                    tweetRepository.save(tweet);
                });

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> delete(Long id, JwtAuthenticationToken jwtAuthenticationToken){

        var tweet = tweetRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        var user = userRepository.findById(UUID.fromString(jwtAuthenticationToken.getName()));
        var isAdmin = user.get().getRoles()
                .stream()
                .anyMatch(roleEntity -> roleEntity.getName().equalsIgnoreCase(RoleEntity.ValuesEnum.ADMIN.name()));
        if(isAdmin || tweet.getUser().getId().equals(UUID.fromString(jwtAuthenticationToken.getName()))){
            tweetRepository.deleteById(id);
        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok().build();
    }
}
