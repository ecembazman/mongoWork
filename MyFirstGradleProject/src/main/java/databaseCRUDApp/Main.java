package databaseCRUDApp;

import services.*;

public class Main {

	public static void main(String [] args){
		ReportGenerator rg = new ReportGenerator();
		DataRetrievalOperations dataOperations = new MongoDataRetrievalService();
		rg.setDataRetrievalOperations(dataOperations);
		rg.report();
	}

}
