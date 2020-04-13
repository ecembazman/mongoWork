package mongoCRUD;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class UseOfIn {
	public static void inExample(DBCollection collection, int id1, int id2) 
	{
		BasicDBObject inQuery = new BasicDBObject();

		List<Integer> list = new ArrayList<Integer>();
		list.add(id1);
		list.add(id2);

		inQuery.put("_id", new BasicDBObject("$in", list));

		DBCursor cursor = collection.find(inQuery);
		while(cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}
}
