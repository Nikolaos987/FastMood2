package com.example.fastmood2;

public class User {
    private static int id;
    private static String fullname;
    private static boolean isStaff;

    User(int usr_id, boolean flag, String name) {
        this.id = usr_id;
        this.isStaff = flag;
        this.fullname = name;
    }

    public static void setID(int user_id) {
        id = user_id;
    }

    public static int getID() {
        return id;
    }

    public static boolean getIsStaff() {
        return isStaff;
    }

    public static String getFullname() {
        return fullname;
    }
}
