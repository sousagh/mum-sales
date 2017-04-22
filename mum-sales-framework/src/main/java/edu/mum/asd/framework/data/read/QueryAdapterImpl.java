package edu.mum.asd.framework.data.read;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import edu.mum.asd.framework.domain.StorableEntity;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static jdk.management.resource.internal.ResourceIdImpl.of;

/**
 * Created by gustavosousa on 4/16/17.
 */
public class QueryAdapterImpl implements QueryAdapter {

    final private ObjectMapper mapper = new ObjectMapper();
    private final MongoCollection<Document> collection;
    private List<Bson> filters;
    private final Class<?> type;

    public QueryAdapterImpl(MongoCollection<Document> collection, Class<?> type) {
        this.collection = collection;
        this.filters = new ArrayList<>();
        this.type = type;
    }

    @Override
    public QueryAdapter eq(String key, Object o) {
        filters.add(Filters.eq(key, o));
        return this;
    }

    @Override
    public QueryAdapter regex(String name, String o) {

        String pattern = ".*" + o + ".*";
        filters.add(Filters.regex(name, pattern, "i"));
        return this;
    }

    @Override
    public <T extends StorableEntity> Optional<T> findOne() {

        FindIterable<Document> documents =  collection.find(Filters.and(filters));
        Document first = documents.first();

        try{
            if(first != null){
                ObjectId id = (ObjectId) first.remove("_id");

                T document = (T) mapper.convertValue(first, type);
                document.setId(id.toHexString());
                return Optional.of(document);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public <T extends StorableEntity> List<T> find() {

        List<T> list = new ArrayList<>();
        FindIterable<Document> documents;

        if(this.filters.isEmpty()){
            documents =  collection.find();
        }
        else{
            documents =  collection.find(Filters.and(filters));
        }

        documents.forEach((Consumer<? super Document>) doc ->{
            try{
                ObjectId id = (ObjectId) doc.remove("_id");
                T document = (T) mapper.convertValue(doc, type);
                document.setId(id.toHexString());
                list.add(document);

            } catch (Exception e){
                e.printStackTrace();
            }
        });

        return list;
    }
}
