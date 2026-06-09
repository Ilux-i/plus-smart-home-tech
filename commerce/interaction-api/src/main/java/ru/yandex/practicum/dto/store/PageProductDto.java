package ru.yandex.practicum.dto.store;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PageProductDto {
    private Integer totalElements;
    private Integer totalPages;
    private Boolean first;
    private Boolean last;
    private Integer size;
    private ProductDto content;
    private Integer number;
    private SortObject sort;
    private Integer numberOfElements;
    private PageableObject pageable;
    private Boolean empty;
}
