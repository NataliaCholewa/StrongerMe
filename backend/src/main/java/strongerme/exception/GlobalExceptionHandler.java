package strongerme.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(ApiException ex) {
        return new ResponseEntity<>(new ApiErrorResponse(ex.getMessage(), 404), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiErrorResponse> handleBadRequest(BadRequestException ex) {
        return new ResponseEntity<>(new ApiErrorResponse(ex.getMessage(), 400), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiErrorResponse> handleUnauthorized(UnauthorizedException ex) {
        return new ResponseEntity<>(new ApiErrorResponse(ex.getMessage(), 401), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ApiErrorResponse> handleForbidden(ForbiddenException ex) {
        return new ResponseEntity<>(new ApiErrorResponse(ex.getMessage(), 403), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneralError(Exception ex) {
        return new ResponseEntity<>(new ApiErrorResponse("Internal server error", 500), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
