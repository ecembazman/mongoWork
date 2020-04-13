package mongoCRUD;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class UseOfLogicOr {
	public static void orLogicalComparison_Example(DBCollection collection, int id, String name) 
	{
	    BasicDBObject orQuery = new BasicDBObject();
	    List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
	    obj.add(new BasicDBObject("_id", id));
	    obj.add(new BasicDBObject("name", name));
	    orQuery.put("$or", obj);
	  
	    System.out.println(orQuery.toString());
	  
	    DBCursor cursor = collection.find(orQuery);
	    while (cursor.hasNext()) {
	        System.out.println(cursor.next());
	    }
	}
}
