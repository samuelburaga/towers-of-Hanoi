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
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Date;

public class MongoDBConnection implements Database {
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
    public void insertUser(String firstName, String lastName, String username, String password, String collectionName) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
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
}
