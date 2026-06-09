package ru.yandex.practicum.dto.store;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SortObject {
    private String direction;
    private String nullHandling;
    private Boolean ascending;
    private String property;
    private Boolean ignoreCase;
}
