package nl.serkanertas.filmspringserver.exception;

public class ActorNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ActorNotFoundException() {
        super();
    }

    public ActorNotFoundException(String message) {
        super(message);
    }
}
