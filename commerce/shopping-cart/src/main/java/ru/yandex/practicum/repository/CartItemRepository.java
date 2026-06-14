package ru.yandex.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.dto.cart.entity.CartItem;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByShoppingCart_ShoppingCartId(UUID shoppingCartId);

    Optional<CartItem> findByShoppingCart_ShoppingCartIdAndProductId(UUID shoppingCartId, UUID productId);

    @Modifying
    void deleteByShoppingCart_ShoppingCartIdAndProductIdIn(UUID shoppingCartId, List<UUID> productIds);

    @Modifying
    void deleteByShoppingCart_ShoppingCartId(UUID shoppingCartId);

    boolean existsByShoppingCart_ShoppingCartIdAndProductId(UUID shoppingCartId, UUID productId);
}
