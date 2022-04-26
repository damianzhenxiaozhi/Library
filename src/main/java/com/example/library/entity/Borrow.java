package com.example.library.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Borrow {
    private Integer id;
    private Integer bookId;
    private String bookName;
    private LocalDateTime borrowTime;
    private Integer studentId;
    private String studentName;
}
