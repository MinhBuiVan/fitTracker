package com.example.fittracker.model.base;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pagination<T> {
    private long total;
    private int page;
    private int pageSize;
    private T result;

    public Pagination(long total, int page, int pageSize, T result) {
        this.total = total;
        this.page = page;
        this.pageSize = pageSize;
        this.result = result;
    }
}
