package com.search;

import java.util.HashMap;
import java.util.List;

public class Method2 {

	Method2() {
		// Query Generate.
		MongoDBConnector mdc = new MongoDBConnector();
		HashMap<String, List<String>> ub = new HashMap<String, List<String>>();
		ub = mdc.userBusinnessData();
	}

}
