package local.vng.module03.validator.exception;

/**
 * WrongPasswordException
 * <p>
 *
 * @author VoylenkoNG
 * 18.03.2023
 */
public class WrongPasswordException extends Exception {

    public WrongPasswordException() {
    }

    public WrongPasswordException(String message) {
        super(message);
    }
}
