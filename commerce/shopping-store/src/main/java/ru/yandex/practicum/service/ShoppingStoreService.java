package ru.yandex.practicum.store.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.practicum.dto.store.PageProductDto;
import ru.yandex.practicum.dto.store.ProductDto;
import ru.yandex.practicum.dto.store.SetProductQuantityStateRequest;
import ru.yandex.practicum.repository.ProductRepository;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShoppingStoreService {

    private final ProductRepository productRepository;

    public PageProductDto getProducts(String category, Integer page, Integer size, List<String> sort) {
        // TODO: реализовать получение страницы товаров
        return null;
    }

    @Transactional
    public ProductDto createNewProduct(ProductDto productDto) {
        // TODO: реализовать создание нового товара
        return null;
    }

    @Transactional
    public ProductDto updateProduct(ProductDto productDto) {
        // TODO: реализовать обновление товара
        return null;
    }

    @Transactional
    public boolean removeProductFromStore(UUID productId) {
        // TODO: реализовать удаление товара
        return false;
    }

    @Transactional
    public boolean setProductQuantityState(SetProductQuantityStateRequest request) {
        // TODO: реализовать установку статуса количества
        return false;
    }

    public ProductDto getProduct(UUID productId) {
        // TODO: реализовать получение товара по ID
        return null;
    }

    private Sort buildSort(List<String> sortParams) {
        // TODO: построить Sort из параметров
        return Sort.unsorted();
    }
}