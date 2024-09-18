package citybike.exceptions;
public class StationsNotFoundException extends RuntimeException {
    public StationsNotFoundException(String msg) {
        super(msg);
    }
}
