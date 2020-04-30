package services;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import com.google.gson.Gson;

import model.Student;

import exceptions.DatabaseConnectionProblem;
import model.*;

public class MongoDataRetrievalService implements DataRetrievalOperations{

	private final Logger logger = LoggerFactory.getLogger(MongoDataRetrievalService.class);

	private MongoDatabase mongoDatabase;
	private MongoCollection<Document> mongoCollection;

	private MongoDatabase getMongoDatabase() {
		return mongoDatabase;
	}

	private void setMongoDatabase(MongoDatabase mongoDatabase) {
		this.mongoDatabase = mongoDatabase;
	}

	private MongoCollection<Document> getMongoCollection() {
		return mongoCollection;
	}

	private void setMongoCollection(MongoCollection<Document> mongoCollection) {
		this.mongoCollection = mongoCollection;
	}

	@Override
	public void init(String databaseName, String collectionName) throws DatabaseConnectionProblem {
		try {
			if(databaseName != "" && collectionName != "") {
				connectMongoDb(databaseName, collectionName);
			}
			else {
				logger.error("Invalid database or collection name");
				throw new DatabaseConnectionProblem();
			}
		} catch (UnknownHostException e) {
			throw new DatabaseConnectionProblem();
		}
	}

	private void connectMongoDb(String databaseName, String collectionName) throws UnknownHostException {
		//MongoClient mongoClient = new MongoClient("localhost", 27017);
		CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
				fromProviders(PojoCodecProvider.builder().automatic(true).build()));
		MongoClient mongoClient = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());

		setMongoDatabase(mongoClient.getDatabase(databaseName));
		setMongoCollection(mongoDatabase.getCollection(collectionName));	
	}

	@Override
	public void addStudents(List<Student> studentsList) {
		MongoDatabase mongoDatabase = getMongoDatabase();
		MongoCollection<Student> collection = mongoDatabase.getCollection("students", Student.class);
		collection.insertMany(studentsList);
	}

	@Override
	public void addStudent(Student student) {
		MongoDatabase mongoDatabase = getMongoDatabase();
		MongoCollection<Student> collection = mongoDatabase.getCollection("students", Student.class);
		collection.insertOne(student);
	}

	@Override
	public List<Student> getStudents() {
		return readDataUsingPojo();
	}

	private List<Student> readDataUsingPojo() {
		List<Student> studentsList = new ArrayList<Student>();
		MongoDatabase mongoDatabase = getMongoDatabase();
		MongoCollection<Document> mongoColl = mongoDatabase.getCollection("students");

		if(mongoCollection.find().cursor().hasNext())
		{
			MongoCursor<Document> cursor = mongoColl.find().cursor();
			Gson gson = new Gson();

			while(cursor.hasNext())
			{

				Document myDoc = cursor.next();
				Student student = gson.fromJson(myDoc.toJson(), Student.class);
				studentsList.add(student);
			}
		}

		return studentsList;
	}

	// POJO
	@Override
	public Optional<Student> getMostSuccessfulStudent() {

		List<Double> scoresList = new ArrayList<Double>();
		List<Student> studentsList = getStudents();

		Optional<Student> optStudent = Optional.empty();

		Student mostSuccessfulStudent = new Student();

		for (Student student : studentsList) {
			for(Scores scores : student.getScores()) {
				if(scores.getType().equals(ScoreType.EXAM.toString())) {
					scoresList.add(scores.getScore());	
				}
			}
		}

		scoresList = scoresList.stream()
				.sorted(Collections.reverseOrder()).collect(Collectors.toList());

		double maxScore = scoresList.stream().findFirst().get();

		for(Student student : studentsList) {
			for(Scores scores : student.getScores()) {
				if(scores.getScore() == maxScore){
					mostSuccessfulStudent = student;
				}
			}
		}

		optStudent = Optional.ofNullable(mostSuccessfulStudent);

		if(!optStudent.isPresent()) {
			logger.error("Optional is empty");
			optStudent = Optional.empty();
			return optStudent;
		}
		logger.info("The most successful student: ");			
		return optStudent;
	}

	@Override
	public List<Student> getMostSuccessfulStudents(int amount) {
		List<Student> successfulStudentList = new ArrayList<Student>();
		List<Double> scoresList = new ArrayList<Double>();
		List<Student> studentsList = getStudents();

		if(amount < studentsList.size()) {

			for (Student student : studentsList) {
				for(Scores scores : student.getScores()) {
					if(scores.getType().equals(ScoreType.EXAM.toString())) {
						scoresList.add(scores.getScore());	
					}
				}
			}

			scoresList = scoresList.stream()
					.sorted(Collections.reverseOrder()).collect(Collectors.toList());

			for(Student student : studentsList) {
				for(Scores scores : student.getScores()) {
					for(int i=0; i<amount; i++) {
						if(scores.getScore() == scoresList.get(i)){
							successfulStudentList.add(student);
						}
					}	
				}
			}

			logger.info("the most successful " + amount + " students: ");
		}
		else {
			logger.error("invalid amount");
		}

		return successfulStudentList;
	}

	@Override
	public Student getMostSuccessfulStudentByType(ScoreType scoreType) {
		Student theMostSuccessfulStudentByType = new Student();
		List<Student> studentsList = getStudents();
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

		logger.info("The Most Successful Student By " + scoreType.toString());
		return theMostSuccessfulStudentByType;
	}


	@Override
	public void deleteCollection(String collectionName) {
		mongoDatabase.getCollection(collectionName).drop();		
	}

	@Override
	public void createCollection(String collectionName) {
		mongoDatabase.createCollection(collectionName);
	}

}