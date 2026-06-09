package ru.yandex.practicum.dto.cart;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ChangeProductQuantityRequest {
    private String productId;
    private Integer newQuantity;
}
