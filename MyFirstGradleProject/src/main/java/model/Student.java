package model;

import java.util.List;

public class Student {

	private String name;
	private int id;
	private List<Scores> scores;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Scores> getScores() {
		return scores;
	}
	public void setScores(List<Scores> scores) {
		this.scores = scores;
	}
}
