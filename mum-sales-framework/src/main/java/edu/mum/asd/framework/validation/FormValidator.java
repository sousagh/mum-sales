package edu.mum.asd.framework.validation;

import edu.mum.asd.framework.di.BaseController;

/**
 * Created by gustavosousa on 4/17/17.
 */
public interface FormValidator {
    ValidationResult validate(BaseController controller);
}
