package com.search;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusinessData {

	@SerializedName("business_id")
	@Expose
	private String businessId;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("city")
	@Expose
	private String city;
	@SerializedName("state")
	@Expose
	private String state;
	@SerializedName("stars")
	@Expose
	private double stars;
	@SerializedName("RestaurantsPriceRange2")
	@Expose
	private double restaurantsPriceRange2;
	@SerializedName("categories")
	@Expose
	private List<String> categories = null;

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public double getStars() {
		return stars;
	}

	public void setStars(double stars) {
		this.stars = stars;
	}

	public double getRestaurantsPriceRange2() {
		return restaurantsPriceRange2;
	}

	public void setRestaurantsPriceRange2(double restaurantsPriceRange2) {
		this.restaurantsPriceRange2 = restaurantsPriceRange2;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

}