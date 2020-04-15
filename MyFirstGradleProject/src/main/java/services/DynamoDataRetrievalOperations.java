package services;

import java.net.UnknownHostException;
import java.util.List;

import exceptions.DatabaseConnectionProblem;
import model.Student;

public class DynamoDataRetrievalOperations implements DataRetrievalOperations {

	@Override
	public void init() throws DatabaseConnectionProblem {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Student getMostSuccessfulStudent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getMostSuccessfulStudents(int amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student getMostSuccessfulStudentByType() {
		// TODO Auto-generated method stub
		return null;
	}

}
