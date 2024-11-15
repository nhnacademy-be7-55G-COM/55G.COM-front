package shop.s5g.front.dto.coupon;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BookDetailsBookResponseDto(
    long bookId,
    String publisherName,
    String typeName,
    String title,
    String chapter,
    String description,
    LocalDateTime publishedDate,
    String isbn,
    long price,
    BigDecimal discountRate,
    boolean isPacked,
    int stock,
    long views,
    LocalDateTime createdAt
) {
}
