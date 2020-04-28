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
		//
		//		dataOperations.createCollection(collectionName);
		//
		//		List<Scores> batikanScoresList =  asList(new Scores(69.9, ScoreType.exam),
		//				new Scores(69.9, ScoreType.quiz),
		//				new Scores(69.9, ScoreType.homework));
		//
		//		List<Scores> ecemScoresList =  asList(new Scores(60.0, ScoreType.exam),
		//				new Scores(60.0, ScoreType.quiz),
		//				new Scores(60.0, ScoreType.homework));
		//
		//		Student ahmet = new Student(new ObjectId(), "ahmet", batikanScoresList);
		//		Student merve = new Student(new ObjectId(), "merve", ecemScoresList);
		//		dataOperations.addStudent(ahmet);
		//		dataOperations.addStudent(merve);

		System.out.println(dataOperations.getMostSuccessfulStudent());
		System.out.println(dataOperations.getMostSuccessfulStudentByType(ScoreType.homework));
	}
}
