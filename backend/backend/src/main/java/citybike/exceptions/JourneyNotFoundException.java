package citybike.exceptions;
public class JourneyNotFoundException extends RuntimeException {
    public JourneyNotFoundException(String msg) {
        super(msg);
    }
}
