package local.vng.module03.validator.exception;

/**
 * WrongLoginException
 * <p>
 *
 * @author VoylenkoNG
 * 18.03.2023
 */
public class WrongLoginException extends Exception {

    public WrongLoginException() {
    }

    public WrongLoginException(String message) {
        super(message);
    }
}
