package services;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
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

	private MongoDatabase mongoDatabase;
	private MongoCollection<Document> mongoCollection;
	private List<Student> studentsList;
	
	public MongoDatabase getMongoDatabase() {
		return mongoDatabase;
	}

	public void setMongoDatabase(MongoDatabase mongoDatabase) {
		this.mongoDatabase = mongoDatabase;
	}

	public MongoCollection<Document> getMongoCollection() {
		return mongoCollection;
	}

	public void setMongoCollection(MongoCollection<Document> mongoCollection) {
		this.mongoCollection = mongoCollection;
	}

	public List<Student> getStudentsList() {
		return studentsList;
	}

	public void setStudentsList(List<Student> studentsList) {
		this.studentsList = studentsList;
	}

	@Override
	public void init() throws DatabaseConnectionProblem {
		try {
			connectMongoDb();
			readDataUsingPojo();
			System.out.println(getMostSuccessfulStudent());
		} catch (UnknownHostException e) {
			throw new DatabaseConnectionProblem();
		}
	}

	private void connectMongoDb() throws UnknownHostException {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		setMongoDatabase(mongoClient.getDatabase("school"));
		setMongoCollection(mongoDatabase.getCollection("students"));
	}

	private void readDataUsingPojo() {
		MongoCollection mongoCollection = getMongoCollection();
		if(mongoCollection.find().cursor().hasNext())
		{
			MongoCursor<Document> cursor = mongoCollection.find().cursor();
			Gson gson = new Gson();
			List<Student> studentsList = new ArrayList<Student>();

			while(cursor.hasNext())
			{
				Document myDoc = cursor.next();
				Student student = gson.fromJson(myDoc.toJson(), Student.class);
				studentsList.add(student);
				setStudentsList(studentsList);
			}
		}
	}

	// POJO
	@Override
	public Student getMostSuccessfulStudent() {
		
		List<Double> scoresList = new ArrayList<Double>();
		List<Student> studentsList = getStudentsList();
		
		Student mostSuccessfulStudent = new Student();

		for (Student student : studentsList) {
			scoresList.add(student.getScores().get(0).getScore());	
		}

		scoresList = scoresList.stream()
				.sorted(Collections.reverseOrder()).collect(Collectors.toList());

		for(Student student : studentsList) {
			if(student.getScores().get(0).getScore() == scoresList.get(0)){
				mostSuccessfulStudent = student;
			}
		}
		logger.info("The most successful student: ");
		return mostSuccessfulStudent;
	}

	@Override
	public List<Student> getMostSuccessfulStudents(int amount) {
		List<Student> successfulStudentList = new ArrayList<Student>();
		List<Double> scoresList = new ArrayList<Double>();
		List<Student> studentsList = getStudentsList();

		if(amount < studentsList.size()) {

			for (Student student : studentsList) {
				scoresList.add(student.getScores().get(0).getScore());	
			}

			scoresList = scoresList.stream()
					.sorted(Collections.reverseOrder()).collect(Collectors.toList());

			for(Student student : studentsList) {
				for(int i=0; i<amount; i++) {
					if(student.getScores().get(0).getScore() == scoresList.get(i)){
						successfulStudentList.add(student);
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
}