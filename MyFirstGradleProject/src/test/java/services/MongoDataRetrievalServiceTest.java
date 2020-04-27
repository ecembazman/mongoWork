package services;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

import java.util.List;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import model.ScoreType;
import model.Scores;
import model.Student;

public class MongoDataRetrievalServiceTest {

	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	private MongoDataRetrievalService mongoService;

	@BeforeClass
	public final void beforeDoing() {

		try{          
			mongoClient = new MongoClient("localhost", 27017);
			mongoService = new MongoDataRetrievalService();
			mongoService.setMongoDatabase(mongoClient.getDatabase("school"));
			MongoDatabase mongoDatabase = mongoService.getMongoDatabase();
			mongoDatabase.createCollection("testStudents");
			mongoService.setMongoCollection(mongoDatabase.getCollection("testStudents"));

		}catch(Exception e){
			System.err.println("mongoClient error");
		}
	}

	@Test
	public final void testGetMostSuccessfulStudentwithoutData() {
		
		List<Scores> batikanScoresList =  asList(new Scores(100.0, ScoreType.EXAM),
				new Scores(90.3, ScoreType.QUIZ),
				new Scores(90.1, ScoreType.HOMEWORK));

		List<Scores> ecemScoresList =  asList(new Scores(90.9, ScoreType.EXAM),
				new Scores(90.3, ScoreType.QUIZ),
				new Scores(90.1, ScoreType.HOMEWORK));

		List<Scores> ahmetScoresList =  asList(new Scores(60.1, ScoreType.EXAM),
				new Scores(60.9, ScoreType.QUIZ),
				new Scores(60.8, ScoreType.HOMEWORK));

		List<Student> testStudentsList = asList(
				new Student(300, "Batikan", batikanScoresList),
				new Student(301, "Ecem", ecemScoresList),
				new Student(302, "Ahmet", ahmetScoresList));

		Student studentActual = mongoService.getMostSuccessfulStudent();
		Student studentExpected = testStudentsList.get(0);

		mongoDatabase.getCollection("testStudents").drop();
		assertEquals(studentExpected, studentActual);
	}

	@After
	public final void afterDoing() {
		mongoDatabase.getCollection("testStudents").drop();
	}
}
