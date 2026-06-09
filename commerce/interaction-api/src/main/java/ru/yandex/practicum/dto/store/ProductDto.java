package ru.yandex.practicum.dto.store;

import lombok.Builder;
import lombok.Getter;
import ru.yandex.practicum.state.ProductCategory;
import ru.yandex.practicum.state.ProductState;
import ru.yandex.practicum.state.QuantityState;

@Builder
@Getter
public class ProductDto {
    private String productId;
    private String productName;
    private String description;
    private String imageSrc;
    private QuantityState quantityState;
    private ProductState productState;
    private ProductCategory productCategory;
    private Number price;
}
