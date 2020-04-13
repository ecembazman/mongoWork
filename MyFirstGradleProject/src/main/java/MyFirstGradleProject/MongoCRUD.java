package MyFirstGradleProject;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;


import functionsForScore.*;
import model.*;

import mongoCRUD.*;

public class MongoCRUD {
	public static void main(String [] args){
		MongoDb mongoDb = new MongoDb();
		mongoDb.connectMongoDb();
		mongoDb.setMongoDatabase("school");
		mongoDb.setMognoCollection("students");
		
		 UseOfIn.inExample(mongoDb.getMongoCollection(), 2, 4);
//		 UseOfLessAndGrater.lessThan_GreaterThan_Example(mongoDb.getMongoCollection(), 2, 4);
//		 UseOfFind.selectAllRecordsFromACollection(mongoDb.getMongoCollection());
//		 UseOfNotIn.negation_Example(mongoDb.getMongoCollection(), 5);
//		 UseOfLogicAnd.andLogicalComparison_Example(mongoDb.getMongoCollection(), 9, "Sanda Ryba");
//		 UseOfLogicOr.orLogicalComparison_Example(mongoDb.getMongoCollection(), 9, "Sanda Ryba");
//		 UseOfRegex.regex_Example(mongoDb.getMongoCollection(), "Sanda Ryba");
//		 UseOfFindOne.selectFirstRecordInCollection(mongoDb.getMongoCollection());
//		 UseOfGetFileds.getSpecificField(mongoDb.getMongoCollection(), "scores", 1);

		Scores quizScores = new Scores();			 
//		ShowScoreWithType.getSpecificScoreAndType(mongoDb.getMongoCollection(), "quiz", quizScores);

		HashMap<String, Double> quizScoresMap = new HashMap<String, Double>();
		quizScoresMap = ReturnScoreMap.returnMap(mongoDb.getMongoCollection(), "quiz", quizScores);

		ArrayList<Double> quizScoresList = new ArrayList<Double>();
		quizScoresList = ReturnScoreArrayList.returnaArrayList(mongoDb.getMongoCollection(), "quiz", quizScores);
//		System.out.println(quizScoresList);

//		ShowScoresLessAndGrater.setScoresListAndLimits(quizScoresList, 60.0, 90.0);

		ArrayList<Double> limitedQuizScoresList = new ArrayList<Double>();
		limitedQuizScoresList = ReturnLilmitedScoresList.returnLimitedList(quizScoresList, 60.0, 90.0);
		System.out.println(limitedQuizScoresList);
	}
}

