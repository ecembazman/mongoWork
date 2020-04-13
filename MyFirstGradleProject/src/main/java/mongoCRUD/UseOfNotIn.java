package mongoCRUD;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class UseOfNotIn {
	public static void negation_Example(DBCollection collection, int notInId) 
	{
		BasicDBObject neQuery = new BasicDBObject();
		neQuery.put("_id", new BasicDBObject("$ne", notInId));
		DBCursor cursor = collection.find(neQuery);
		while(cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}
}
