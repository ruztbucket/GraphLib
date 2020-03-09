package exception;

public class MissingVertexException extends Exception {

    public MissingVertexException() {
    }

    public MissingVertexException(String message) {
        super(message);
    }

    public MissingVertexException(String message, Throwable cause) {
        super(message, cause);
    }

    public MissingVertexException(Throwable cause) {
        super(cause);
    }

    public MissingVertexException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
