package nl.serkanertas.filmspringserver.exception;

public class MediaNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MediaNotFoundException() {
        super();
    }

    public MediaNotFoundException(String message) {
        super(message);
    }
}
