package ru.yandex.practicum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.practicum.dto.cart.ShoppingCartDto;
import ru.yandex.practicum.dto.warehouse.*;
import ru.yandex.practicum.dto.warehouse.entity.WarehouseProduct;
import ru.yandex.practicum.repository.WarehouseAddressRepository;
import ru.yandex.practicum.repository.WarehouseProductRepository;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WarehouseService {

    private final WarehouseProductRepository warehouseProductRepository;
    private final WarehouseAddressRepository warehouseAddressRepository;

    @Transactional
    public void newProductInWarehouse(NewProductInWarehouseRequest request) {
        // TODO: реализовать добавление нового товара на склад
    }

    public BookedProductsDto checkProductQuantityEnoughForShoppingCart(ShoppingCartDto shoppingCart) {
        // TODO: проверить наличие товаров и вернуть зарезервированные данные
        return null;
    }

    @Transactional
    public void addProductToWarehouse(AddProductToWarehouseRequest request) {
        // TODO: реализовать прием товара на склад
    }

    public AddressDto getWarehouseAddress() {
        // TODO: вернуть адрес склада
        return null;
    }

    private double calculateTotalVolume(WarehouseProduct product, Long quantity) {
        // TODO: рассчитать объем товара
        return 0.0;
    }
}