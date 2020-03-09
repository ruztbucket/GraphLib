package exception;

public class DuplicateVertexException extends Exception {

    public DuplicateVertexException() {
    }

    public DuplicateVertexException(String message) {
        super(message);
    }

    public DuplicateVertexException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateVertexException(Throwable cause) {
        super(cause);
    }

    public DuplicateVertexException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
