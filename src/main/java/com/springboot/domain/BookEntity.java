package com.springboot.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "book")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String bookId;

    private String name;
    private BigDecimal unitPrice;
    private String author;

    @Column(length = 1000)
    private String description;

    private String publisher;
    private String category;
    private Long unitsInStock;
    private String releaseDate;
    private String condition;
    private String fileName;
}