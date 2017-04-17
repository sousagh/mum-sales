package edu.mum.asd.framework.validation;

import edu.mum.asd.framework.di.BaseController;

import java.lang.reflect.Field;
import java.util.stream.Stream;

/**
 * Created by gustavosousa on 4/17/17.
 */
public class FormValidatorImpl implements FormValidator {

    private final BaseController controller;

    public FormValidatorImpl(BaseController controller) {

        this.controller = controller;
    }

    @Override
    public ValidationResult validate() {

        Field[] fields = controller.getClass().getFields();

        ValidationResult validationResult = new ValidationResult();
        Stream.of(fields).forEach(
                field -> {
                    NotNullValidation notNull = field.getDeclaredAnnotation(NotNullValidation.class);
                    if(notNull != null) {
                        System.out.println(field.getName());
                    }
                }
        );
        return validationResult;
    }
}
