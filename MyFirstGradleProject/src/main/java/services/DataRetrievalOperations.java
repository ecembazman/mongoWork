package services;

import java.util.List;
import java.util.Optional;

import exceptions.DatabaseConnectionProblem;
import model.ScoreType;
import model.Student;

public interface DataRetrievalOperations {

	public void init(String databaseName, String collectionName) throws DatabaseConnectionProblem;

	public void deleteCollection(String collectionName);

	public void createCollection(String collectionName);

	public List<Student> getStudents();

	public void addStudents(List<Student> studentsList);

	public void addStudent(Student student);

	public Optional<Student> getMostSuccessfulStudent();

	public List<Student> getMostSuccessfulStudents(int amount);

	public Student getMostSuccessfulStudentByType(ScoreType scoreType);
}
