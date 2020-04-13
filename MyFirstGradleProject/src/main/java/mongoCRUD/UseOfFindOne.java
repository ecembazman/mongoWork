package mongoCRUD;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class UseOfFindOne {
	public static void selectFirstRecordInCollection(DBCollection collection) 
	{
		DBObject dbObject = collection.findOne();
		System.out.println(dbObject);
	}
}
