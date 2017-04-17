package edu.mum.asd.framework.di;

import edu.mum.asd.framework.ApplicationContext;
import edu.mum.asd.framework.validation.FormValidator;
import edu.mum.asd.framework.validation.FormValidatorImpl;
import edu.mum.asd.framework.validation.ValidationResult;

/**
 * Created by gustavosousa on 4/13/17.
 */
public class BaseController {

    public BaseController() {
        ApplicationContext applicationContext = ApplicationContext.getInstance();
        /*
        TODO implement a real visitor
         */
        applicationContext.applyContext(this);
    }

    public ValidationResult validate(){

        FormValidator validator = new FormValidatorImpl(this);

        return validator.validate();
    }

}
