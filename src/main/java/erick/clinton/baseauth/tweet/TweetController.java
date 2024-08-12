package erick.clinton.baseauth.tweet;

import erick.clinton.baseauth.tweet.dto.CreateTweetDto;
import erick.clinton.baseauth.tweet.dto.FeedDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/tweet")
public class TweetController {

    private final TweetService tweetService;

    public TweetController(TweetService tweetService){
        this.tweetService = tweetService;
    }


    @GetMapping("/feed")
    public ResponseEntity<FeedDto> feed(@RequestParam(value = "page",defaultValue = "0") int page,
                                              @RequestParam(value = "pageSize",defaultValue = "10") int pageSize ){
        return this.tweetService.feed(page,pageSize);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody CreateTweetDto createTweetDto, JwtAuthenticationToken jwtAuthenticationToken){
        return this.tweetService.create(createTweetDto,jwtAuthenticationToken);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id, JwtAuthenticationToken jwtAuthenticationToken){
        return this.tweetService.delete(id, jwtAuthenticationToken);
    }
}
