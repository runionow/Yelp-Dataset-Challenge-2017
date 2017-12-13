package com.search;

public class ReviewItem {
	String businessId;
	String userId;
	String ratingStars;
	String reviewText;
	
	public ReviewItem(String userId, String businessId, String ratingStars, String reviewText) {
		this.businessId=businessId;
		this.userId=userId;
		this.ratingStars=ratingStars;
		this.reviewText=reviewText;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRatingStars() {
		return ratingStars;
	}

	public void setRatingStars(String ratingStars) {
		this.ratingStars = ratingStars;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
}
