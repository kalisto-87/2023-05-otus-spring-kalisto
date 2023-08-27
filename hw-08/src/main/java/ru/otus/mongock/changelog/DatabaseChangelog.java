package ru.otus.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "DropDb", author = "Corrado Cattani", runAlways = true)
    public void dropDb(MongoDatabase mdb) {
        mdb.drop();
    }

    @ChangeSet(order = "002", id = "CreateCollections", author = "Corrado Cattani", runAlways = true)
    public void createCollections(MongoDatabase mdb) {
        mdb.createCollection("authors");
        mdb.createCollection("genre");
        mdb.createCollection("books");
        mdb.createCollection("comments");
    }

    @ChangeSet(order = "003", id = "insertAuthors", author = "Corrado Cattani", runAlways = true)
    public void insertAuthors(MongoDatabase db) {
        MongoCollection<Document> myCollection = db.getCollection("authors");
        var doc = new Document().append("name", "Jack London");
        myCollection.insertOne(doc);
        doc = new Document().append("name", "Gustave Flaubert");
        myCollection.insertOne(doc);
        doc = new Document().append("name", "James Joyce");
        myCollection.insertOne(doc);
        doc = new Document().append("name", "Emile Zola");
        myCollection.insertOne(doc);
    }

    @ChangeSet(order = "004", id = "insertGenres", author = "Corrado Cattani", runAlways = true)
    public void insertGenres(MongoDatabase db) {
        MongoCollection<Document> myCollection = db.getCollection("genre");
        var doc = new Document().append("name", "'novel'");
        myCollection.insertOne(doc);
        doc = new Document().append("name", "science fiction");
        myCollection.insertOne(doc);
        doc = new Document().append("name", "adventure fiction");
        myCollection.insertOne(doc);
    }

    @ChangeSet(order = "005", id = "insertBooks", author = " Corrado Cattani", runAlways = true)
    public void insertBooks(MongoDatabase db) {
        MongoCollection<Document> myCollection = db.getCollection("books");

    }
}
