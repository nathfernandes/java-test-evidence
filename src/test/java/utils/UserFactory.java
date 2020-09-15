package utils;

import utils.CRUD.User;

public class UserFactory {
    public static User createValidUser(){
        return new User(
                "nath@teste.com",
                "senhadeteste"
        );
    }

    public static User createInvalidUser(){
        return new User(
                "nath@teste.com",
                "testing"
        );
    }
}
