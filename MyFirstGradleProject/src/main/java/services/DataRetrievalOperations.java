package services;

import java.util.List;

import exceptions.DatabaseConnectionProblem;
import model.Student;

public interface DataRetrievalOperations {

	public void init() throws DatabaseConnectionProblem;

	public Student getMostSuccessfulStudent();

	public List<Student> getMostSuccessfulStudents(int amount);

	// TODO parametresine enum alacak
	public Student getMostSuccessfulStudentByType();

}
