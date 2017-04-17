package edu.mum.asd.framework.data.read;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.Optional;

import static jdk.management.resource.internal.ResourceIdImpl.of;

/**
 * Created by gustavosousa on 4/16/17.
 */
public class QueryAdapterImpl implements QueryAdapter {

    final private ObjectMapper mapper = new ObjectMapper();
    private final MongoCollection<Document> collection;
    private final Document example;
    private final Class<?> type;

    public QueryAdapterImpl(MongoCollection<Document> collection, Class<?> type) {
        this.collection = collection;
        this.example = new Document();
        this.type = type;
    }

    @Override
    public QueryAdapter eq(String key, Object o) {
        example.append(key, o);
        return this;
    }

    @Override
    public <T> Optional<T> findOne() {

        FindIterable<Document> documents = collection.find(example);
        Document first = documents.first();

        try{
            if(first != null){
                Object id = first.remove("_id");

                return Optional.of((T) mapper.convertValue(first, type));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
