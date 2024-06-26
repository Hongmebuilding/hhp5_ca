package com.lec.exception;

import com.lec.model.vo.ErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorCode message;

    public CustomException(ErrorCode message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message.value;
    }
}
