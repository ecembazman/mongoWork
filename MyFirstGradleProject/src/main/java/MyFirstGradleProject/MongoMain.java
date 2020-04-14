package MyFirstGradleProject;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

import functionsForScore.*;
import model.*;
import mongoOperations.*;

public class MongoMain {
	public static void main(String [] args){
		MongoDb mongoDb = new MongoDb();
		try {
			mongoDb.connectMongoDb();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		mongoDb.setMongoDatabase("school");
		mongoDb.setMognoCollection("students");

		MongoCRUD mongoCRUD = new MongoCRUD();
//		mongoCRUD.lessThanGreaterThan(mongoDb.getMongoCollection(), "_id", 2, 6);
//		mongoCRUD.selectAllRecordsFromACollection(mongoDb.getMongoCollection());
//		mongoCRUD.notInCollection(mongoDb.getMongoCollection(), "_id" ,5);
//		mongoCRUD.andLogicalComparison(mongoDb.getMongoCollection(), "_id", "name", 9, "Sanda Ryba");
//		mongoCRUD.orLogicalComparison(mongoDb.getMongoCollection(), "_id", "name", 9, "Sanda Ryba");
//		mongoCRUD.regexOfCollection(mongoDb.getMongoCollection(), "name", "Sanda Ryba");
//		mongoCRUD.selectFirstRecordInCollection(mongoDb.getMongoCollection());
//		mongoCRUD.getSpecificField(mongoDb.getMongoCollection(), "scores", "score", "type", 2);

		Scores quizScores = new Scores();			 
		//ShowScoreWithType.getSpecificScoreAndType(mongoDb.getMongoCollection(), "quiz", quizScores);

		HashMap<String, Double> quizScoresMap = new HashMap<String, Double>();
		quizScoresMap = ReturnScoreMap.returnMap(mongoDb.getMongoCollection(), "quiz", quizScores);

		ArrayList<Double> quizScoresList = new ArrayList<Double>();
		quizScoresList = ReturnScoreArrayList.returnaArrayList(mongoDb.getMongoCollection(), "quiz", quizScores);
		//System.out.println(quizScoresList);

		//ShowScoresLessAndGrater.setScoresListAndLimits(quizScoresList, 60.0, 90.0);

		ArrayList<Double> limitedQuizScoresList = new ArrayList<Double>();
		limitedQuizScoresList = ReturnLilmitedScoresList.returnLimitedList(quizScoresList, 60.0, 90.0);
		System.out.println(limitedQuizScoresList);
	}

}

