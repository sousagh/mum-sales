package edu.mum.asd.framework.data;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

/**
 * Created by gustavosousa on 4/16/17.
 */
public interface MongoAccess {
    MongoCollection<Document> getCollection(String s);
}
