package storage;

import java.util.ArrayList;
import model.*;

public class Storage {

    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Trip> trips = new ArrayList<>();

    // users
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

    // trips
    public static ArrayList<Trip> getTrips() {
        return trips;
    }

    public static void addTrip(Trip trip) {
        if (!trips.contains(trip)) {
            trips.add(trip);
        }
    }

    public static void removeTrip(Trip trip) {
        trips.remove(trip);
    }

}