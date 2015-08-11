package co.gapx.lugaresvp.exceptions;

/**
 *
 * @author Felix Ernesto Orduz Grimaldo<felix.orduz@gmail.com>
 */
public class DataAccessLayerException extends RuntimeException {

    /**
     *
     */
    public DataAccessLayerException() {
    }

    /**
     *
     * @param message
     */
    public DataAccessLayerException(String message) {
        super(message);
    }

    /**
     *
     * @param cause
     */
    public DataAccessLayerException(Throwable cause) {
        super(cause);
    }

    /**
     *
     * @param message
     * @param cause
     */
    public DataAccessLayerException(String message, Throwable cause) {
        super(message, cause);
    }
}