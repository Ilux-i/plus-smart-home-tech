package ru.yandex.practicum.dto.warehouse;

public class NewProductInWarehouseRequest {
    private String productId;
    private Boolean fragile;
    private DimensionDto dimension;
    private Number weight;
}
