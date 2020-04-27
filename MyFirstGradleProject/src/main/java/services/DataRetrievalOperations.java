package services;

import java.util.List;

import exceptions.DatabaseConnectionProblem;
import model.ScoreType;
import model.Student;

public interface DataRetrievalOperations {

	public void init(String databaseName, String collectionName) throws DatabaseConnectionProblem;

	public void delete(String collectionName);

	public void create(String collectionName);

	public List<Student> getStudents();

	public void addStudents(List<Student> studentsList);

	public void addStudent(Student student);

	public Student getMostSuccessfulStudent();

	public List<Student> getMostSuccessfulStudents(int amount);

	public Student getMostSuccessfulStudentByType(ScoreType scoreType);
}
