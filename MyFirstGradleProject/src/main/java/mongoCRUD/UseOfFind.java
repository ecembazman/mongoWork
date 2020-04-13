package mongoCRUD;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class UseOfFind {
	public static void selectAllRecordsFromACollection(DBCollection collection) 
	{
	    DBCursor cursor = collection.find();
	    while(cursor.hasNext())
	    {
	        System.out.println(cursor.next());
	    }
	}
}
