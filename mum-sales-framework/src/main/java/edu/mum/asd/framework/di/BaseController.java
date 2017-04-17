package edu.mum.asd.framework.di;

import edu.mum.asd.framework.ApplicationContext;

import java.lang.reflect.Field;

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

}
