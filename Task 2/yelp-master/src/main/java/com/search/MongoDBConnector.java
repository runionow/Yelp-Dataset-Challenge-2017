package com.search;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

//import static com.mongodb.client.model.Filters.eq;
//import static com.mongodb.client.model.Filters.gt;


public class MongoDBConnector {
	private MongoClient mongoClient;
	private DBObject business_query;
	
	MongoDBConnector(){
		mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		MongoDatabase db = mongoClient.getDatabase("yelp");
		MongoCollection<Document> coll = db.getCollection("business");
		System.out.println("Succesfully connected to the DB");
		business_query = new BasicDBObject();
		Document myDoc = coll.find().first();
		
		System.out.println(myDoc.toJson());
		long count = coll.count(Document.parse("{review_count:{$gt:10}}"));
		System.out.println(count);
		
		MongoCursor<Document> cursor = coll.find(Document.parse("{review_count:{$gt:10},categories:{$in :['Restaurants']}},{_id:0,business_id}")).iterator();
		try {
		    while (cursor.hasNext()) {
		        System.out.println(cursor.next().toJson());
		    }
		} finally {
		    cursor.close();
		}
		mongoClient.close();
	}
}
