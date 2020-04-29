package model;

public class Scores
{
	private double score;
	private String type;
	
	public Scores(){
		
	}

	public Scores(double score, String type) {
		this.score = score;
		this.type = type;
	}

	public void setScore(double score){
		this.score = score;
	}
	public double getScore(){
		return this.score;
	}

	public void setType(String scoreType){
		this.type = scoreType;
	}
	public String getType(){
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