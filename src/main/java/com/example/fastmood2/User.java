package com.example.fastmood2;

public class User {
    private static boolean isStaff;
    private static int id;
    private static String fullname;

    private static String phone;

    private static String email;

    private static String username;


    User(boolean flag, int usr_id, String name, String number, String mail, String user_name) {
        this.isStaff = flag;
        this.id = usr_id;
        this.fullname = name;
        this.phone = number;
        this.email = mail;
        this.username = user_name;
    }

    // #################### getters ####################
    public static boolean getIsStaff() {
        return isStaff;
    }

    public static int getID() {
        return id;
    }

    public static String getFullname() {
        return fullname;
    }

    public static String getPhone() {
        return phone;
    }

    public static String getEmail() {
        return email;
    }

    public static String getUsername() {
        return username;
    }
    // #################### setters ####################
    public static void setIsStaff(boolean flag) {
        isStaff = flag;
    }
    public static void setID(int user_id) {
        id = user_id;
    }

    public static void setFullname(String name) {
        fullname = name;
    }

    public static void setPhone(String number) {
        phone = number;
    }

    public static void setEmail(String mail) {
        email = mail;
    }

    public static void setUsername(String user) {
        username = user;
    }
}
