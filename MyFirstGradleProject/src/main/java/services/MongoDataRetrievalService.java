package services;

import java.net.UnknownHostException;
import java.util.List;

import org.bson.codecs.configuration.CodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import org.bson.codecs.pojo.PojoCodecProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import exceptions.DatabaseConnectionProblem;
import model.*;

public class MongoDataRetrievalService implements DataRetrievalOperations{
	private final Logger logger = LoggerFactory.getLogger(MongoDataRetrievalService.class);

	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	private MongoCollection mongoCollection;
	private DBCursor cursor;

	@Override
	public void init() throws DatabaseConnectionProblem {
		try {
			connectMongoDb();
		} catch (UnknownHostException e) {
			throw new DatabaseConnectionProblem();
		}
	}

	private void connectMongoDb() throws UnknownHostException {
		mongoClient = new MongoClient("localhost", 27017);
		CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
		mongoClient = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
		mongoDatabase = mongoClient.getDatabase("school");
		mongoCollection = mongoDatabase.getCollection("students");
		MongoCollection<Student> studentCollection= mongoDatabase.getCollection("students", Student.class);
		System.out.println(studentCollection.count());
	}

	private void readDataUsingPojo() {		
	}

	// POJO
	@Override
	public Student getMostSuccessfulStudent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getMostSuccessfulStudents(int amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student getMostSuccessfulStudentByType(ScoreType scoreType) {
		// TODO Auto-generated method stub
		return null;
	}

}