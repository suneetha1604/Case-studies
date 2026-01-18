package coding2.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import coding2.comparator.TransactionPriorityComparator;
import coding2.model.Transaction;

public class TransactionManagement {
	static TransactionPriorityComparator comparator = new TransactionPriorityComparator();

	public static void main(String args[]) {
		List<Transaction> us007 = new ArrayList<>();
		us007.add(new Transaction("TXN001","RTGS",300000.2,1678886400000l));
		us007.add(new Transaction("TXN002","RTGS",300000.2,16788864000001l));
		us007.add(new Transaction("TXN003","RTGS",300000.2,16788864000002l));
		
		printScenario("Identical Properties and amounts and different timestamps",us007, comparator);
		
		
		List<Transaction> us008 = new ArrayList<>();
		us008.add(new Transaction("TXN001","IMPS",10000.0,1678886400000l));
		us008.add(new Transaction("TXN002","IMPS",15000.0,16788864000000l));
		us008.add(new Transaction("TXN003","IMPS",10000.0,1678886400001l));
		printScenario("Same priority varying amount and time", us008, comparator);
		
		
		List<Transaction> us009 = new ArrayList<>();
		us009.add(new Transaction("A","NEFT",50000.0,100l));
		us009.add(new Transaction("B","IMPS",10000.0,100l));
		us009.add(new Transaction("C","RTGS",250000.0,100l));
		us009.add(new Transaction("D","NEFT",75000.0,100l));
		us009.add(new Transaction("E","IMPS",10000.0,101l));
		us009.add(new Transaction("F","RTGS",250000.0,101l));
		printScenario("Same priorities varying  amounts,timestamps", us009, comparator);
		
		List<Transaction> us010 = new ArrayList<>();
		us010.add(new Transaction("A","NEFT",50000.0,100l));
		us010.add(new Transaction("B","IMPS",10000.0,101l));
		us010.add(new Transaction("C","RTGS",300000.0,102l));
		printScenario("Mixed Priorities, amounts,timestamps", us010, comparator);
		
		List<Transaction> us011 = new ArrayList<>();
		us011.add(new Transaction("A","NEFT",50000.0,100l));
		us011.add(new Transaction("B","IMPS",50000.0,101l));
		us011.add(new Transaction("C","RTGS",50000.0,102l));
		printScenario("Mixed Priorities, same amounts", us011, comparator);
		
		List<Transaction> us012 = new ArrayList<>();
		us012.add(new Transaction("a","NEFT",50000.0,100l));
		us012.add(new Transaction("B","NEFT",50000.0,100l));
		us012.add(new Transaction("A","NEFT",50000.0,100l));
	
		printScenario("same Priorities, amounts,  timestamps", us012, comparator);
	
	}
	
	private static void printScenario(String title ,List<Transaction> transactions,TransactionPriorityComparator comparator) {
		
		System.out.println("===========================================================");
		System.out.println(title);
		System.out.println("===========================================================");

		System.out.println("UnorderedList: ");
		printList(transactions);
		System.out.println("----------------------------------------------------------------");
        System.out.println("Ordered List: ");
		Collections.sort(transactions,comparator);
		printList(transactions);
	}

	private static void printList(List<Transaction> transactions) {
		
		
		
		if(transactions.isEmpty()) {
			System.out.println("[]");
			return;
		}
		for(Transaction t : transactions) {
			System.out.println(t);
		}
		
	}

}
