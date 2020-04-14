package mongoOperations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import model.Scores;
import model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MongoCRUD {
	private final Logger logger = LoggerFactory.getLogger(MongoCRUD.class);

	public void selectAllRecordsFromACollection(DBCollection collection) 
	{
		DBCursor cursor = collection.find();
		while(cursor.hasNext())
		{
			logger.info(cursor.next().toString());
		}
	}

	public void selectFirstRecordInCollection(DBCollection collection) 
	{
		DBObject dbObject = collection.findOne();
		logger.info(dbObject.toString());
	}

	public void getSpecificField(DBCollection collection, String fieldName, String innerFieldName1, String innerFieldName2, int fieldIndex) {
		DBCursor curs = collection.find(); 
		Iterator<DBObject> fields = curs.iterator(); 
		while(fields.hasNext()){ 
			BasicDBList dbList = (BasicDBList) fields.next().get(fieldName);
			BasicDBObject dbObj = (BasicDBObject) dbList.get(fieldIndex);

			Scores scores = new Scores();
			scores.setScore(dbObj.getDouble(innerFieldName1));
			//System.out.println(scores.getScore());

			scores.setType(dbObj.getString(innerFieldName2));
			//System.out.println(scores.getType());

			HashMap<String, Double> scoreWithType = new HashMap<String, Double>();
			scoreWithType.put(scores.getType(), scores.getScore());

			System.out.println(scoreWithType.toString());
		} 
	}

	public void setInCollection(DBCollection collection, String fieldName, int recordId1, int recordId2) 
	{
		BasicDBObject inQuery = new BasicDBObject();

		List<Integer> list = new ArrayList<Integer>();
		list.add(recordId1);
		list.add(recordId2);

		inQuery.put(fieldName, new BasicDBObject("$in", list));

		DBCursor cursor = collection.find(inQuery);
		while(cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	public void lessThanGreaterThan(DBCollection collection, String fieldName, int grater, int less) 
	{
		BasicDBObject getQuery = new BasicDBObject();
		getQuery.put(fieldName, new BasicDBObject("$gt", grater).append("$lt", less));
		DBCursor cursor = collection.find(getQuery);
		while(cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	public void andLogicalComparison(DBCollection collection, String fieldName1, String fieldName2, int id, String recordName) 
	{
		BasicDBObject andQuery = new BasicDBObject();
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject(fieldName1, id));
		obj.add(new BasicDBObject(fieldName2, recordName));
		andQuery.put("$and", obj);

		System.out.println(andQuery);

		DBCursor cursor = collection.find(andQuery);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	public void orLogicalComparison(DBCollection collection, String fieldName1, String fieldName2, int recordId, String recordName) 
	{
		BasicDBObject orQuery = new BasicDBObject();
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject(fieldName1, recordId));
		obj.add(new BasicDBObject(fieldName2, recordName));
		orQuery.put("$or", obj);

		System.out.println(orQuery.toString());

		DBCursor cursor = collection.find(orQuery);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	public void notInCollection(DBCollection collection, String fieldName, int notInId) 
	{
		BasicDBObject neQuery = new BasicDBObject();
		neQuery.put(fieldName, new BasicDBObject("$ne", notInId));
		DBCursor cursor = collection.find(neQuery);
		while(cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	public void regexOfCollection(DBCollection collection, String fieldName, String recordName) {
		BasicDBObject regexQuery = new BasicDBObject();
		regexQuery.put(fieldName, 
				new BasicDBObject("$regex", recordName)
				.append("$options", "i"));

		System.out.println(regexQuery.toString());

		DBCursor cursor = collection.find(regexQuery);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	public void selectSingleRecordAndFieldByRecordNumber(DBCollection collection, String fieldName, int recordId1, int recordId2) 
	{
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put(fieldName, recordId1);
		BasicDBObject fields = new BasicDBObject();
		fields.put(fieldName, recordId2);

		DBCursor cursor = collection.find(whereQuery, fields);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}
}
