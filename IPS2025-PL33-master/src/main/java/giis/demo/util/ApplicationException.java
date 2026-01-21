package giis.demo.util;

/**
 * Excepción producida por la aplicación ante situaciones controladas
 * (validaciones, prerequisitos no cumplidos, etc.) de las que se puede recuperar.
 */
@SuppressWarnings("serial")
public class ApplicationException extends RuntimeException {

    public ApplicationException(Throwable e) {
        super(e);
    }

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
