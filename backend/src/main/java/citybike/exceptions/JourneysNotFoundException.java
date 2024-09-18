package citybike.exceptions;
public class JourneysNotFoundException extends RuntimeException {
    public JourneysNotFoundException(String msg) {
        super(msg);
    }
}
