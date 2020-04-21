package services;

import java.util.List;

import exceptions.DatabaseConnectionProblem;
import model.ScoreType;
import model.Student;

public interface DataRetrievalOperations {

	public void init() throws DatabaseConnectionProblem;

	public Student getMostSuccessfulStudent();

	public List<Student> getMostSuccessfulStudents(int amount);

	public Student getMostSuccessfulStudentByType(ScoreType scoreType);
}
