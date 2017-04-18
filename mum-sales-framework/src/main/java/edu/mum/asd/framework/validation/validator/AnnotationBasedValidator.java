package edu.mum.asd.framework.validation.validator;

import edu.mum.asd.framework.validation.ValidationError;
import javafx.scene.control.TextInputControl;

import java.lang.annotation.Annotation;
import java.util.Optional;

/**
 * Created by gustavosousa on 4/17/17.
 */
public interface AnnotationBasedValidator {

    Optional<ValidationError> validate(TextInputControl textInputControl, Annotation annotation);
}
