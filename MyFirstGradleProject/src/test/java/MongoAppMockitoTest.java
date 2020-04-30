import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;

import org.bson.codecs.ObjectIdGenerator;
import org.bson.types.ObjectId;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.mongodb.internal.connection.PowerOfTwoBufferPool;
import com.sun.nio.sctp.Notification;

import exceptions.DatabaseConnectionProblem;
import model.ScoreType;
import model.Scores;
import model.Student;
import services.DataRetrievalOperations;
import services.MongoDataRetrievalService;

public class MongoAppMockitoTest {
	@InjectMocks
	private static MongoDataRetrievalService mongoService;

	@BeforeClass
	public static void setUp() throws DatabaseConnectionProblem{

	}

	@Test
	public void getMostSuccessfulStudentTest(){	
		//Given
		DataRetrievalOperations ops = Mockito.mock(DataRetrievalOperations.class);

		Student expectedStudent = new Student();
		Student actualStudent = new Student();

		List<Scores> batikanScoresList =  asList(new Scores(100.0, ScoreType.EXAM.toString()),
				new Scores(100.0, ScoreType.QUIZ.toString()),
				new Scores(100.0, ScoreType.HOMEWORK.toString()));

		expectedStudent.setStudent_id(new ObjectId("5ea9806d519ece2200c3ab5d"));
		expectedStudent.setName("Batikan");
		expectedStudent.setScores(batikanScoresList);

		Optional<Student> optStudent = Optional.ofNullable(expectedStudent);

		//When
		Mockito.when(ops.getMostSuccessfulStudent()).thenReturn(optStudent);	

		if(optStudent.isPresent()) {
			actualStudent = optStudent.get();

			//Then		
			assertEquals(expectedStudent, actualStudent);
		}
		else {

			//Then		
			assertEquals(Optional.empty(), actualStudent);
		}

	}
}
