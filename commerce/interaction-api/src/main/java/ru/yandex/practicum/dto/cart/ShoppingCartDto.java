package ru.yandex.practicum.dto.cart;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ShoppingCartDto {
    private String shoppingCartId;
    private List<Integer> products;
}
