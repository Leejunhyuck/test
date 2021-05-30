package com.raccoon.blog.global.exception;

import java.util.List;

public class ErrorResponse {
    private int code;
    private String message;
    private List<ErrorDetail> details;

    ErrorResponse(int code, String message) {
        this.code = code;
        this.message = message;

    }

    ErrorResponse(int code, String message, List<ErrorDetail> details) {
        this.code = code;
        this.message = message;
        this.details= details;

    }

    class ErrorDetail {
        private String detailMessage;
    }

    // public ErrorResponse response(ErrorCode code, List<ErrorDetail> details) {
    //     ErrorResponse errorResponse = new ErrorResponse(code.getCode(), code.getMessage());

    //     if (details.size() != 0)
    //         errorResponse.details = details;

    //     return errorResponse;

    // }

}