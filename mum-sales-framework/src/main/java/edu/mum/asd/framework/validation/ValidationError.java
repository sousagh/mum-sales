package edu.mum.asd.framework.validation;

/**
 * Created by gustavosousa on 4/17/17.
 */
public class ValidationError {
    private final String message;

    public ValidationError(String s) {
        this.message = s;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "message='" + message + '\'';
    }
}
