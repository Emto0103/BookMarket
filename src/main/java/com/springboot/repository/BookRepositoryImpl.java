package com.springboot.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.domain.Book;
import com.springboot.domain.BookEntity;
import com.springboot.exception.BookIdException;

@Repository
public class BookRepositoryImpl implements BookRepository {

	@Autowired
	private BookJpaRepository bookJpaRepository;

	private List<Book> listOfBooks = new ArrayList<Book>();

	public BookRepositoryImpl() {
		// 초기 샘플 데이터
		Book book1 = new Book();
		book1.setBookId("isbn0001");
		book1.setName("자바스크립트 입문");
		book1.setUnitPrice(new BigDecimal(30000));
		book1.setAuthor("조현영");
		book1.setDescription(
				"자바스크립트의 기초부터 심화까지 핵심 문법을 학습한 후 12가지 프로그램을 만들며 학습한 내용을 확인할 수 있습니다.");
		book1.setPublisher("길벗");
		book1.setCategory("IT전문서");
		book1.setUnitsInStock(1000);
		book1.setReleaseDate("2024/02/20");
		book1.setFileName("isbn0001.jpg");

		Book book2 = new Book();
		book2.setBookId("isbn0002");
		book2.setName("파이썬의 정석");
		book2.setUnitPrice(new BigDecimal(29800));
		book2.setAuthor("조용주,임좌상");
		book2.setDescription(
				"4차 산업혁명의 핵심인 머신러닝, 사물 인터넷(IoT), 데이터 분석 등 다양한 분야에 활용되는 파이썬 프로그래밍 언어를 학습할 수 있습니다.");
		book2.setPublisher("길벗");
		book2.setCategory("IT교육교재");
		book2.setUnitsInStock(1000);
		book2.setReleaseDate("2023/01/10");
		book2.setFileName("isbn0002.jpg");

		Book book3 = new Book();
		book3.setBookId("isbn0003");
		book3.setName("안드로이드 프로그래밍");
		book3.setUnitPrice(new BigDecimal(25000));
		book3.setAuthor("송미영");
		book3.setDescription(
				"안드로이드의 기본 개념을 체계적으로 익히고, 이를 실습 예제를 통해 익힙니다.");
		book3.setPublisher("길벗");
		book3.setCategory("IT교육교재");
		book3.setUnitsInStock(1000);
		book3.setReleaseDate("2023/06/30");
		book3.setFileName("isbn0003.jpg");

		listOfBooks.add(book1);
		listOfBooks.add(book2);
		listOfBooks.add(book3);
	}

	public List<Book> getAllBookList() {
		// DB에서 모든 책 가져오기
		List<BookEntity> entities = bookJpaRepository.findAll();
		List<Book> dbBooks = entities.stream()
				.map(this::entityToBook)
				.collect(Collectors.toList());

		// 메모리의 책과 합치기
		List<Book> allBooks = new ArrayList<>(listOfBooks);
		allBooks.addAll(dbBooks);

		return allBooks;
	}

	public List<Book> getBookListByCategory(String category) {
		List<Book> booksByCategory = new ArrayList<Book>();

		// 메모리 검색
		for(Book book : listOfBooks) {
			if(category.equalsIgnoreCase(book.getCategory()))
				booksByCategory.add(book);
		}

		// DB 검색
		List<BookEntity> entities = bookJpaRepository.findByCategory(category);
		booksByCategory.addAll(entities.stream()
				.map(this::entityToBook)
				.collect(Collectors.toList()));

		return booksByCategory;
	}

	public Set<Book> getBookListByFilter(Map<String, List<String>> filter) {
		Set<Book> booksByPublisher = new HashSet<Book>();
		Set<Book> booksByCategory = new HashSet<Book>();

		Set<String> booksByFilter = filter.keySet();

		if (booksByFilter.contains("publisher")) {
			for (int j = 0; j < filter.get("publisher").size(); j++) {
				String publisherName = filter.get("publisher").get(j);
				for (Book book : getAllBookList()) {
					if (publisherName.equalsIgnoreCase(book.getPublisher()))
						booksByPublisher.add(book);
				}
			}
		}

		if (booksByFilter.contains("category")) {
			for (int i = 0; i < filter.get("category").size(); i++) {
				String category = filter.get("category").get(i);
				List<Book> list = getBookListByCategory(category);
				booksByCategory.addAll(list);
			}
		}

		booksByCategory.retainAll(booksByPublisher);
		return booksByCategory;
	}

	public Book getBookById(String bookId) {
		Book bookInfo = null;

		// 메모리 검색
		for(Book book : listOfBooks) {
			if (book != null && book.getBookId() != null &&
					book.getBookId().equals(bookId)) {
				bookInfo = book;
				break;
			}
		}

		// DB 검색
		if (bookInfo == null) {
			BookEntity entity = bookJpaRepository.findByBookId(bookId);
			if (entity != null) {
				bookInfo = entityToBook(entity);
			}
		}

		if(bookInfo == null)
			throw new BookIdException(bookId);

		return bookInfo;
	}

	public void setNewBook(Book book) {
		// DB에 저장
		BookEntity entity = bookToEntity(book);
		bookJpaRepository.save(entity);

		// 메모리에도 추가
		listOfBooks.add(book);
	}

	private Book entityToBook(BookEntity entity) {
		Book book = new Book();
		book.setBookId(entity.getBookId());
		book.setName(entity.getName());
		book.setUnitPrice(entity.getUnitPrice());
		book.setAuthor(entity.getAuthor());
		book.setDescription(entity.getDescription());
		book.setPublisher(entity.getPublisher());
		book.setCategory(entity.getCategory());
		book.setUnitsInStock(entity.getUnitsInStock());
		book.setReleaseDate(entity.getReleaseDate());
		book.setCondition(entity.getCondition());
		book.setFileName(entity.getFileName());
		return book;
	}

	private BookEntity bookToEntity(Book book) {
		BookEntity entity = new BookEntity();
		entity.setBookId(book.getBookId());
		entity.setName(book.getName());
		entity.setUnitPrice(book.getUnitPrice());
		entity.setAuthor(book.getAuthor());
		entity.setDescription(book.getDescription());
		entity.setPublisher(book.getPublisher());
		entity.setCategory(book.getCategory());
		entity.setUnitsInStock(book.getUnitsInStock());
		entity.setReleaseDate(book.getReleaseDate());
		entity.setCondition(book.getCondition());
		entity.setFileName(book.getFileName());
		return entity;
	}
}