package ru.yandex.practicum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.practicum.dto.cart.ChangeProductQuantityRequest;
import ru.yandex.practicum.dto.cart.ShoppingCartDto;
import ru.yandex.practicum.dto.cart.entity.ShoppingCart;
import ru.yandex.practicum.repository.CartItemRepository;
import ru.yandex.practicum.repository.ShoppingCartRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemRepository cartItemRepository;

    public ShoppingCartDto getShoppingCart(String username) {
        // TODO: реализовать получение корзины
        return null;
    }

    @Transactional
    public ShoppingCartDto addProductToShoppingCart(String username, Map<UUID, Long> products) {
        // TODO: реализовать добавление товаров в корзину
        return null;
    }

    @Transactional
    public void deactivateCurrentShoppingCart(String username) {
        // TODO: реализовать деактивацию корзины
    }

    @Transactional
    public ShoppingCartDto removeFromShoppingCart(String username, List<UUID> productIds) {
        // TODO: реализовать удаление товаров из корзины
        return null;
    }

    @Transactional
    public ShoppingCartDto changeProductQuantity(String username, ChangeProductQuantityRequest request) {
        // TODO: реализовать изменение количества товара
        return null;
    }

    private ShoppingCart getOrCreateActiveCart(String username) {
        // TODO: получить активную корзину или создать новую
        return null;
    }
}