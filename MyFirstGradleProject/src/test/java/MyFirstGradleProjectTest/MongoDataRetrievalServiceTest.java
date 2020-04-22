package MyFirstGradleProjectTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import model.ScoreType;
import model.Scores;
import model.Student;
import services.MongoDataRetrievalService;

public class MongoDataRetrievalServiceTest extends MongoDataRetrievalService{

	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;

	@Before
	public final void beforeDoing() {

		try{          
			MongoClient mongoClient = new MongoClient("localhost", 27017);
			setMongoDatabase(mongoClient.getDatabase("school"));
			mongoDatabase.getCollection("testStudents").drop();

		}catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
	}

	@Test
	public final void testGetMostSuccessfulStudentwithoutData() {		
		MongoDatabase mongoDatabase = getMongoDatabase();
		mongoDatabase.createCollection("testStudents");
		setMongoCollection(mongoDatabase.getCollection("testStudents"));
		MongoCollection<Student> mongoCollection = mongoDatabase.getCollection("testStudents", Student.class);
		
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
		setStudentsList(testStudentsList);
		
		Student studentActual = getMostSuccessfulStudent();
		Student studentExpected = testStudentsList.get(0);

		mongoDatabase.getCollection("testStudents").drop();
		assertEquals(studentExpected, studentActual);
	}
	
	@After
	public final void afterDoing() {
		mongoDatabase.getCollection("testStudents").drop();
	}
}
