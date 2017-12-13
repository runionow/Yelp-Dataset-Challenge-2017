package com.search;

import java.util.List;

public class CategoryRequest {

	private String businessId;
	private double stars;
	private double restaurantsPriceRange2;
	private List<String> categories = null;

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
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