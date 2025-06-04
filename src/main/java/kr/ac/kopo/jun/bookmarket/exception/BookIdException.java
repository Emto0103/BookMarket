package kr.ac.kopo.jun.bookmarket.exception;

public class BookIdException extends RuntimeException {
    private String bookId;
    public BookIdException(String bookId) {
        super();
        this.bookId = bookId;
    }

    public String getBookId() {
        return bookId;
    }
}
