package ru.yandex.practicum.dto.store;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PageableObject {
    private Integer offset;
    private SortObject sort;
    private Boolean unpaged;
    private Boolean paged;
    private Integer pageNumber;
    private Integer pageSize;
}
