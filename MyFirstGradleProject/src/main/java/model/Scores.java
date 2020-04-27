package model;

public class Scores
{
	private double score;
	private ScoreType type;
	
	public Scores(){
		
	}

	public Scores(double score, ScoreType type) {
		this.score = score;
		this.type = type;
	}

	public void setScore(double score){
		this.score = score;
	}
	public double getScore(){
		return this.score;
	}
	public void setType(ScoreType scoreType){
		this.type = scoreType;
	}
	public ScoreType getType(){
		return this.type;
	}

	@Override
	public String toString() {
		return "Scores{" +
				"score=" + score +
				", type='" + type + '\'' +
				'}';
	}
}