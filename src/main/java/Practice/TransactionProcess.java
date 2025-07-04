package BookMyShow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class TransactionProcess {
    static class Transaction {
        public String id;
        public String name;
        public String type;
        public long amount;
        public String timestamp;
        public Transaction(String id, String name,long amount, String type, String timestamp) {
            this.id = id;
            this.name = name;
            this.type = type;
            this.amount = amount;
            this.timestamp = timestamp;
        }
    }

    public List<Transaction> getTop5Trans(List<Transaction> transactions,String name,String startTimestamp, String endTimestamp){
        return transactions.stream()
                .filter(t -> t.name.equals(name))
                .filter(t -> t.type.equals("DEBIT"))
                .filter(t -> t.timestamp.compareTo(startTimestamp) >= 0 && t.timestamp.compareTo(endTimestamp)<=0)
                .sorted(Comparator
                    .comparingLong((Transaction t) -> t.amount)
                        .reversed()
                        .thenComparing( t -> t.timestamp))
                .limit(5)
                .collect(Collectors.toList());
    }

    public static void main(String agrs[]) throws JsonProcessingException {
        List<Transaction> transactions = Arrays.asList(
                new Transaction("txn001", "user123", 5000, "DEBIT", "2025-06-20T10:15:00Z"),
                new Transaction("txn002", "user123", 15000, "DEBIT", "2025-06-15T08:30:00Z"),
                new Transaction("txn003", "user123", 15000, "DEBIT", "2025-06-14T08:30:00Z"),
                new Transaction("txn004", "user123", 25000, "DEBIT", "2025-06-18T09:00:00Z"),
                new Transaction("txn005", "user123", 1000, "DEBIT", "2025-06-10T11:00:00Z"),
                new Transaction("txn006", "user123", 20000, "DEBIT", "2025-06-19T14:00:00Z"),
                new Transaction("txn007", "user999", 30000, "CREDIT", "2025-06-20T10:00:00Z")
        );
        String userId = "user123";
        String startDate = "2025-06-01T00:00:00Z";
        String endDate = "2025-06-30T23:59:59Z";

        TransactionProcess transactionProcess = new TransactionProcess();

        List<Transaction> top5 = transactionProcess.getTop5Trans(transactions, userId, startDate, endDate);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String jsonOutput = mapper.writeValueAsString(top5);
        System.out.println(jsonOutput);
    }
}
