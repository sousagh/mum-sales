package edu.mum.asd.framework.data;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import edu.mum.asd.framework.di.InjectableComponent;
import org.bson.Document;

/**
 * Created by gustavosousa on 4/16/17.
 */
@InjectableComponent
public class MongoAccessImpl implements MongoAccess{

    private MongoClient mongoClient;

    private MongoDatabase database;

    public MongoAccessImpl() {
        //TODO get this info from properties file.
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDatabase("sales");
    }

    @Override
    public MongoCollection<Document> getCollection(String s){
        return database.getCollection(s);
    }
}
