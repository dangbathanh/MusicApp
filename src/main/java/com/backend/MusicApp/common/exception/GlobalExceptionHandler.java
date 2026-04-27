package com.backend.MusicApp.common.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler  {
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse> handleAppException(AppException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        ErrorResponse error = new ErrorResponse(
                errorCode.getHttpStatus().value(),
                errorCode.getCode(),
                errorCode.getMessage()
        );

        return ResponseEntity.status(errorCode.getHttpStatus()).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult().getFieldError().getDefaultMessage();
        return ResponseEntity.badRequest().body(new ErrorResponse(400, 400, msg));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {
        String msg = ex.getConstraintViolations().iterator().next().getMessage();

        return ResponseEntity.badRequest().body(new ErrorResponse(400, 400, msg));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String msg = String.format("Tham số '%s' không đúng định dạng", ex.getName());

        return ResponseEntity.badRequest().body(new ErrorResponse(400, 400, msg));
    }

    // === Spring Security exceptions từ AuthenticationManager (login flow) ===

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUsernameNotFound(UsernameNotFoundException ex) {
        return toResponse(ErrorCode.USER_NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentials(BadCredentialsException ex) {
        return toResponse(ErrorCode.INVALID_PASSWORD);
    }

    @ExceptionHandler({DisabledException.class, LockedException.class})
    public ResponseEntity<ErrorResponse> handleDisabledOrLocked(Exception ex) {
        return toResponse(ErrorCode.USER_NOT_ACTIVE);
    }

    private ResponseEntity<ErrorResponse> toResponse(ErrorCode code) {
        ErrorResponse body = new ErrorResponse(
                code.getHttpStatus().value(), code.getCode(), code.getMessage()
        );
        return ResponseEntity.status(code.getHttpStatus()).body(body);
    }
}
