package edu.mum.asd.framework.validation;

import edu.mum.asd.framework.di.BaseController;
import edu.mum.asd.framework.validation.annotation.NotNullValidation;
import edu.mum.asd.framework.validation.annotation.RegexValidation;
import edu.mum.asd.framework.validation.validator.AnnotationBasedValidator;
import edu.mum.asd.framework.validation.validator.NotNullValidator;
import edu.mum.asd.framework.validation.validator.RegexValidator;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by gustavosousa on 4/17/17.
 */
public class FormValidatorImpl implements FormValidator {

    private Map<Class<?>,AnnotationBasedValidator> validators;

    public FormValidatorImpl(){
        validators = new HashMap<>();
        validators.put(RegexValidation.class, new RegexValidator());
        validators.put(NotNullValidation.class, new NotNullValidator());

    }

    @Override
    public ValidationResult validate(BaseController controller) {

        Field[] fields = controller.getClass().getDeclaredFields();

        ValidationResult validationResult = new ValidationResult();
        Stream.of(fields).forEach(
                field -> {
                    if(TextInputControl.class.isAssignableFrom(field.getType())){
                        validateField(controller, validationResult, field);
                    }
                }
        );
        return validationResult;
    }

    private void validateField(BaseController controller, ValidationResult validationResult, Field field) {
        Annotation[] annotations = field.getAnnotations();
        if(annotations != null){
            Stream.of(annotations).forEach(
                    annotation -> {
                        /**
                         * Strategy Pattern (validator)
                         */
                        if(validators.containsKey(annotation.annotationType())){
                            AnnotationBasedValidator validator = validators.get(annotation.annotationType());
                            field.setAccessible(true);
                            try {
                                TextInputControl textInputControl = (TextInputControl) field.get(controller);
                                Optional<ValidationError> error = validator.validate(textInputControl, annotation);
                                if(error.isPresent()){
                                    validationResult.addError(error.get());
                                }
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }

                        }
                    }
            );
        }
    }
}
