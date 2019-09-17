package controller;

import model.*;
import storage.Storage;

public class Controller {

    public static User authenticateUser(String username, String password) {
        User user = null;

        for (User u : Storage.getUsers()) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                user = u;
                break;
            }
        }

        return user;
    }

    public static User getUserById(String userId) {

        User userObject = null;

        for (User user : Storage.getUsers()) {
            if (user.getId() == Integer.parseInt((userId))) {
                userObject = user;
                break;
            }
        }
        return userObject;
    }

    public static void updateUser(String userId, String email, String name, String address, int telephoneNumber,
            String username, String password) {

        for (User user : Storage.getUsers()) {
            if (user.getId() == Integer.parseInt((userId))) {
                user.setName(name);
                user.setEmail(email);
                user.setAddress(address);
                user.setTelephoneNumber(telephoneNumber);
                user.setUsername(username);
                user.setPassword(password);
                break;
            }
        }
    }

    public static void createUser(String name, String email, String address, int telephoneNumber, String username,
            String password) {

        User user = new User(name, email, address, telephoneNumber, username, password);
        Storage.addUser(user);

    }

    public static void createTrip(String date, String timeOfDeparture, String timeOfArrival, String departureAddress,
            String arrivalAddress, User user) {

        Trip trip = new Trip(date, timeOfDeparture, timeOfArrival, departureAddress, arrivalAddress, user);
        Storage.addTrip(trip);
    }

}
