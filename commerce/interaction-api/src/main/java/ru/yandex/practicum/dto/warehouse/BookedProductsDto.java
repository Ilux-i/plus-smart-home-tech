package ru.yandex.practicum.dto.warehouse;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BookedProductsDto {
    private Number deliveryWeight;
    private Number deliveryVolume;
    private Boolean fragile;
}
