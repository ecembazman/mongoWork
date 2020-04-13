package functionsForScore;

import java.util.ArrayList;
import java.util.Iterator;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import model.Scores;

public class ReturnScoreArrayList {
	public static ArrayList<Double> returnaArrayList(DBCollection collection, String typeName, Scores scores) {
		ArrayList<Double> scoreList = new ArrayList<Double>();

		DBCursor curs = collection.find(); 
		Iterator<DBObject> fields = curs.iterator(); 
		while(fields.hasNext()){
			BasicDBList dbList = (BasicDBList) fields.next().get("scores");
			int dbListSize = (int) dbList.size();
			for(int fieldIndex=0; fieldIndex < dbListSize; fieldIndex++) {
				BasicDBObject dbObj = (BasicDBObject) dbList.get(fieldIndex);
				String dbObjTypeName = dbObj.getString("type");
				if( dbObjTypeName.equals(typeName)) {
					scores.setScore(dbObj.getDouble("score"));				   
					scores.setType(dbObj.getString("type"));

					scoreList.add(scores.getScore());
				}
			} 
		}

		return scoreList;
	}
}
