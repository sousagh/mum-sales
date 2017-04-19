package edu.mum.asd.framework.security;

import edu.mum.asd.framework.domain.StorableEntity;
import org.bson.types.ObjectId;

/**
 * Created by gustavosousa on 4/14/17.
 */
public class UserData extends StorableEntity {

    private String userName;
    private String password;

    public UserData(){}

    public UserData(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

}
