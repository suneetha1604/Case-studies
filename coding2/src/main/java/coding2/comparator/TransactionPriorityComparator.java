package coding2.comparator;

import java.util.Comparator;

import coding2.model.Transaction;

public class TransactionPriorityComparator  implements Comparator<Transaction>{

	@Override
	public int compare(Transaction t1, Transaction t2) {
	
		Integer p1 = priorityOf(t1.getTransactionType());
		Integer p2 = priorityOf(t2.getTransactionType());
		if(p1!=p2) {
			return Integer.compare(p2, p1);
		}
		
		Double a1 = t1.getAmount();
		Double a2 = t2.getAmount();
		int amtStatus = Double.compare(a2, a1);
		if(amtStatus!=0) {
			return amtStatus;
		}
		
		int time = Long.compare(t1.getTimestamp(), t2.getTimestamp());
		if(time!=0) {
			
			return time;
		}
		
		
		return t1.getTransactionId().compareTo(t2.getTransactionId());
		

	}

	private Integer priorityOf(String tId) {
		switch(tId) {
		case "RTGS" : return 3;
		case "IMPS" : return 2;
		case "NEFT" : return 1;
		default  : return 0;
		}
	}
}
