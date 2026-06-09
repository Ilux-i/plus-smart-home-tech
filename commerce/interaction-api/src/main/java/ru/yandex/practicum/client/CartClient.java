package ru.yandex.practicum.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.dto.cart.ChangeProductQuantityRequest;
import ru.yandex.practicum.dto.cart.ShoppingCartDto;

import java.util.List;

@FeignClient(name = "shopping-cart")
@RequestMapping("api/v1/shopping-cart")
public interface CartClient {

    @GetMapping
    ShoppingCartDto getActualCart(
            @RequestParam String username
    );

    @PutMapping
    ShoppingCartDto addProductFromCart(
            @RequestParam String username,
            @RequestBody List<Integer> productIds
            );

    @DeleteMapping
    void removeCart(
            @RequestParam String username
    );

    @PostMapping("/remove")
    List<Integer> clearProductFromCart(
            @RequestParam String username
    );

    @PostMapping("/change-quantity")
    ShoppingCartDto changeQuantityProductFromCart(
            @RequestParam String username,
            @RequestBody ChangeProductQuantityRequest dto
    );
}
