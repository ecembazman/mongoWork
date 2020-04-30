package services;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import exceptions.DatabaseConnectionProblem;
import model.ScoreType;
import model.Student;

public class DynamoDataRetrievalOperations implements DataRetrievalOperations {

	@Override
	public void init(String databaseName, String collectionName) throws DatabaseConnectionProblem {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<Student> getMostSuccessfulStudent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getMostSuccessfulStudents(int amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student getMostSuccessfulStudentByType(ScoreType scoreType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addStudents(List<Student> studentsList) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addStudent(Student student) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCollection(String collectionName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createCollection(String collectionName) {
		// TODO Auto-generated method stub

	}

}
