package mongoCRUD;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class UseOfLogicAnd {
	public static void andLogicalComparison_Example(DBCollection collection, int id, String name) 
	{
		BasicDBObject andQuery = new BasicDBObject();
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("_id", id));
		obj.add(new BasicDBObject("name", name));
		andQuery.put("$and", obj);

		System.out.println(andQuery.toString());

		DBCursor cursor = collection.find(andQuery);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}
}
