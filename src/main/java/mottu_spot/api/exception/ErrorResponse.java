package mottu_spot.api.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
    int status,
    String error,
    List<String> message,
    LocalDateTime timestamp
) {
    public static ErrorResponse of(int status, String error, String message) {
        return new ErrorResponse(status, error, List.of(message), LocalDateTime.now());
    }
    public static ErrorResponse of(int status, String error, List<String> messages) {
        return new ErrorResponse(status, error, messages, LocalDateTime.now());
    }
}

