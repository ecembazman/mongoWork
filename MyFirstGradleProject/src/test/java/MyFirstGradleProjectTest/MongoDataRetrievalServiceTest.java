package MyFirstGradleProjectTest;

import static org.junit.Assert.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import static java.util.Arrays.asList;

import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import model.ScoreType;
import model.Scores;
import model.Student;

public class MongoDataRetrievalServiceTest {

	private MongoClient mongoClient;
	private MongoCollection<Student> mongoCollection;
	private MongoDatabase mongoDatabase;

	@Before
	public final void beforeDoing() {

		try{          
			mongoClient = new MongoClient("localhost", 27017);
			mongoDatabase = mongoClient.getDatabase("school");
			//mongoDatabase.createCollection("testStudents");
			mongoCollection = mongoDatabase.getCollection("testStudents", Student.class);
		}catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
	}

	@Test
	public final void connectTest() {
		List<String> colNameListExpected = new ArrayList<String>();
		List<String> colNameListActual = new ArrayList<String>();

		colNameListExpected.add("students");
		colNameListExpected.add("testStudents");
		for (String name : mongoDatabase.listCollectionNames()) {
			colNameListActual.add(name.toString());
		}

		assertEquals(colNameListExpected, colNameListActual);
	}

	/*

	@Test
	public final void testGetMostSuccessfulStudentwithoutData() {
		
	}

	@Test
	public final void testGetMostSuccessfulStudentwithData() {
		//setup
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

		//execute TODO

	}
	 */

	/*	@Test

	public final void testInit() {

	}

	@Test
	public final void testGetMostSuccessfulStudents() {
		fail("Not yet implemented"); // TODO
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
