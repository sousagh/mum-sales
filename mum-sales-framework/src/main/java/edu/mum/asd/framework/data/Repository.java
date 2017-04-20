package edu.mum.asd.framework.data;

import edu.mum.asd.framework.data.read.QueryAdapter;
import edu.mum.asd.framework.exception.DatabaseException;
import edu.mum.asd.framework.security.UserData;

/**
 * Created by gustavosousa on 4/15/17.
 */
public interface Repository <T> {

     void deleteAll();

     void save(T object) throws DatabaseException;
     void remove(T object);
     QueryAdapter createQueryAdapter();
}