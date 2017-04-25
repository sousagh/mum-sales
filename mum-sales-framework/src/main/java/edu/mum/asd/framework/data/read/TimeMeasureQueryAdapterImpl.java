package edu.mum.asd.framework.data.read;

import edu.mum.asd.framework.domain.StorableEntity;

import java.util.List;
import java.util.Optional;

/**
 * Created by gustavosousa on 4/24/17.
 */
public class TimeMeasureQueryAdapterImpl implements QueryAdapter{

    private final QueryAdapter adapter;

    public TimeMeasureQueryAdapterImpl(QueryAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public QueryAdapter eq(String name, Object o) {
        adapter.eq(name, o);
        return this;
    }

    @Override
    public QueryAdapter regex(String name, String o) {
        adapter.regex(name, o);
        return this;
    }

    @Override
    public QueryAdapter lt(String name, Object o) {
        adapter.lt(name, o);
        return this;
    }

    @Override
    public QueryAdapter gt(String name, Object o) {
        adapter.lt(name, o);
        return this;
    }

    @Override
    public <T extends StorableEntity> Optional<T> findOne() {
        long start = System.currentTimeMillis();
        Optional<T> one = this.adapter.findOne();
        long end = System.currentTimeMillis();
        System.out.println("Query took " + (end - start) + "ms");
        return one;
    }

    @Override
    public <T extends StorableEntity> List<T> find() {
        long start = System.currentTimeMillis();
        List<T> list = this.adapter.find();

        long end = System.currentTimeMillis();
        System.out.println("Query took " + (end - start) + "ms");
        return list;
    }
}
