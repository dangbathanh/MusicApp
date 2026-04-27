package com.backend.MusicApp.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // Định nghĩa: [Mã HTTP], [Mã lỗi nội bộ], [Thông báo]
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, 1001, "Người dùng không tồn tại"),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, 1002, "Mật khẩu không chính xác"),
    UNAUTHENTICATED(HttpStatus.UNAUTHORIZED, 1003, "Chưa xác thực"),
    USER_EXIST(HttpStatus.BAD_REQUEST, 1005, "Email đã tồn tại"),
    UNCATEGORIZED_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, 9999, "Lỗi hệ thống chưa xác định"),
    INVALID_KEY(HttpStatus.BAD_REQUEST, 1004, "Key không hợp lệ"),
    TOKEN_REUSE_DETECTION(HttpStatus.BAD_REQUEST,1007, "Token bị sử dụng lại"),
    USER_NOT_ACTIVE(HttpStatus.BAD_REQUEST, 1008, "Người dùng chưa được kích hoạt"),
    DEVICE_MISMATCH(HttpStatus.BAD_REQUEST, 1009, "Thiết bị không khớp"),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, 1010, "Không tìm thấy tài nguyên"),
    INVALID_QUALITY_REQUEST(HttpStatus.BAD_REQUEST, 1011, "Chất lượng nhạc không hợp lệ"),
    TOKEN_EXPIRED(HttpStatus.BAD_REQUEST,1006, "Token hết hạn");

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;
}
