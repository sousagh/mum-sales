package edu.mum.asd.framework.validation.validator;

import edu.mum.asd.framework.validation.ValidationError;
import edu.mum.asd.framework.validation.annotation.NotNullValidation;
import javafx.scene.control.TextInputControl;

import java.lang.annotation.Annotation;
import java.util.Optional;

/**
 * Created by gustavosousa on 4/17/17.
 */
public class NotNullValidator implements AnnotationBasedValidator {
    @Override
    public Optional<ValidationError> validate(TextInputControl textInputControl, Annotation annotation) {
        String text = textInputControl.getText();

        if(text == null || text.isEmpty()){
            NotNullValidation notNullValidation = (NotNullValidation) annotation;
            ValidationError error = new ValidationError(notNullValidation.message());
            return Optional.of(error);
        }
        return Optional.empty();
    }
}
