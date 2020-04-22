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

public class MongoDataRetrievalServiceTest {

	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;

	@Before
	public final void beforeDoing() {

		try{          
			mongoClient = new MongoClient("localhost", 27017);
			mongoDatabase = mongoClient.getDatabase("school");

		}catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
	}

	@Test
	public final void connectTest() {
		mongoDatabase.createCollection("testStudents");

		List<String> colNameListExpected = new ArrayList<String>();
		List<String> colNameListActual = new ArrayList<String>();

		colNameListExpected.add("students");
		colNameListExpected.add("testStudents");

		for (String name : mongoDatabase.listCollectionNames()) {
			colNameListActual.add(name.toString());
		}
		mongoDatabase.getCollection("testStudents").drop();
		assertEquals(colNameListExpected, colNameListActual);
	}

	@Test
	public final void readDataUsingPojoWithoutCollectionTest() {
		MongoCollection<Document> docCollection;
		docCollection = mongoDatabase.getCollection("testStudents");

		List<Student> studentsListActual = new ArrayList<Student>();
		List<Student> studentsListExpected = new ArrayList<Student>();

		Student studentActual = new Student();

		if(docCollection.find().cursor().hasNext())
		{
			MongoCursor<Document> cursor = docCollection.find().cursor();
			Gson gson = new Gson();

			while(cursor.hasNext())
			{
				Document myDoc = cursor.next();
				studentActual = gson.fromJson(myDoc.toJson(), Student.class);
				studentsListActual.add(studentActual);
			}
		}
		mongoDatabase.getCollection("testStudents").drop();
		assertEquals(studentsListExpected, studentsListActual);
	}

	@Test
	public final void readDataUsingPojoWithCollectionTest() {

		mongoDatabase.createCollection("testStudents");

		MongoCollection<Document> docCollection;
		docCollection = mongoDatabase.getCollection("testStudents");

		List<Student> studentsListActual = new ArrayList<Student>();
		List<Student> studentsListExpected = new ArrayList<Student>();

		Student studentActual = new Student();

		if(docCollection.find().cursor().hasNext())
		{
			MongoCursor<Document> cursor = docCollection.find().cursor();
			Gson gson = new Gson();

			while(cursor.hasNext())
			{
				Document myDoc = cursor.next();
				studentActual = gson.fromJson(myDoc.toJson(), Student.class);
				studentsListActual.add(studentActual);
			}
		}
		mongoDatabase.getCollection("testStudents").drop();
		assertEquals(studentsListExpected, studentsListActual);
	}

	@Test
	public final void testGetMostSuccessfulStudentwithoutData() {
		
		mongoDatabase.createCollection("testStudents");
		MongoCollection<Document> docCollection = mongoDatabase.getCollection("testStudents");

		List<Student> studentsListActual = new ArrayList<Student>();
		List<Double> scoresList = new ArrayList<Double>();

		Student studentActual = new Student();
		Student studentExpected = new Student();
		Student mostSuccessfulStudentActual = new Student();

		if(docCollection.find().cursor().hasNext())
		{
			MongoCursor<Document> cursor = docCollection.find().cursor();
			Gson gson = new Gson();

			while(cursor.hasNext())
			{
				Document myDoc = cursor.next();
				studentActual = gson.fromJson(myDoc.toJson(), Student.class);
				studentsListActual.add(studentActual);
			}
		}
		
		for (Student student : studentsListActual) {
			scoresList.add(student.getScores().get(0).getScore());	
		}

		scoresList = scoresList.stream()
				.sorted(Collections.reverseOrder()).collect(Collectors.toList());

		for(Student student : studentsListActual) {
			if(student.getScores().get(0).getScore() == scoresList.get(0)){
				mostSuccessfulStudentActual = student;
			}
		}

		mongoDatabase.getCollection("testStudents").drop();
		assertEquals(studentExpected, mostSuccessfulStudentActual);
	}

	@Test
	public final void testGetMostSuccessfulStudentwithDataTest() {
		// setup
		mongoDatabase.createCollection("testStudents");
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

		// execute
		List<Student> studentsListActual = new ArrayList<Student>();
		List<Double> scoresList = new ArrayList<Double>();

		Student studentActual = new Student();
		Student studentExpected = new Student();
		Student mostSuccessfulStudentActual = new Student();

		MongoCollection<Document> docCollection = mongoDatabase.getCollection("testStudents");
		if(docCollection.find().cursor().hasNext())
		{
			MongoCursor<Document> cursor = docCollection.find().cursor();
			Gson gson = new Gson();

			while(cursor.hasNext())
			{
				Document myDoc = cursor.next();
				studentActual = gson.fromJson(myDoc.toJson(), Student.class);
				studentsListActual.add(studentActual);
			}
		}
		
		for (Student student : studentsListActual) {
			scoresList.add(student.getScores().get(0).getScore());	
		}

		scoresList = scoresList.stream()
				.sorted(Collections.reverseOrder()).collect(Collectors.toList());

		for(Student student : studentsListActual) {
			if(student.getScores().get(0).getScore() == scoresList.get(0)){
				mostSuccessfulStudentActual = student;
			}
		}

		mongoDatabase.getCollection("testStudents").drop();
		assertEquals(studentExpected, mostSuccessfulStudentActual);
	}

	@After
	public final void afterDoing() {
		mongoDatabase.getCollection("testStudents").drop();
	}

	/*
	@Test
	public final void testGetMostSuccessfulStudents() {
		fail("Not yet implemented"); // TODO
	}

	/*	@Test
	public final void testInit() {

	}

	@Test
	public final void testGetMostSuccessfulStudentByType() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testObject() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetClass() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testHashCode() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testEquals() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testClone() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testToString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testNotify() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testNotifyAll() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testWait() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testWaitLong() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testWaitLongInt() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testFinalize() {
		fail("Not yet implemented"); // TODO
	}
	 */

}
