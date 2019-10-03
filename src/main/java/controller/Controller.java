package controller;

import model.*;
import storage.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import org.threeten.bp.LocalTime;

import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.time.LocalDate;

//////////////////////////////////////////////////
//various imports
//////////////////////////////////////////////////
//// import com.google.auth.Credentials;
import com.google.cloud.ReadChannel;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

public class Controller {

    public static User authenticateUser(String username, String password) {
        User user = null;

        for (User u : LocalStorage.getUsers()) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                user = u;
                break;
            }
        }

        return user;
    }

    public static User getUserById(String userId) {

        User userObject = null;

        for (User user : LocalStorage.getUsers()) {
            if (user.getId() == Integer.parseInt((userId))) {
                userObject = user;
                break;
            }
        }
        return userObject;
    }

    public static void updateUser(String userId, String email, String name, String address, int telephoneNumber,
            String username, String password) {

        for (User user : LocalStorage.getUsers()) {
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
            String password) throws IOException {

        User user = new User(name, email, address, telephoneNumber, username, password);
        LocalStorage.addUser(user);

        updateUsersInGoogleStorage(LocalStorage.getUsers(), "User.txt");
    }

    public static Trip getTripById(String tripId) {

        Trip tripObject = null;

        for (Trip trip : LocalStorage.getTrips()) {
            if (trip.getId() == Integer.parseInt((tripId))) {
                tripObject = trip;
                break;
            }
        }
        return tripObject;
    }

    public static void updateTrip(String tripId, String date, String timeOfDeparture, String timeOfArrival,
            String departureAddress, String arrivalAddress, User user, int availableSeats) {

        for (Trip trip : LocalStorage.getTrips()) {
            if (trip.getId() == Integer.parseInt(tripId)) {
                trip.setDate(date);
                trip.setDepartureAddress(departureAddress);
                trip.setArrivalAddress(arrivalAddress);
                trip.setTimeOfDeparture(timeOfDeparture);
                trip.setTimeOfArrival(timeOfArrival);
                trip.setAvailableSeats(availableSeats);
                break;
            }
        }
    }

    public static void createTrip(String date, String timeOfDeparture, String timeOfArrival, String departureAddress,
            String arrivalAddress, User user, int availableSeats) throws IOException {

        Trip trip = new Trip(date, timeOfDeparture, timeOfArrival, departureAddress, arrivalAddress, user,
                availableSeats);
        LocalStorage.addTrip(trip);

        Controller.updateTripsInGoogleStorage(LocalStorage.getTrips(), "Trip.txt");
    }

    public static ArrayList<Trip> searchForTrips(String date, String timeOfDeparture) {

        ArrayList<Trip> matches = new ArrayList<>();
        LocalDate inputLocalDate = LocalDate.parse(date);
        LocalTime inputLocalTime = LocalTime.parse(timeOfDeparture);

        for (Trip t : LocalStorage.getTrips()) {
            LocalDate tripLocalDate = LocalDate.parse(t.getDate());
            LocalTime tripLocalTime = LocalTime.parse(t.getTimeOfDeparture());
            LocalTime tripArrivalLocalTime = LocalTime.parse(t.getTimeOfArrival());

            if (inputLocalDate.equals(tripLocalDate)) {
                if (inputLocalTime.isAfter(tripLocalTime) && inputLocalTime.isBefore(tripArrivalLocalTime)) {
                    matches.add(t);
                }
            }
        }

        return matches;
    }

    public static PickUpPoint getPickUpPointById(String pickuppointId) {

        PickUpPoint pickUpPointObject = null;

        for (PickUpPoint pickUpPoint : LocalStorage.getPickUpPoints()) {
            if (pickUpPoint.getId() == Integer.parseInt((pickuppointId))) {
                pickUpPointObject = pickUpPoint;
                break;
            }
        }
        return pickUpPointObject;
    }

    public static void createPickUpPoint(User jumper, int tripId, String departureAddress, String arrivalAddress,
            String price, double km) {

        PickUpPoint pickUpPoint = new PickUpPoint(jumper, tripId, departureAddress, arrivalAddress, price, km);
        LocalStorage.addPickUpPoint(pickUpPoint);
        getTripById(Integer.toString(tripId)).addPickUpPoint(pickUpPoint);
    }

    // opretter forbindelse til Google Storage Bucket samt returnerer et
    // bucket-objekt
    public static Bucket getGoogleStorageBucket() throws IOException {
        String bucketName = "jumperr.appspot.com";
        System.out.printf("Bucket name %s ", bucketName);

        FileInputStream stream = new FileInputStream("WEB-INF/credentials/jumperr-b647ad4acd5f.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(stream);
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        Bucket bucket = storage.get(bucketName);

        return bucket;
    }

    // opretter forbindelse til Google Storage Bucket samt returnerer et
    // storage-objekt
    public static Storage getGoogleStorage() throws IOException {
        String bucketName = "jumperr.appspot.com";
        System.out.printf("Bucket name %s ", bucketName);

        FileInputStream stream = new FileInputStream("WEB-INF/credentials/jumperr-b647ad4acd5f.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(stream);
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

        return storage;
    }

    // henter alle users fra Google Storage Bucket, opretter dem som objekter og
    // tilføjer dem til LocalStorage
    public static void downloadUsersFromGoogleStorage(String fileName) throws IOException {
        String dataFileName = fileName;
        Blob blob = getGoogleStorage().get(BlobId.of(getGoogleStorageBucket().getName(), dataFileName));

        ArrayList<String> lines = new ArrayList<>();

        try (ReadChannel reader = blob.reader()) { // import com.google.cloud.ReadChannel;
            BufferedReader br = new BufferedReader(Channels.newReader(reader, "UTF-8")); // import
                                                                                         // java.nio.channels.Channels;

            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }

        for (String line : lines) {

            String[] userInfo = line.split(", ");
            String type = userInfo[0];
            String name = userInfo[1];
            String email = userInfo[2];
            String address = userInfo[3];
            String phoneNumber = userInfo[4];
            String username = userInfo[5];
            String password = userInfo[6];

            if (type.equals("Admin")) {
                Admin admin = new Admin(name, email, address, Integer.parseInt(phoneNumber), username, password);
                LocalStorage.addUser(admin);
            } else {
                User user = new User(name, email, address, Integer.parseInt(phoneNumber), username, password);
                LocalStorage.addUser(user);
            }
        }
    }

    // opdaterer User.txt filen i Google Storage Bucket
    // bruges til create, update & delete -operationerne
    public static void updateUsersInGoogleStorage(ArrayList<User> users, String fileName) throws IOException {
        String dataFileName = fileName;
        Blob blob = getGoogleStorage().get(BlobId.of(getGoogleStorageBucket().getName(), dataFileName));

        String newString = "";
        WritableByteChannel channel = blob.writer();

        for (User u : users) {
            newString = newString + u.getClass().getSimpleName() + ", ";
            newString = newString + u.getName() + ", ";
            newString = newString + u.getEmail() + ", ";
            newString = newString + u.getAddress() + ", ";
            newString = newString + u.getTelephoneNumber() + ", ";
            newString = newString + u.getUsername() + ", ";
            newString = newString + u.getPassword();
            newString = newString + "\n";
        }

        channel.write(ByteBuffer.wrap(newString.getBytes()));
        channel.close();
    }

    // henter alle trips fra Google Storage Bucket, opretter dem som objekter og
    // tilføjer dem til LocalStorage
    public static void downloadTripsFromGoogleStorage(String fileName) throws IOException {
        String dataFileName = fileName;
        Blob blob = getGoogleStorage().get(BlobId.of(getGoogleStorageBucket().getName(), dataFileName));

        ArrayList<String> lines = new ArrayList<>();

        try (ReadChannel reader = blob.reader()) { // import com.google.cloud.ReadChannel;
            BufferedReader br = new BufferedReader(Channels.newReader(reader, "UTF-8")); // import
                                                                                         // java.nio.channels.Channels;

            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }

        for (String line : lines) {

            String[] TripInfo = line.split(", ");

            String date = TripInfo[1];
            String timeOfDeparture = TripInfo[2];
            String timeOfArrival = TripInfo[3];
            String departureAddress = TripInfo[4];
            String arrivalAddress = TripInfo[5];
            User user = LocalStorage.getUsers().get(0);
            String availableSeats = TripInfo[6];

            Trip trip = new Trip(date, timeOfDeparture, timeOfArrival, departureAddress, arrivalAddress, user,
                    Integer.parseInt(availableSeats));
            LocalStorage.addTrip(trip);

            // String jumper = TripInfo[7];
            if (TripInfo.length > 7) {
                int tripId = Integer.parseInt(TripInfo[8]);
                String departureAddress_pickUpPoint = TripInfo[9];
                String arrivalAddress_pickUpPoint = TripInfo[10];
                String price = TripInfo[11];
                double km = Double.parseDouble(TripInfo[12]);

                PickUpPoint pickUpPoint = new PickUpPoint(user, tripId, departureAddress_pickUpPoint,
                        arrivalAddress_pickUpPoint, price, km);
                LocalStorage.addPickUpPoint(pickUpPoint);
                trip.addPickUpPoint(pickUpPoint);
            }
        }
    }

    // opdaterer Trip.txt filen i Google Storage Bucket
    // bruges til create, update & delete -operationerne
    public static void updateTripsInGoogleStorage(ArrayList<Trip> trips, String fileName) throws IOException {
        String dataFileName = fileName;
        Blob blob = getGoogleStorage().get(BlobId.of(getGoogleStorageBucket().getName(), dataFileName));

        String newString = "";
        WritableByteChannel channel = blob.writer();

        for (Trip t : trips) {
            newString = newString + t.getClass().getSimpleName() + ", ";
            newString = newString + t.getDate() + ", ";
            newString = newString + t.getTimeOfDeparture() + ", ";
            newString = newString + t.getTimeOfArrival() + ", ";
            newString = newString + t.getDepartureAddress() + ", ";
            newString = newString + t.getArrivalAddress() + ", ";
            newString = newString + t.getAvailableSeats();
            newString = newString + "\n";
        }

        channel.write(ByteBuffer.wrap(newString.getBytes()));
        channel.close();

    }

}
