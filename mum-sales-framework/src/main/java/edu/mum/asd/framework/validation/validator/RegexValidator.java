package edu.mum.asd.framework.validation.validator;

import edu.mum.asd.framework.validation.ValidationError;
import edu.mum.asd.framework.validation.annotation.RegexValidation;
import javafx.scene.control.TextInputControl;

import java.lang.annotation.Annotation;
import java.util.Optional;

/**
 * Created by gustavosousa on 4/17/17.
 */
public class RegexValidator implements AnnotationBasedValidator {
    @Override
    public Optional<ValidationError> validate(TextInputControl textInputControl, Annotation annotation) {

        RegexValidation regexValidation = (RegexValidation) annotation;

        String matches = regexValidation.matches();

        String text = textInputControl.getText();

        if(text.matches(matches)){
            return Optional.empty();
        }

        ValidationError error = new ValidationError("Validation error: regex " + matches + " doesn't match " + text);
        return Optional.of(error);
    }
}
