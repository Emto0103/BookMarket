package com.springboot.repository;

import com.springboot.domain.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookJpaRepository extends JpaRepository<BookEntity, Long> {
    BookEntity findByBookId(String bookId);
    List<BookEntity> findByCategory(String category);
}