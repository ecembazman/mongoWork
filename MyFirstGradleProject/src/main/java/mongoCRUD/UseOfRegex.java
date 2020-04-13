package mongoCRUD;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class UseOfRegex {
	public static void regex_Example(DBCollection collection, String name) {
		BasicDBObject regexQuery = new BasicDBObject();
		regexQuery.put("name", 
				new BasicDBObject("$regex", name)
				.append("$options", "i"));

		System.out.println(regexQuery.toString());

		DBCursor cursor = collection.find(regexQuery);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}
}
