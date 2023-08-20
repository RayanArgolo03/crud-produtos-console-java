
package exceptions;


public class ControllerException extends RuntimeException{
    
    static final long serialVersionUID = 1L;

    public ControllerException(String message) {
        super(message);
    }
    
}
