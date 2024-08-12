package erick.clinton.baseauth.tweet.dto;

import java.util.List;

public record FeedDto(List<FeedItemDto> feedItemDto, int page, int pageSize, int totalPages, Long totalElements) {
}
