package ru.yandex.practicum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.dto.order.CreateNewOrderRequest;
import ru.yandex.practicum.dto.order.OrderDto;
import ru.yandex.practicum.dto.order.ProductReturnRequest;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    /**
     * Получить заказы пользователя.
     */
    @GetMapping
    public List<OrderDto> getClientOrders(@RequestParam String username) {
        log.info("Получение заказов пользователя: {}", username);
        return null;
    }

    /**
     * Создать новый заказ в системе.
     */
    @PutMapping
    public OrderDto createNewOrder(@RequestBody CreateNewOrderRequest request) {
        log.info("Создание нового заказа для корзины: {}", request.getShoppingCart().getShoppingCartId());
        return null;
    }

    /**
     * Возврат заказа.
     */
    @PostMapping("/return")
    public OrderDto productReturn(@RequestBody ProductReturnRequest request) {
        log.info("Возврат товаров для заказа: {}", request.getOrderId());
        return null;
    }

    /**
     * Оплата заказа.
     */
    @PostMapping("/payment")
    public OrderDto payment(@RequestBody UUID orderId) {
        log.info("Обработка оплаты для заказа: {}", orderId);
        return null;
    }

    /**
     * Оплата заказа произошла с ошибкой.
     */
    @PostMapping("/payment/failed")
    public OrderDto paymentFailed(@RequestBody UUID orderId) {
        log.info("Ошибка оплаты для заказа: {}", orderId);
        return null;
    }

    /**
     * Доставка заказа.
     */
    @PostMapping("/delivery")
    public OrderDto delivery(@RequestBody UUID orderId) {
        log.info("Обработка доставки для заказа: {}", orderId);
        return null;
    }

    /**
     * Доставка заказа произошла с ошибкой.
     */
    @PostMapping("/delivery/failed")
    public OrderDto deliveryFailed(@RequestBody UUID orderId) {
        log.info("Ошибка доставки для заказа: {}", orderId);
        return null;
    }

    /**
     * Завершение заказа.
     */
    @PostMapping("/completed")
    public OrderDto complete(@RequestBody UUID orderId) {
        log.info("Завершение заказа: {}", orderId);
        return null;
    }

    /**
     * Расчёт стоимости заказа.
     */
    @PostMapping("/calculate/total")
    public OrderDto calculateTotalCost(@RequestBody UUID orderId) {
        log.info("Расчёт общей стоимости для заказа: {}", orderId);
        return null;
    }

    /**
     * Расчёт стоимости доставки заказа.
     */
    @PostMapping("/calculate/delivery")
    public OrderDto calculateDeliveryCost(@RequestBody UUID orderId) {
        log.info("Расчёт стоимости доставки для заказа: {}", orderId);
        return null;
    }

    /**
     * Сборка заказа.
     */
    @PostMapping("/assembly")
    public OrderDto assembly(@RequestBody UUID orderId) {
        log.info("Сборка заказа: {}", orderId);
        return null;
    }

    /**
     * Сборка заказа произошла с ошибкой.
     */
    @PostMapping("/assembly/failed")
    public OrderDto assemblyFailed(@RequestBody UUID orderId) {
        log.info("Ошибка сборки заказа: {}", orderId);
        return null;
    }

}