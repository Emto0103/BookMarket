package kr.ac.kopo.jun.bookmarket.service;

import kr.ac.kopo.jun.bookmarket.domain.Book;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BookService {
    List<Book> getAllBookList();
    Book getBookById(String bookId);
    List<Book> getBookListByCategory(String category);
    Set<Book> getBookListByFilter(Map<String, List<String>> Filter);
    void setNewBook(Book book);
}
