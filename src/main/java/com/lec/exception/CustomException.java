package com.lec.exception;

import com.lec.model.vo.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {
    private ErrorCode message;
    private HttpStatus code;


    public CustomException(HttpStatus httpStatus, ErrorCode errorCode) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message.value;
    }
}
