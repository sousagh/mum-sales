package edu.mum.asd.framework.validation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavosousa on 4/17/17.
 */
public class ValidationResult {
    private List<ValidationError> errors = new ArrayList<>();

    public void addError(ValidationError validationError) {
        errors.add(validationError);
    }

    public boolean valid(){
        return errors.isEmpty();
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return "ValidationResult{" +
                "errors=" + errors +
                '}';
    }
}
