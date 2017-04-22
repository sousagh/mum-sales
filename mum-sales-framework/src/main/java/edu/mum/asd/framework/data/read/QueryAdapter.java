package edu.mum.asd.framework.data.read;

import edu.mum.asd.framework.domain.StorableEntity;
import edu.mum.asd.framework.security.UserData;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by gustavosousa on 4/16/17.
 */
public interface QueryAdapter {

    QueryAdapter eq(String name, Object o);
    QueryAdapter regex(String name, String o);
    QueryAdapter lt(String key, Object date);
    QueryAdapter gt(String key, Object date);

    <T extends StorableEntity> Optional<T> findOne();
    <T extends StorableEntity>  List<T> find();
}