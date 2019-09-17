package storage;

import java.util.ArrayList;

import model.*;

public class Storage {
    public static ArrayList<User> users = new ArrayList<>();

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void addUser(User user) {
        if (!users.contains(user)) {
            users.add(user);
        }
    }

    public static void removeUser(User user) {
        if (users.contains(user)) {
            users.remove(user);
        }
    }
}
