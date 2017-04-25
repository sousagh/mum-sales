package edu.mum.asd.framework.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import edu.mum.asd.framework.data.read.QueryAdapter;
import edu.mum.asd.framework.data.read.QueryAdapterImpl;
import edu.mum.asd.framework.data.read.TimeMeasureQueryAdapterImpl;
import edu.mum.asd.framework.di.InjectableComponent;
import edu.mum.asd.framework.domain.StorableEntity;
import edu.mum.asd.framework.exception.DatabaseException;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static com.mongodb.client.model.Filters.eq;


/**
 * Created by gustavosousa on 4/15/17.
 * TODO add Decorator Behavior
 */
public class MongodbRepositoryImpl<T extends StorableEntity> implements Repository<T> {

    final private ObjectMapper mapper = new ObjectMapper();
    private final Class<?> aClass;

    private MongoCollection<Document> collection;

    public MongodbRepositoryImpl(MongoCollection<Document> collection, Class<?> aClass) {
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

            if (object.getId() != null) {

                ObjectId objectId = new ObjectId(object.getId());
                objectDoc.append("_id", objectId);
                collection.replaceOne(eq("_id", objectId), objectDoc);

            } else {
                collection.insertOne(objectDoc);
            }
        } catch (JsonProcessingException e) {
            throw new DatabaseException("Error while saving document", e);
        }
    }


    public void remove(T object) {

        if (object.getId() != null) {
            collection.deleteOne(eq("_id", new ObjectId(object.getId())));
        }
    }

    @Override
    public QueryAdapter createQueryAdapter() {

        QueryAdapterImpl queryAdapter = new QueryAdapterImpl(collection, aClass);
        return new TimeMeasureQueryAdapterImpl(queryAdapter);
    }

}
