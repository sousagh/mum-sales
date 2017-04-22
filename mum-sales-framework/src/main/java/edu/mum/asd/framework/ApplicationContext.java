package edu.mum.asd.framework;

import edu.mum.asd.framework.data.*;
import edu.mum.asd.framework.di.AutoInjected;
import edu.mum.asd.framework.di.InjectableComponent;
import edu.mum.asd.framework.exception.ApplicationContextException;
import edu.mum.asd.framework.validation.FormValidator;
import edu.mum.asd.framework.validation.FormValidatorImpl;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by gustavosousa on 4/13/17.
 */
public class ApplicationContext {

    private static ApplicationContext instance = new ApplicationContext();

    private static String scanPath = null;

    private final MongoAccess mongoAccess;

    private final FormValidator formValidator;

    private Map<String, Object> extraParams = new HashMap<>();

    private Map<Class<?>, Object> objectPool = new HashMap<>();

    private Map<Class<?>, Repository> repositories = new HashMap<>();

    public static ApplicationContext getInstance(){
        return instance;
    }

    public static void initializeContext(String appScanPath) {
        scanPath = appScanPath;

    }

    private ApplicationContext(){

        populatePool();

        this.mongoAccess = new MongoAccessImpl();

        this.formValidator = new FormValidatorImpl();

        objectPool.forEach( (key, value) -> applyContext(value));

    }


    public void applyContext(Object value) {

        Class<?> valueClass = value.getClass();

        try {
            for(Field field : valueClass.getDeclaredFields()){

                AutoInjected autoInjected = field.getDeclaredAnnotation(AutoInjected.class);
                if(autoInjected != null){
                    Object o = getBean(field.getType());
                    field.setAccessible(true);
                    field.set(value, o);
                }

                DataAccess dataAccess = field.getDeclaredAnnotation(DataAccess.class);
                if(dataAccess != null){
                    Class<?> collection = dataAccess.collection();

                    Repository repository = null;

                    if(!repositories.containsKey(collection)){
                        repository = new MongodbRepositoryImpl(
                                this.mongoAccess.getCollection(collection.getSimpleName()), collection);

                        repositories.put(collection, repository);
                    }
                    repository = repositories.get(collection);
                    field.setAccessible(true);
                    field.set(value, repository);
                }

            }
        } catch (Exception e){
            throw new ApplicationContextException(e);
        }
    }

    private void populatePool() {
        Reflections reflections = new Reflections(scanPath);
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(InjectableComponent.class);
        reflections = new Reflections("edu.mum.asd.framework");
        annotated.addAll(reflections.getTypesAnnotatedWith(InjectableComponent.class));

        annotated.forEach(component ->{

            try {
                objectPool.put(component, component.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

    }

    public Object getBean(Class<?> clazz){

        Set<Map.Entry<Class<?>, Object>> entries = this.objectPool.entrySet();

        for(Map.Entry<Class<?>, Object> entry: entries){
            if(clazz.isInstance(entry.getValue())){
                return entry.getValue();
            }
        }

        return null;
    }

    public Repository getCollection(Class<?> clazz) {
        return repositories.get(clazz);
    }

    public FormValidator getFormValidator() {
        return formValidator;
    }

    public void putExtraParam(String name, Object object){
        this.extraParams.put(name, object);
    }

    public Object getExtraParam(String name){
        return extraParams.get(name);
    }
}
