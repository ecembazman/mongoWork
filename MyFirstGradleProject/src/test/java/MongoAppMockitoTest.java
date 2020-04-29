import static org.junit.Assert.*;

import java.util.List;
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
		List<Scores> batikanScoresList =  asList(new Scores(99.9, ScoreType.exam),
				new Scores(99.9, ScoreType.quiz),
				new Scores(99.9, ScoreType.homework));

		expectedStudent.setStudent_id(null);
		expectedStudent.setName("batikan");
		expectedStudent.setScores(batikanScoresList);
		
		//When
		Mockito.when(ops.getMostSuccessfulStudent()).thenReturn(expectedStudent);
		
		//Then		
		assertEquals(expectedStudent, ops.getMostSuccessfulStudent());
	}
}
