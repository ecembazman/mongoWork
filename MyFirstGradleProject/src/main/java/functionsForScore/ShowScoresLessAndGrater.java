package functionsForScore;

import java.util.ArrayList;

public class ShowScoresLessAndGrater {
	public static void setScoresListAndLimits(ArrayList<Double> scoresList, double graterThan, double lessThan){
		for(int index = 0; index < scoresList.size(); index++) {
			if(scoresList.get(index) > graterThan && scoresList.get(index) < lessThan) {
				System.out.println(scoresList.get(index));
			}
		}
	}
}
