package strongerme.exception;

public class ApiErrorResponse {
    private String error;
    private int status;

    public ApiErrorResponse(String error, int status) {
        this.error = error;
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public int getStatus() {
        return status;
    }
}
