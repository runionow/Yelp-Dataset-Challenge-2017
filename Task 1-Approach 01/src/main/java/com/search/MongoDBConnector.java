package com.search;

import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MongoDBConnector {

	private MongoClient mongoClient;
	//private DBObject business_query;
	private MongoDatabase db; 

	MongoDBConnector(){
		this.mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		this.db = mongoClient.getDatabase("yelp");
		//this.business_query = new BasicDBObject();
	}

	// Auto CategoryRequest.
	public CategoryRequest categoryRequest(String businessID){
		CategoryRequest cr = new CategoryRequest();

		Gson gson = new Gson();
		Type type = new TypeToken<List<String>>(){}.getType();

		MongoCollection<Document> collBusiness = this.db.getCollection("business");

		Document cursorBusiness = collBusiness.find(eq("business_id", businessID)).first();
        // System.out.println("Business :"+cursorBusiness.toString());
		JsonObject userJson = new Gson().fromJson(cursorBusiness.toJson(), JsonObject.class);
		userJson.get("business_id").toString();

		JsonElement priceRange = userJson.get("attributes").getAsJsonObject().get("RestaurantsPriceRange2");
		JsonArray array = userJson.get("categories").getAsJsonArray();
		
		List<String> categoriesList = gson.fromJson(array, type);
		cr.setCategories(categoriesList);
		cr.setRestaurantsPriceRange2(priceRange.getAsDouble());
		cr.setStars(userJson.get("stars").getAsDouble());
		return cr;
	}
	
	public float getBusinessRating(String businessID) {
		MongoCollection<Document> collBusiness = this.db.getCollection("business");

		Document cursorBusiness = collBusiness.find(eq("business_id", businessID)).first();
        System.out.println("Business :"+cursorBusiness.toString());
        
		JsonObject userJson = new Gson().fromJson(cursorBusiness.toJson(), JsonObject.class);
		System.out.println(userJson.get("stars").getAsDouble());

//		JsonElement priceRange = userJson.get("attributes").getAsJsonObject().get("RestaurantsPriceRange2");
//		JsonArray array = userJson.get("categories").getAsJsonArray();

		return userJson.get("stars").getAsFloat();
	}

	// User-Business HashMap 
	public HashMap<String, List<String>> userBusinnessData() {
		MongoCollection<Document> collBusiness = this.db.getCollection("business");
		MongoCollection<Document> collUsers = this.db.getCollection("review");
		JsonObject userJson;
		List<String> BusinessID = new ArrayList<String>();

		// Hash-Map for Storing Business and User Info.
		HashMap<String, List<String>> userBizData = new HashMap<String, List<String>>();
        int i =0;
		MongoCursor<Document> cursorBusiness = collBusiness.find(Document.parse("{\"attributes.RestaurantsPriceRange2\":{$gt: 0},\"categories\" : {$in : [\"Restaurants\"]}, \"city\" :\"Charlotte\"}")).iterator();
		System.out.println("Number of Business:"+collBusiness.count(Document.parse("{\"attributes.RestaurantsPriceRange2\":{$gt: 0},\"categories\" : {$in : [\"Restaurants\"]}, \"city\" :\"Charlotte\"}")));
		try {
			while(cursorBusiness.hasNext()) {
				userJson = new Gson().fromJson(cursorBusiness.next().toJson(), JsonObject.class);
				BusinessID.add(userJson.get("business_id").toString());
				i++;
			}
		}
		finally {
			cursorBusiness.close();
		}
		
		System.out.println("Count :"+i);
		
		MongoCursor<Document> cursorUser = collUsers.find(Document.parse("{}")).iterator();
		try {
			while(cursorUser.hasNext()) {
				userJson = new Gson().fromJson(cursorUser.next().toJson(), JsonObject.class);
				String user = userJson.get("user_id").toString();
				String business = userJson.get("business_id").toString();

				// Check if the HashMap Contains the reciever.If not insert the 
				if(userBizData.containsKey(user)) {
					List<String> temp = userBizData.get(user);

					// Check if the businessId for the particular review contains in the HashMap or not.
					// If not add it to the List.
					if(!temp.contains(business)){
						if(BusinessID.contains(business)) {
							temp.add(business);
							userBizData.put(user, temp);
						}
					}
				}
				else {
					List<String> temp1 = new ArrayList<String>();
					if(BusinessID.contains(business)) {
						temp1.add(business);
						userBizData.put(user, temp1);
					}
				}
			}
		}
		finally {
			cursorUser.close();
		}
		return userBizData;
	}


	// Business data HashMap
	public HashMap<String,BusinessData> businessData(){
		MongoCollection<Document> collBusiness  =  this.db.getCollection("business");
		HashMap<String, BusinessData> bd = new HashMap<String, BusinessData>();
		MongoCursor<Document> cursorBusiness = collBusiness.find(Document.parse("{\"attributes.RestaurantsPriceRange2\":{$gt: 0},\"categories\" : {$in : [\"Restaurants\"]}, \"city\" :\"Charlotte\"}")).iterator();
		System.out.println(collBusiness.count(Document.parse("{\"attributes.RestaurantsPriceRange2\":{$gt: 0},\"categories\" : {$in : [\"Restaurants\"]}, \"city\" :\"Charlotte\"}")));

		try {
			while (cursorBusiness.hasNext()) {
				Gson gson = new Gson();
				BusinessData bd1 = new BusinessData();		    	
				Type type = new TypeToken<List<String>>(){}.getType();

				JsonObject userJson = new Gson().fromJson(cursorBusiness.next().toJson(), JsonObject.class);

				JsonArray array = userJson.get("categories").getAsJsonArray();
				List<String> categoriesList = gson.fromJson(array, type);

				JsonElement priceRange =userJson.get("attributes").getAsJsonObject().get("RestaurantsPriceRange2");

				// Helper.
				//				System.out.println("Business ID :" + userJson.get("business_id").toString());
				//				System.out.println("Name :" + userJson.get("name").toString());
				//				System.out.println("City :" + userJson.get("city").toString());
				//				System.out.println("State :" + userJson.get("state").toString());
				//				System.out.println("Stars :" + userJson.get("stars").getAsLong());
				//				System.out.println("price Range :" + priceRange);

				// Updating the values 
				bd1.setBusinessId(userJson.get("business_id").toString());
				bd1.setName(userJson.get("name").toString());
				bd1.setCity(userJson.get("city").toString());
				bd1.setState(userJson.get("state").toString());
				bd1.setStars(userJson.get("stars").getAsDouble());
				bd1.setCategories(categoriesList);
				bd1.setRestaurantsPriceRange2(priceRange.getAsDouble());

				// Updating the HashMap.
				bd.put(userJson.get("business_id").toString(), bd1);
			}
		}
		catch(Exception e) {
			System.out.println("Hello Error ! :"+  e.getMessage());
		}
		finally {
			cursorBusiness.close();
		}
		return bd;
	}

	public void close() {
		mongoClient.close();
	}
}
