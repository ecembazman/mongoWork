package model;

import java.util.List;

public class Student {

	private int _id;
	private String name;
	private List<Scores> scores;
	
	public Student() {

	}
	
	public Student(int _id, String name, List<Scores> scores) {
		super();
		this._id = _id;
		this.name = name;
		this.scores = scores;
	}
	
	public void set_id(int _id){
		this._id = _id;
	}
	public int get_id(){
		return this._id;
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

	@Override
	public String toString() {

		String allScores = "";
		for (Scores scr : scores) {
			allScores = allScores + " " + scr.toString() + " ";
		}

		return "Student{" +
		"_id=" + _id +
		", name='" + name  +
		", scores: " + allScores +
		'}';
	}
}