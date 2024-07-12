package Service;

import Entity.Account;
import Entity.ECurrency;
import Entity.EType;
import Entity.Transaction;
import Repository.TransactionRepo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionHistory {
    private List<Account> accounts;
    private List<Transaction> transactions;
    private TransactionRepo transactionRepo;

    public TransactionHistory(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Map<Account, List<Transaction>> getTransactionByDate(LocalDate startDate, LocalDate endDate) {
        return transactions.stream()
                .filter(t-> t.getDatetime().isAfter(startDate.atStartOfDay())
                            &&t.getDatetime().isBefore(endDate.atStartOfDay()))
                .collect(Collectors.groupingBy(Transaction::getAccount));
    }

    public Map<Account, List<Transaction>> mapTransactionByCondition(int time) {
        return transactions.stream()
                .filter(t-> Duration.between(t.getDatetime(), LocalDateTime.now()).toDays() < time
                        && t.getType() == EType.WITHDRAWAL
                        && t.getAccount().getCurrency() == ECurrency.USD)
                .collect(Collectors.groupingBy(Transaction::getAccount));
    }
}
