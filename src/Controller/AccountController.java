package Controller;

import Entity.Account;
import Entity.ECurrency;
import Entity.EType;
import Entity.Transaction;
import Repository.AccountRepo;
import Service.TransactionHistory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class AccountController {
    public static AccountRepo accountRepo;
    public static List<Transaction> transactions;
    public static TransactionHistory transactionHistory;

    public static Account inputGetAccount() {
        String accountNumber;
        Optional<Account> accountOpt = Optional.empty();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Enter account number: ");
            accountNumber = br.readLine();
            accountOpt = accountRepo.findById(accountNumber);
            if (accountOpt.isEmpty()) throw new Exception("Account not found");
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
        return accountOpt.get();
    }

    public static Account checkAccountProfit(Account account, int time) {
        //get Account does not meet the conditions for interest accrual.
        Map<Account, List<Transaction>> map = transactionHistory.mapTransactionByCondition(time);
//        check account existed in map
        if (!map.containsKey(account)) {
            return account;
        }
        return null;
    }
}
