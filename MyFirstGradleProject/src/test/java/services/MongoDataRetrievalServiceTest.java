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

	@Before
	public final void beforeDoing() {

		try{          
			MongoClient mongoClient = new MongoClient("localhost", 27017);
			mongoService.setMongoDatabase(mongoClient.getDatabase("school"));
			mongoDatabase.getCollection("testStudents").drop();

		}catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
	}

	@Test
	public final void testGetMostSuccessfulStudentwithoutData() {		
		MongoDatabase mongoDatabase = mongoService.getMongoDatabase();
		mongoDatabase.createCollection("testStudents");
		MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("testStudents");
		mongoService.setMongoCollection(mongoCollection);

		List<Scores> batikanScoresList =  asList(new Scores(100.0, ScoreType.exam),
				new Scores(90.3, ScoreType.quiz),
				new Scores(90.1, ScoreType.homework));

		List<Scores> ecemScoresList =  asList(new Scores(90.9, ScoreType.exam),
				new Scores(90.3, ScoreType.quiz),
				new Scores(90.1, ScoreType.homework));

		List<Scores> ahmetScoresList =  asList(new Scores(60.1, ScoreType.exam),
				new Scores(60.9, ScoreType.quiz),
				new Scores(60.8, ScoreType.homework));

		List<Student> testStudentsList = asList(
				new Student(300, "Batikan", batikanScoresList),
				new Student(301, "Ecem", ecemScoresList),
				new Student(302, "Ahmet", ahmetScoresList));

		mongoCollection.insertMany(testStudentsList);
		mongoService.setStudentsList(testStudentsList);

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
