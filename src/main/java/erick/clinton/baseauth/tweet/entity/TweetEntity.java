package erick.clinton.baseauth.tweet.entity;

import erick.clinton.baseauth.user.entity.UserEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tweet")
public class TweetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String content;

    private UserEntity user;

    @CreationTimestamp
    private Instant createAtDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Instant getCreateAtDate() {
        return createAtDate;
    }

    public void setCreateAtDate(Instant createAtDate) {
        this.createAtDate = createAtDate;
    }
}
