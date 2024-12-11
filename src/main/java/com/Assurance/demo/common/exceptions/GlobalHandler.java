package com.Assurance.demo.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

public class GlobalHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> GlobalExceptionHandler(Exception ex){

        ErrorDetails errorDetails = new ErrorDetails(new Date(), "An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(errorDetails, HttpStatusCode.valueOf(501));
    }
    public static class ErrorDetails {
        private Date timestamp;
        private String message;
        private int status;

        // Constructor, getters, and setters
        public ErrorDetails(Date timestamp, String message, int status) {
            this.timestamp = timestamp;
            this.message = message;
            this.status = status;
        }

        public Date getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Date timestamp) {
            this.timestamp = timestamp;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return status;
        }
}

    }
