package com.umkcisguide;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.operation.MapReduceWithInlineResultsOperation;

public class MongoConnection {
	public static void main(String[] args) {
		MongoConnection c = new MongoConnection();
		c.createConnnect();
	}
	public DB createConnnect()
	{
		MongoClientURI uri = new MongoClientURI("mongodb://umkcis:1234@ds015849.mlab.com:15849/umkcisguide");
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		return db;
	}
}
