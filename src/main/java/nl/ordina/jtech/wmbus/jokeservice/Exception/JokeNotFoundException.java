package nl.ordina.jtech.wmbus.jokeservice.Exception;

public class JokeNotFoundException extends RuntimeException {
    public JokeNotFoundException(String message) {
        super(message);
    }
}
