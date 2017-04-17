package edu.mum.asd.util;

import com.mongodb.client.MongoCollection;
import edu.mum.asd.framework.ApplicationContext;
import edu.mum.asd.framework.data.MongoAccessImpl;
import edu.mum.asd.framework.data.Repository;
import edu.mum.asd.framework.exception.DatabaseException;
import edu.mum.asd.framework.security.UserData;
import org.bson.Document;

/**
 * Created by gustavosousa on 4/15/17.
 */
public class DBinit{

    public static void start() {

        try{
            Repository userDataRepository = ApplicationContext.getInstance().getCollection(UserData.class);

            userDataRepository.deleteAll();
            UserData user = new UserData("user", "123");
            userDataRepository.save(user);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

    }
}
