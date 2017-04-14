package edu.mum.asd.framework.di;

import edu.mum.asd.framework.ApplicationContext;

import java.lang.reflect.Field;

/**
 * Created by gustavosousa on 4/13/17.
 */
public class BaseController {
    public BaseController() {

        Class<? extends BaseController> controllerClass = this.getClass();

        for(Field field : controllerClass.getDeclaredFields()){
            Class type = field.getType();
            AutoInjected declaredAnnotation = field.getDeclaredAnnotation(AutoInjected.class);
            if(declaredAnnotation != null){
                try {
                    ApplicationContext instance = ApplicationContext.getInstance();

                    Object o = instance.getBean(type);
                    field.setAccessible(true);
                    field.set(this, o);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
