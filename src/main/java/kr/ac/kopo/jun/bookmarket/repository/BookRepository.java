package kr.ac.kopo.jun.bookmarket.repository;

import kr.ac.kopo.jun.bookmarket.domain.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getAllBookList();
    Book getBookById(String bookId);
}
