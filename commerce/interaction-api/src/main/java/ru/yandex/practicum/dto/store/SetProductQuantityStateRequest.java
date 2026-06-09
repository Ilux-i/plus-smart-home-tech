package ru.yandex.practicum.dto.store;

import lombok.Builder;
import lombok.Getter;
import ru.yandex.practicum.state.QuantityState;

@Getter
@Builder
public class SetProductQuantityStateRequest {
    private String productId;
    private QuantityState quantityState;
}
