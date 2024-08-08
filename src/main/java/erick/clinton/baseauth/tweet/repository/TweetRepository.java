package erick.clinton.baseauth.tweet.repository;

import erick.clinton.baseauth.tweet.entity.TweetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends JpaRepository<TweetEntity, Long> {
}
