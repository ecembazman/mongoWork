package mongoCRUD;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class UseOfLessAndGrater {
	public static void lessThan_GreaterThan_Example(DBCollection collection, int grater, int less) 
	{
	    BasicDBObject getQuery = new BasicDBObject();
	    getQuery.put("_id", new BasicDBObject("$gt", grater).append("$lt", less));
	    DBCursor cursor = collection.find(getQuery);
	    while(cursor.hasNext()) {
	        System.out.println(cursor.next());
	    }
	}
}
