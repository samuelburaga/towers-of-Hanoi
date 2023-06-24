package com.example.towersofhanoi;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MongoDBConnection implements DatabaseConnection <Document>{
    private MongoDatabase mongoDatabase;
    private MongoClient mongoClient;
    private static final String hostname = "localhost";
    private static int port = 27017;
    private static final String database = "towers-of-Hanoi";
    private static final String [] COLLECTIONS = {"users", "statistics", "achievements", "notifications", "complaints", "auto_increments"};
    @Override
    public void connect() {

    }
    @Override
    public void disconnect() {
        if (this.mongoClient != null) {
            this.mongoClient.close();
            System.out.println("Disconnected from MongoDB");
        }
    }
    @Override
    public boolean checkIfUserExists(final String username, final String password) {
        MongoCollection<Document> collection = this.mongoDatabase.getCollection(COLLECTIONS[0]);
        // Create a query to check if the user exists
        Document query = new Document("username", username).append("password", password);
        // Retrieve documents matching the query
        MongoCursor<Document> cursor = collection.find(query).iterator();
        // Check if any matching document is found
        boolean exists = cursor.hasNext();
        cursor.close();
        return exists;
    }
    @Override
    public Document getUserByUsername(String username) {
        MongoCollection<Document> collection = mongoDatabase.getCollection("users");
        // Create a query to retrieve the user based on the username
        Document query = new Document("username", username);
        // Retrieve the user document
        Document userDocument = collection.find(query).first();
        return userDocument;
    }

    @Override
    public int getLatestUserId() {
        return 0;
    }
    @Override
    public void insertNewUser(final String firstName, final String lastName, final String username, final String password) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTIONS[0]);
        // Retrieve the next sequence value for user_id
        int user_id = getNextSequenceValue("user_id");
        // Create a new user document
        Document userDocument = new Document("user_id", user_id)
                .append("first_name", firstName)
                .append("last_name", lastName)
                .append("username", username)
                .append("password", password)
                .append("created_at", new Date());

        // Insert the user document into the collection
        collection.insertOne(userDocument);
    }
    private int getNextSequenceValue(String sequenceName) {
        MongoCollection<Document> autoIncrements = mongoDatabase.getCollection("auto_increments");
        // Find the document for the given sequenceName
        Bson filter = Filters.eq("name", sequenceName);
        Bson update = Updates.inc("value", 1);
        FindOneAndUpdateOptions options = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
        Document sequenceDocument = autoIncrements.findOneAndUpdate(filter, update, options);
        if (sequenceDocument == null) {
            // If the sequence document doesn't exist, insert it with the initial value
            sequenceDocument = new Document("name", sequenceName).append("value", 1);
            autoIncrements.insertOne(sequenceDocument);
        }
        // Retrieve and return the updated value
        return sequenceDocument.getInteger("value");
    }
    @Override
    public void updateUsername(final String currentUsername, final String newUsername) {
        MongoCollection<Document> collection = mongoDatabase.getCollection("users");
        Bson filter = Filters.eq("username", currentUsername);
        Bson update = Updates.set("username", newUsername);
        UpdateResult result = collection.updateOne(filter, update);
        if (result.getModifiedCount() > 0) {
            System.out.println("Username updated successfully. New username: " + newUsername);
        } else {
            System.out.println("Failed to update username.");
        }
    }
    public boolean checkIfUser2(String username, String password) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTIONS[0]);
        // Create a query to check if the user exists
        Document query = new Document("username", username);
        // Retrieve documents matching the query
        MongoCursor<Document> cursor = collection.find(query).iterator();
        // Check if any matching document is found
        boolean exists = cursor.hasNext();
        cursor.close();
        return exists;
    }
    public void printCollection(String collectionName) {
        MongoCollection<Document> collectionDocument = this.mongoDatabase.getCollection(collectionName);
        // Retrieve all documents from the collection
        MongoCursor<Document> cursor = collectionDocument.find().iterator();
        // Iterate over the documents and print them
        while (cursor.hasNext()) {
            Document document = cursor.next();
            System.out.println(document.toJson());
        }
        cursor.close();
    }
    @Override
    public void deleteAccount(final String username) {
        MongoCollection<Document> collection = mongoDatabase.getCollection("users");
        Bson filter = Filters.eq("username", username);
        DeleteResult result = collection.deleteOne(filter);
        if (result.getDeletedCount() > 0) {
            System.out.println("Account deleted.");
        } else {
            System.out.println("Failed to delete account.");
        }
    }
    @Override
    public void extractStatistics(TableView<StatisticsData> statisticsTable) {
        MongoCollection<Document> collection = this.mongoDatabase.getCollection(COLLECTIONS[1]);
        // Create a sort criteria to order by points in descending order
        Document sortCriteria = new Document("points", -1) // Sort by points descending (-1)
                .append("time", 1) // Sort by time ascending (1)
                .append("disks", -1) // Sort by disks descending (-1)
                .append("user_id", 1); // Sort by user ID ascending (1)
        // Retrieve the top 10 documents ordered by points
        List<Document> documents = collection.find()
                .sort(sortCriteria)
                .limit(10)
                .into(new ArrayList<>());
        // Clear existing rows
        statisticsTable.getItems().clear();
        // Iterate over the retrieved documents and populate the table
        for (Document document : documents) {
            // Retrieve the user_id from the statistics document
            int userId = document.getInteger("users_user_id");
            // Retrieve the corresponding username from the users collection
            String username = getUsernameFromUsersCollection(userId);
            // Retrieve other fields from the statistics document
            int points = document.getInteger("points");
            int disks = document.getInteger("disks");
            String time = document.getString("time");
            // Create a StatisticsData object and add it to the table
            StatisticsData data = new StatisticsData(username, points, time, disks);
            statisticsTable.getItems().add(data);
        }
    }
    private String getUsernameFromUsersCollection(int userId) {
        MongoCollection<Document> usersCollection = this.mongoDatabase.getCollection(COLLECTIONS[0]);
        // Create a query to find the user document with the given user_id
        Document query = new Document("user_id", userId);
        // Retrieve the user document
        Document userDocument = usersCollection.find(query).first();
        // Extract the username from the user document
        if (userDocument != null) {
            return userDocument.getString("username");
        }
        return null; // Return null if user document not found
    }
}
