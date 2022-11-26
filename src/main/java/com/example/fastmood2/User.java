package com.example.fastmood2;

public class User {
    private static int id;

    User() {

    }

    User(int usr_id) {
        this.id = usr_id;
    }

    public static void setID(int user_id) {
        id = user_id;
    }

    public static int getID() {
        return id;
    }
}
