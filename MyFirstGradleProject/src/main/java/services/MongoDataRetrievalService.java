package services;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;

import org.bson.codecs.configuration.CodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import org.bson.codecs.pojo.PojoCodecProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.google.gson.Gson;

import model.Student;

import exceptions.DatabaseConnectionProblem;
import model.*;

public class MongoDataRetrievalService implements DataRetrievalOperations{

	private final Logger logger = LoggerFactory.getLogger(MongoDataRetrievalService.class);

	//private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	private MongoCollection<Document> mongoCollection;
	private Document myDoc;
	private Student student;
	private List<Student> studentsList;

	@Override
	public void init() throws DatabaseConnectionProblem {
		try {
			connectMongoDb();
			readDataUsingPojo();
			System.out.println(getMostSuccessfulStudentByType(ScoreType.homework));
			System.out.println(getMostSuccessfulStudentByType(ScoreType.exam));
			System.out.println(getMostSuccessfulStudentByType(ScoreType.quiz));
		} catch (UnknownHostException e) {
			throw new DatabaseConnectionProblem();
		}
	}

	private void connectMongoDb() throws UnknownHostException {

		MongoClient mongoClient = new MongoClient("localhost", 27017);
		mongoDatabase = mongoClient.getDatabase("school");

		for (String name : mongoDatabase.listCollectionNames()) {
			System.out.println(name);
		}

		mongoCollection = mongoDatabase.getCollection("students");
	}

	private void readDataUsingPojo() {
		if(mongoCollection.find().cursor().hasNext())
		{
			MongoCursor<Document> cursor = mongoCollection.find().cursor();
			Gson gson = new Gson();
			studentsList = new ArrayList<Student>();

			while(cursor.hasNext())
			{
				//System.out.println(cursor.next().toJson());
				myDoc = cursor.next();
				student = gson.fromJson(myDoc.toJson(), Student.class);
				studentsList.add(student);
			}
			// get all students' scores
			/*
			for (Student student : studentsList) {
				System.out.println(student.getScores());
			}
			 */
		}
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
		Student theMostSuccessfulStudentByType = new Student();
		List<Double> typeScores = new ArrayList<Double>();

		for (Student student : studentsList) {
			typeScores.add(student.getScores().get(scoreType.ordinal()).getScore());	
		}

		double typeMaxScore = typeScores
				.stream()
				.mapToDouble(v -> v)
				.max().orElseThrow(NoSuchElementException::new);

		for (Student student : studentsList) {
			if(student.getScores().get(scoreType.ordinal()).getScore() == typeMaxScore) {
				theMostSuccessfulStudentByType = student;
			}
		}

		return theMostSuccessfulStudentByType;
	}

}