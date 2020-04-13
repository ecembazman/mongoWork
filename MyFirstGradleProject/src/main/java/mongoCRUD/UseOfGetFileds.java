package mongoCRUD;

import java.util.HashMap;
import java.util.Iterator;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import model.Scores;

public class UseOfGetFileds{
	public static void getSpecificField(DBCollection collection, String fieldName, int fieldIndex) {
		DBObject allQuery = new BasicDBObject(); 
		DBCursor curs = collection.find(); 
		Iterator<DBObject> fields = curs.iterator(); 
		while(fields.hasNext()){ 
		   BasicDBList dbList = (BasicDBList) fields.next().get(fieldName);
		   BasicDBObject dbObj = (BasicDBObject) dbList.get(fieldIndex);
		   
		   Scores scores = new Scores();
		   scores.setScore(dbObj.getDouble("score"));
		   //System.out.println(scores.getScore());
		   
		   scores.setType(dbObj.getString("type"));
		   //System.out.println(scores.getType());
		   
		   HashMap<String, Double> scoreWithType = new HashMap<String, Double>();
		   scoreWithType.put(scores.getType(), scores.getScore());
		   
		   System.out.println(scoreWithType.toString());
		   
		} 
	}
}
