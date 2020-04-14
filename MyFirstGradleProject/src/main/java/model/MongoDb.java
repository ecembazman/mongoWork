package model;

import java.net.UnknownHostException;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoDb {

	private MongoClient mongoClient;
	private DB mongoDatabase;
	private DBCollection mongoCollection;

	public void connectMongoDb() throws UnknownHostException {
		mongoClient = new MongoClient("localhost", 27017);
	}

	public void setMongoDatabase(String databaseName) {
		mongoDatabase = mongoClient.getDB(databaseName);
	}

	public void setMognoCollection(String collectionName) {
		mongoCollection = mongoDatabase.getCollection(collectionName);
	}

	public DB getMongoDatabase() {
		return this.mongoDatabase;
	}

	public DBCollection getMongoCollection() {
		return this.mongoCollection;
	}
}
