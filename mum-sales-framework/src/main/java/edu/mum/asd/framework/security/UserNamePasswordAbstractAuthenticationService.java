package edu.mum.asd.framework.security;

/**
 * Created by gustavosousa on 4/14/17.
 */
public abstract class UserNamePasswordAbstractAuthenticationService implements AuthenticationService{

    public final void authenticate(UserData userData) {
        boolean isValidUser = verifyUserData(userData);

        if(isValidUser){
            onSuccess();
        } else {
            onFailure();
        }
    }

    protected abstract void onFailure();

    protected abstract void onSuccess();

    protected abstract boolean verifyUserData(UserData userData);
}
