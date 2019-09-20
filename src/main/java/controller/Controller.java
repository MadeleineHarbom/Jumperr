package controller;

import model.*;
import storage.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import javax.servlet.http.Part;

import java.nio.channels.Channels;

//////////////////////////////////////////////////
//various imports
//////////////////////////////////////////////////
//// import com.google.auth.Credentials;
import com.google.api.gax.paging.Page;
import com.google.cloud.ReadChannel;
import com.google.cloud.WriteChannel;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
//import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File;
//////////////////////////////////////////////////

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
            String password) {

        User user = new User(name, email, address, telephoneNumber, username, password);
        LocalStorage.addUser(user);

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
            String departureAddress, String arrivalAddress, User user) {

        for (Trip trip : LocalStorage.getTrips()) {
            if (trip.getId() == Integer.parseInt(tripId)) {
                trip.setDate(date);
                trip.setDepartureAddress(departureAddress);
                trip.setArrivalAddress(arrivalAddress);
                trip.setTimeOfDeparture(timeOfDeparture);
                trip.setTimeOfArrival(timeOfArrival);
                break;
            }
        }
    }

    public static void createTrip(String date, String timeOfDeparture, String timeOfArrival, String departureAddress,
            String arrivalAddress, User user) {

        Trip trip = new Trip(date, timeOfDeparture, timeOfArrival, departureAddress, arrivalAddress, user);
        LocalStorage.addTrip(trip);
    }

    // opretter forbindelse til Google Storage Bucket samt returnerer et
    // bucket-objekt
    public static Bucket getBucketConnection() throws IOException {
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
    public static Storage getBucketConnection_storage() throws IOException {
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
        Blob blob = getBucketConnection_storage().get(BlobId.of(getBucketConnection().getName(), dataFileName));

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

    public static void updateUsersInGoogleStorage(ArrayList<User> users, String uploadedFileName) {

    }

    // henter alle trips fra Google Storage Bucket, opretter dem som objekter og
    // tilføjer dem til LocalStorage
    public static void downloadTripsFromGoogleStorage(String fileName) throws IOException {
        String dataFileName = fileName;
        Blob blob = getBucketConnection_storage().get(BlobId.of(getBucketConnection().getName(), dataFileName));

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

            Trip trip = new Trip(date, timeOfDeparture, timeOfArrival, departureAddress, arrivalAddress, user);
            LocalStorage.addTrip(trip);
        }
    }

    public static void updateTripsInGoogleStorage(ArrayList<User> users, String uploadedFileName) {

    }

}
