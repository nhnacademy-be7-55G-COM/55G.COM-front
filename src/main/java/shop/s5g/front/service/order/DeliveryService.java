package shop.s5g.front.service.order;

import shop.s5g.front.dto.delivery.DeliveryResponseDto;
import shop.s5g.front.dto.delivery.DeliveryUpdateRequestDto;

public interface DeliveryService {

    DeliveryResponseDto adminUpdateDelivery(DeliveryUpdateRequestDto update);

    DeliveryResponseDto userUpdateDelivery(DeliveryUpdateRequestDto update);
}
