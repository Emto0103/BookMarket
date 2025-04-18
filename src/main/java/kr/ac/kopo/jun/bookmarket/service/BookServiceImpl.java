package kr.ac.kopo.jun.bookmarket.service;

import kr.ac.kopo.jun.bookmarket.domain.Book;
import kr.ac.kopo.jun.bookmarket.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Override
    public List<Book> getAllBookList() {
        return bookRepository.getAllBookList();
    }

    public Book getBookById(String bookId) {
        Book bookinfo = bookRepository.getBookById(bookId);
        return bookinfo;
    }

    @Override
    public List<Book> getBookListByCategory(String category) {
        List<Book> bookByCategory = bookRepository.getBookListByCategory(category);
        return bookByCategory;
    }
}
