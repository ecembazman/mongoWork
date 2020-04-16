package model;

import java.util.List;
import java.util.Objects;

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

	@Override
	public String toString() {
		return "Student [name=" + name + ", id=" + id + ", scores=" + scores + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(name, id, scores);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (scores == null) {
			if (other.scores != null)
				return false;
		} else if (!scores.equals(other.scores))
			return false;
		return true;
	}

}
