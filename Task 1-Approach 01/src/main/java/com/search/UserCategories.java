
package com.search;

import java.util.ArrayList;
import java.util.List;

public class UserCategories {

	private List<String> businessIds = null;
	private double cPrange;
	private Integer cCount;

	public List<String> getBusinessIds() {
		return businessIds;
	}

	public void setBusinessIds(String bid) {
		businessIds = new ArrayList<String>();
		this.businessIds.add(bid);
	}

	public Integer getCPrange() {
		return (int) Math.ceil(cPrange);
	}

	public void setCPrange(double cPrange) {
		this.cPrange = cPrange;
	}

	public int getCCount() {
		return cCount;
	}

	public void setCCount(Integer cCount) {
		this.cCount = cCount;
	}

	public void updateBusinessId(String bid) {
		this.businessIds.add(bid);
	}
	
	public void updateCPrange(double pRange) {
		cPrange = (pRange + cPrange)/2;
	}
	
	public int getCount() {
		return businessIds.size();	
	}
	
}