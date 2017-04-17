package edu.mum.asd.framework.data.read;

import edu.mum.asd.framework.security.UserData;

import java.util.Optional;

/**
 * Created by gustavosousa on 4/16/17.
 */
public interface QueryAdapter {
    QueryAdapter eq(String name, Object o);

    <T> Optional<T> findOne();

}
