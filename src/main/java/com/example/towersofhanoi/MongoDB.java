package com.example.towersofhanoi;

import java.util.Date;

public class MongoDB {
    public static MongoDatabase mongoDatabase;
    static final String hostname = "localhost";
    static int port = 27017;
    public static final String database = "towers-of-Hanoi";
    public static final String [] collections = {"users", "statistics", "achievements", "notifications", "complaints", "auto_increments"};
    protected MongoClient mongoClient;
    public void connect() {
        this.mongoClient = new MongoClient(hostname, port);
        this.mongoDatabase = mongoClient.getDatabase(database);
        System.out.println("Connected to MongoDB");
    }
    public void disconnect() {
        if (this.mongoClient != null) {
            this.mongoClient.close();
            System.out.println("Disconnected from MongoDB");
        }
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
    public boolean checkUser(String username, String password, String collectionName) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);

        // Create a query to check if the user exists
        Document query = new Document("username", username)
                .append("password", password);

        // Retrieve documents matching the query
        MongoCursor<Document> cursor = collection.find(query).iterator();

        // Check if any matching document is found
        boolean exists = cursor.hasNext();

        cursor.close();

        return exists;
    }
    public Document getUserByUsername(String username) {
        MongoCollection<Document> collection = mongoDatabase.getCollection("users");

        // Create a query to retrieve the user based on the username
        Document query = new Document("username", username);

        // Retrieve the user document
        Document userDocument = collection.find(query).first();

        return userDocument;
    }
    public boolean checkUser2(String username, String password, String collectionName) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);

        // Create a query to check if the user exists
        Document query = new Document("username", username);

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
    public void updateUsername(String currentUsername, String newUsername) {
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
    public void deleteAccount(String username) {
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
