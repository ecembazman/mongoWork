package mongoCRUD;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class UseOfFields {

	public static void selectSingleRecordAndFieldByRecordNumber(DBCollection collection, int id1, int id2) 
	{
	    BasicDBObject whereQuery = new BasicDBObject();
	    whereQuery.put("_id", id1);
	    BasicDBObject fields = new BasicDBObject();
		fields.put("_id", id2);
	  
	    DBCursor cursor = collection.find(whereQuery, fields);
	    while (cursor.hasNext()) {
	        System.out.println(cursor.next());
	    }
	}
}
