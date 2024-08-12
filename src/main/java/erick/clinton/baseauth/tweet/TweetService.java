package erick.clinton.baseauth.tweet;

import erick.clinton.baseauth.tweet.dto.CreateTweetDto;
import erick.clinton.baseauth.tweet.entity.TweetEntity;
import erick.clinton.baseauth.tweet.repository.TweetRepository;
import erick.clinton.baseauth.user.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TweetService {

    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    public TweetService(TweetRepository tweetRepository,UserRepository userRepository){
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
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
}
