package ru.yandex.practicum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.dto.order.OrderDto;
import ru.yandex.practicum.dto.payment.PaymentDto;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payment")
public class PaymentController {

    /**
     * Формирование оплаты для заказа (переход в платежный шлюз).
     */
    @PostMapping
    public PaymentDto payment(@RequestBody OrderDto orderDto) {
        log.info("Формирование оплаты для заказа: {}", orderDto.getOrderId());
        return null;
    }

    /**
     * Расчёт полной стоимости заказа.
     */
    @PostMapping("/totalCost")
    public Double getTotalCost(@RequestBody OrderDto orderDto) {
        log.info("Расчёт полной стоимости заказа: {}", orderDto.getOrderId());
        return null;
    }

    /**
     * Метод для эмуляции успешной оплаты в платежного шлюза.
     */
    @PostMapping("/refund")
    public void paymentSuccess(@RequestBody UUID paymentId) {
        log.info("Успешная оплата для платежа: {}", paymentId);
    }

    /**
     * Расчёт стоимости товаров в заказе.
     */
    @PostMapping("/productCost")
    public Double productCost(@RequestBody OrderDto orderDto) {
        log.info("Расчёт стоимости товаров для заказа: {}", orderDto.getOrderId());
        return null;
    }

    /**
     * Метод для эмуляции отказа в оплате платежного шлюза.
     */
    @PostMapping("/failed")
    public void paymentFailed(@RequestBody UUID paymentId) {
        log.info("Ошибка оплаты для платежа: {}", paymentId);
    }

}