package exceptions;

public class ServiceException extends RuntimeException {

    static final long serialVersionUID = 1L;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

}
