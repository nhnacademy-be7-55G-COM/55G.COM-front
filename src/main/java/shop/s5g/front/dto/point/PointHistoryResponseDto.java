package shop.s5g.front.dto.point;

import java.time.LocalDateTime;

public record PointHistoryResponseDto(
    long id,    // point history id
    String pointSource,
    long point,
    long remainingPoint,
    LocalDateTime createdAt
) {

}
