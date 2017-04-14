package edu.mum.asd.framework;

import edu.mum.asd.framework.di.InjectableComponent;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by gustavosousa on 4/13/17.
 */
public class ApplicationContext {

    private static ApplicationContext instance = new ApplicationContext();

    private Map<Class<?>, Object> objectPool = new HashMap<>();

    public static ApplicationContext getInstance(){
        return instance;
    }

    private ApplicationContext(){

        Reflections reflections = new Reflections("edu.mum");
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(InjectableComponent.class);

        for (Class<?> component : annotated) {

            try {
                objectPool.put(component, component.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    public Object getBean(Class<?> clazz){
        return this.objectPool.get(clazz);
    }
}
