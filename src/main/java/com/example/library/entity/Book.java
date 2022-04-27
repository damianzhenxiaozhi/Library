package com.example.library.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class Book {
    private Integer bid;
    private String title;
    private String desc;
    private BigDecimal price;
    private Boolean borrow;
}
