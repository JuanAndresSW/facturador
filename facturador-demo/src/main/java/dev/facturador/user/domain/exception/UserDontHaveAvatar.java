package dev.facturador.user.domain.exception;

public class UserDontHaveAvatar extends Exception {

    public UserDontHaveAvatar(String message){
        super(message);
    }
}
