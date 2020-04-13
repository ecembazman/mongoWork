package MyFirstGradleProject;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import functionsForScore.*;
import model.*;

import mongoCRUD.*;

public class MongoCRUD {
	public static void main(String [] args){
		try {
			MongoClient client = new MongoClient("localhost",27017);
			DB db = client.getDB( "school" );
			DBCollection collection = db.getCollection("students");

			 UseOfIn.inExample(collection, 2, 4);
//			 UseOfLessAndGrater.lessThan_GreaterThan_Example(collection, 2, 4);
//			 UseOfFind.selectAllRecordsFromACollection(collection);
//			 UseOfNotIn.negation_Example(collection, 5);
//			 UseOfLogicAnd.andLogicalComparison_Example(collection, 9, "Sanda Ryba");
//			 UseOfLogicOr.orLogicalComparison_Example(collection, 9, "Sanda Ryba");
//			 UseOfRegex.regex_Example(collection, "Sanda Ryba");
//			 UseOfFindOne.selectFirstRecordInCollection(collection);
//			 UseOfGetFileds.getSpecificField(collection, "scores", 1);

			Scores quizScores = new Scores();			 
//			ShowScoreWithType.getSpecificScoreAndType(collection, "quiz", quizScores);

			HashMap<String, Double> quizScoresMap = new HashMap<String, Double>();
			quizScoresMap = ReturnScoreMap.returnMap(collection, "quiz", quizScores);

			ArrayList<Double> quizScoresList = new ArrayList<Double>();
			quizScoresList = ReturnScoreArrayList.returnaArrayList(collection, "quiz", quizScores);
//			System.out.println(quizScoresList);

//			ShowScoresLessAndGrater.setScoresListAndLimits(quizScoresList, 60.0, 90.0);

			ArrayList<Double> limitedQuizScoresList = new ArrayList<Double>();
			limitedQuizScoresList = ReturnLilmitedScoresList.returnLimitedList(quizScoresList, 60.0, 90.0);
			System.out.println(limitedQuizScoresList);
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}

