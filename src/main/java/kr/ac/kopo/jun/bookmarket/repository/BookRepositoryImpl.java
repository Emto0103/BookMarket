package kr.ac.kopo.jun.bookmarket.repository;

import kr.ac.kopo.jun.bookmarket.domain.Book;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private List<Book> listOfBooks = new ArrayList<Book>();

    public BookRepositoryImpl(){
        Book book1 = new Book();
        book1.setBookId("isbn0001");
        book1.setName("스프링부트완전정복");
        book1.setUnitprice(BigDecimal.valueOf(35000));
        book1.setAuthor("송미영");
        book1.setDescription("스프링 부트는 스프링을 기반으로 쉽고 빠르게 웹 애플리케이션을 개발할 수 있는 도구이다.");
        book1.setPublisher("길벗캠퍼스");
        book1.setCategory("IT교재");
        book1.setUnitInStock(1000);
        book1.setReleaseDate("2024/12/31");
        book1.setCondition("신규도서");
        Book book2 = new Book();
        book2.setBookId("isbn0002");
        book2.setName("자바완전정복");
        book2.setUnitprice(BigDecimal.valueOf(32000));
        book2.setAuthor("김미영");
        book2.setDescription("자바 완전 정복은 자바를 기반으로 쉽고 빠르게 배울 수 있는 책이다.");
        book2.setPublisher("자바나라");
        book2.setCategory("IT교재");
        book2.setUnitInStock(2000);
        book2.setReleaseDate("2023/9/11");
        book2.setCondition("신규도서");
        Book book3 = new Book();
        book3.setBookId("isbn0003");
        book3.setName("파이썬분석");
        book3.setUnitprice(BigDecimal.valueOf(28000));
        book3.setAuthor("윤미영");
        book3.setDescription("파이썬 분석은 파이썬을 알기 쉽게 분석한 책이다.");
        book3.setPublisher("한울");
        book3.setCategory("IT교재");
        book3.setUnitInStock(1500);
        book3.setReleaseDate("2024/9/16");
        book3.setCondition("신규도서");

        listOfBooks.add(book1);
        listOfBooks.add(book2);
        listOfBooks.add(book3);
    }

    @Override
    public List<Book> getAllBookList() {
        return listOfBooks;
    }

    @Override
    public Book getBookById(String bookId) {
        Book bookInfo = null;
        for (Book book : listOfBooks) {
            if (book != null && book.getBookId()!=null && book.getBookId().equals(bookId)) {
                bookInfo = book;
                break;
            }
        }

        if (bookInfo == null) {
            throw new IllegalArgumentException("도서번호가 "+ bookId+"인 해당 도서를 찾을 수 없습니다.");
        }
        return bookInfo;
    }
}
