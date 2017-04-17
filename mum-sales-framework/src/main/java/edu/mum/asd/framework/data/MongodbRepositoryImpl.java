package edu.mum.asd.framework.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import edu.mum.asd.framework.data.read.QueryAdapter;
import edu.mum.asd.framework.data.read.QueryAdapterImpl;
import edu.mum.asd.framework.di.InjectableComponent;
import edu.mum.asd.framework.exception.DatabaseException;
import org.bson.Document;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * Created by gustavosousa on 4/15/17.
 * TODO add Decorator Behavior
 */
public class MongodbRepositoryImpl<T> implements Repository<T> {

    final private ObjectMapper mapper = new ObjectMapper();
    private final Class<?> aClass;

    private MongoCollection<Document> collection;

    public MongodbRepositoryImpl(MongoCollection<Document> collection, Class<?> aClass){
        this.collection = collection;
        this.aClass = aClass;
    }

    @Override
    public void deleteAll() {
        BasicDBObject document = new BasicDBObject();
        collection.deleteMany(document);
    }


    public void save(T object) throws DatabaseException {
        try {
            final String userJson = mapper.writeValueAsString(object);
            final Document objectDoc = Document.parse(userJson);
            collection.insertOne(objectDoc);
        } catch (JsonProcessingException e) {
            throw new DatabaseException("Error while saving document", e);
        }
    }

    @Override
    public QueryAdapter createQueryAdapter() {

        return new QueryAdapterImpl(collection, aClass);
    }


}
