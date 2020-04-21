package databaseCRUDApp;

import exceptions.DatabaseConnectionProblem;
import model.ScoreType;
import services.DataRetrievalOperations;

public class ReportGenerator {

	private DataRetrievalOperations dataOperations;

	// dependency injection
	public void setDataRetrievalOperations(DataRetrievalOperations dataOperations) {
		this.dataOperations = dataOperations;
	}

	public void report() {
		try {
			dataOperations.init();
		} catch (DatabaseConnectionProblem e) {
			System.err.println("There is an error" + e); // TODO error
			return;
		}
		dataOperations.getMostSuccessfulStudentByType(ScoreType.homework);
		// print 
	}

}
