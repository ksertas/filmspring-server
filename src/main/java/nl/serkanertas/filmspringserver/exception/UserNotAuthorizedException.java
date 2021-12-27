package nl.serkanertas.filmspringserver.exception;

public class UserNotAuthorizedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UserNotAuthorizedException() {
        super();
    }

    public UserNotAuthorizedException(String message) {
        super(message);
    }
}
