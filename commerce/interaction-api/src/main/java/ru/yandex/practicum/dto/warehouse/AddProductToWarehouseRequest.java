package ru.yandex.practicum.dto.warehouse;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddProductToWarehouseRequest {
    private String productId;
    private Integer quantity;
}
