package model;

import java.util.List;
import org.bson.types.ObjectId;

public class Student {

	private String name;
	private List<Scores> scores;
	private ObjectId student_id;

	public Student(ObjectId student_id, String name, List<Scores> scores) {
		this.student_id = student_id;
		this.name = name;
		this.scores = scores;
	}

	public Student() {

	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setScores(List<Scores> scores){
		this.scores = scores;
	}
	public List<Scores> getScores(){
		return this.scores;
	}

	public ObjectId getStudent_id() {
		return student_id;
	}

	public void setStudent_id(ObjectId student_id) {
		this.student_id = student_id;
	}

	@Override
	public String toString() {
		return "Student [student_id=" + student_id + ", name=" + name + ", scores=" + scores + "]";
	}
	
}