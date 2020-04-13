package functionsForScore;

import java.util.ArrayList;

public class ReturnLilmitedScoresList {
	public static ArrayList<Double> returnLimitedList(ArrayList<Double> fieldList, double graterThan, double lessThan) {
		ArrayList<Double> limitedScoreList = new ArrayList<Double>();

		for(int index = 0; index < fieldList.size(); index++) {
			if(fieldList.get(index) > graterThan && fieldList.get(index) < lessThan) {
				limitedScoreList.add(fieldList.get(index));
			}
		}
		return limitedScoreList;
	}
}
