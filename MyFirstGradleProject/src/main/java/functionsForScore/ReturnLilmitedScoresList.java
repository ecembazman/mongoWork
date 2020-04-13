package functionsForScore;

import java.util.ArrayList;

public class ReturnLilmitedScoresList {
	public static ArrayList<Double> returnLimitedList(ArrayList<Double> scoresList, double graterThan, double lessThan) {
		ArrayList<Double> limitedScoreList = new ArrayList<Double>();
 
	    for(int index = 0; index < scoresList.size(); index++) {
			if(scoresList.get(index) > graterThan && scoresList.get(index) < lessThan) {
				limitedScoreList.add(scoresList.get(index));
			}
	    }
		return limitedScoreList;
	}
}
