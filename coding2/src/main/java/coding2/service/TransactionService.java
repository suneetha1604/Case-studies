package coding2.service;

import java.util.HashMap;
import java.util.Map;

public class TransactionService {

	public Map<String,Integer> getPriority() {
		HashMap<String, Integer> transactionTypePriorties = new HashMap<String, Integer>();

		transactionTypePriorties.put("RTGS", 3);
		transactionTypePriorties.put("IMPS", 2);
		transactionTypePriorties.put("NEFT", 1);
		return transactionTypePriorties;
		
	}

}
