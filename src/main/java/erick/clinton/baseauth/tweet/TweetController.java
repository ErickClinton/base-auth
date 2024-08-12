package erick.clinton.baseauth.tweet;

import erick.clinton.baseauth.tweet.dto.CreateTweetDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tweet")
public class TweetController {

    private final TweetService tweetService;

    public TweetController(TweetService tweetService){
        this.tweetService = tweetService;
    }

    @PostMapping("/tweets")
    public ResponseEntity<Void> create(@RequestBody CreateTweetDto createTweetDto, JwtAuthenticationToken jwtAuthenticationToken){
        return this.tweetService.create(createTweetDto,jwtAuthenticationToken);
    }
}
