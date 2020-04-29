package databaseCRUDApp;

import static java.util.Arrays.asList;
import java.util.List;

import org.bson.types.ObjectId;
import model.ScoreType;
import model.Scores;
import model.Student;

import exceptions.DatabaseConnectionProblem;

import services.DataRetrievalOperations;

public class ReportGenerator {

	private DataRetrievalOperations dataOperations;

	// dependency injection
	public void setDataRetrievalOperations(DataRetrievalOperations dataOperations) {
		this.dataOperations = dataOperations;
	}

	String collectionName = "students";
	public void report() {
		try {
			dataOperations.init("school", collectionName);
		} catch (DatabaseConnectionProblem e) {
			System.err.println("There is an error" + e); // TODO error
			return;
		}

		//		List<Scores> batikanScoresList =  asList(new Scores(100.0, ScoreType.EXAM.toString()),
		//				new Scores(100.0, ScoreType.QUIZ.toString()),
		//				new Scores(100.0, ScoreType.HOMEWORK.toString()));
		//
		//		Student batikan = new Student(new ObjectId(), "Batikan", batikanScoresList);
		//		dataOperations.addStudent(batikan);

		System.out.println(dataOperations.getMostSuccessfulStudent());
	}
}
