package edu.mum.asd.framework.exception;

/**
 * Created by gustavosousa on 4/15/17.
 */
public class DatabaseException extends Exception {
    public  DatabaseException(Exception e){
        super(e);
    }
    public DatabaseException(String message, Exception e){
        super(message, e);
    }
}
