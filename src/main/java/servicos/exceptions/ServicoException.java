package servicos.exceptions;

public class ServicoException extends RuntimeException {

    static final long serialVersionUID = 1L;

    public ServicoException() {
        super();
    }

    public ServicoException(String message) {
        super(message);
    }

}
