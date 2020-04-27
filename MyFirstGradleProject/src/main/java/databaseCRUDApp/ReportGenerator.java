package databaseCRUDApp;

import static java.util.Arrays.asList;

import java.util.List;

import exceptions.DatabaseConnectionProblem;
import model.ScoreType;
import model.Scores;
import model.Student;
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

		//dataOperations.create(collectionName);

		List<Scores> batikanScoresList =  asList(new Scores(100.0, ScoreType.EXAM),
				new Scores(100.0, ScoreType.QUIZ),
				new Scores(100.0, ScoreType.HOMEWORK));

		//Student batikan = new Student(300, "batikan", batikanScoresList);
		//dataOperations.addStudent(batikan);

		System.out.println(dataOperations.getMostSuccessfulStudent());
		//System.out.println(dataOperations.getMostSuccessfulStudentByType(ScoreType.HOMEWORK));
	}
}
