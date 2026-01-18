package coding2.model;



public class Transaction {

	private String transactionId;
	private String transactionType;
	private Double amount;
	private Long timestamp;
	public Transaction(String transactionId, String transactionType, Double amount, Long timestamp) {
		super();
		this.transactionId = transactionId;
		this.transactionType = transactionType;
		this.amount = amount;
		this.timestamp = timestamp;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return String.format("Transaction[%-20s %-20s %-10.2f  %20d ] ", transactionId,transactionType,amount,timestamp);

	}
	
}
