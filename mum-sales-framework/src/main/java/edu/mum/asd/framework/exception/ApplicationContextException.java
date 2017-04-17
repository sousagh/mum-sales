package edu.mum.asd.framework.exception;

import edu.mum.asd.framework.ApplicationContext;

/**
 * Created by gustavosousa on 4/14/17.
 */
public class ApplicationContextException extends RuntimeException {

    public ApplicationContextException(Exception e){
        super(e);
    }

    public ApplicationContextException(String message, Exception e){
        super(message, e);
    }
}
