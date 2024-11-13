package shop.s5g.front.dto.point;

import java.math.BigDecimal;

public record PointPolicyResponseDto(
    long id,
    String name,
    String sourceName,
    BigDecimal value
) {

}
