package model;

public class Scores
{
    private double score;
    private String type;

    public void setScore(double score){
        this.score = score;
    }
    public double getScore(){
        return this.score;
    }
    public void setType(String type){
        this.type = type;
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