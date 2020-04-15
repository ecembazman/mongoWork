package services;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.bson.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;

import exceptions.DatabaseConnectionProblem;
import model.*;

public class MongoDataRetrievalService implements DataRetrievalOperations{
	private final Logger logger = LoggerFactory.getLogger(MongoDataRetrievalService.class);

	private MongoClient mongoClient;
	private DB mongoDatabase;
	private DBCollection mongoCollection;
	private DBCursor cursor;

	@Override
	public void init() throws DatabaseConnectionProblem {
		try {
			connectMongoDb();
		} catch (UnknownHostException e) {
			throw new DatabaseConnectionProblem();
		}
		mongoDatabase = mongoClient.getDB("school");
		mongoCollection = mongoDatabase.getCollection("students");
		cursor =  mongoCollection.find();
	}

	
	private void connectMongoDb() throws UnknownHostException {
		mongoClient = new MongoClient("localhost", 27017);
	}
/*
	@Override
	public void selectAllRecordsFromACollection() {
		while(cursor.hasNext())
		{
			logger.info(cursor.next().toString());
		}
	}

	@Override
	public void selectFirstRecordInCollection() {
		DBObject dbObject = mongoCollection.findOne();
		logger.info(dbObject.toString());
	}

	@Override
	public void getSpecificField() { 
		Iterator<DBObject> fields = cursor.iterator(); 
		while(fields.hasNext()){ 
			BasicDBList dbList = (BasicDBList) fields.next().get("scores");
			BasicDBObject dbObj = (BasicDBObject) dbList.get(0);

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

	@Override
	public void setInCollection() {
		BasicDBObject inQuery = new BasicDBObject();

		List<Integer> list = new ArrayList<Integer>();
		list.add(recordId1);
		list.add(recordId2);

		inQuery.put("_id", new BasicDBObject("$in", list));

		DBCursor cur = mongoCollection.find(inQuery);
		while(cur.hasNext()) {
			System.out.println(cur.next());
		}
	}

	@Override
	public void lessThanGreaterThan() {
		// TODO Auto-generated method stub

	}

	@Override
	public void andLogicalComparison() {
		// TODO Auto-generated method stub

	}

	@Override
	public void orLogicalComparison() {
		// TODO Auto-generated method stub

	}

	@Override
	public void notInCollection() {
		// TODO Auto-generated method stub

	}

	@Override
	public void regexOfCollection() {
		// TODO Auto-generated method stub

	}

	@Override
	public void selectSingleRecordAndFieldByRecordNumber() {
		// TODO Auto-generated method stub

	*/
	
	// POJO
	@Override
	public Student getMostSuccessfulStudent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getMostSuccessfulStudents(int amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student getMostSuccessfulStudentByType() {
		// TODO Auto-generated method stub
		return null;
	}

}