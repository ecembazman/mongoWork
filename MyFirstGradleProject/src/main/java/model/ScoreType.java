package model;

public enum ScoreType {
	EXAM,
	QUIZ,
	HOMEWORK;

	public String toString(){
		switch (this) {
		case EXAM: return "exam";
		case QUIZ: return "quiz";
		case HOMEWORK: return "homework";
		}
		return null;
	}
}
