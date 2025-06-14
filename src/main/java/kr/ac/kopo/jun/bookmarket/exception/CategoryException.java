package kr.ac.kopo.jun.bookmarket.exception;
//카테고리를 찾을 수 없을 때 처리하기 위한 사용자 정의 예외클래스

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@Data
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CategoryException extends RuntimeException {
    private String category;
    private String errormessage;
    public CategoryException(String category) {
        super();
        this.category = category;
        errormessage = "요청한 도서 분야를 찾을 수 없습니다.";
    }
}
