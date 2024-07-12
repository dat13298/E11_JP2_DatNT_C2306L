package Service;

import Entity.Account;
import Entity.Transaction;
import Repository.AccountRepo;
import Repository.TransactionRepo;

import java.util.List;

public abstract class BankService {
    private List<Account> accounts;
    private Transaction transaction;
    private AccountRepo accountRepo;
    private TransactionRepo transactionRepo;
    private String transactionPath;
    private String accountPath;

    public BankService(List<Account> accounts,Transaction transaction, AccountRepo accountRepo, TransactionRepo transactionRepo, String transactionPath,  String accountPath) {
        this.accounts = accounts;
        this.transaction = transaction;
        this.accountRepo = accountRepo;
        this.transactionRepo = transactionRepo;
        this.transactionPath = transactionPath;
        this.accountPath = accountPath;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public AccountRepo getAccountRepo() {
        return accountRepo;
    }

    public String getAccountPath() {
        return accountPath;
    }

    public String getTransactionPath() {
        return transactionPath;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public TransactionRepo getTransactionRepo() {
        return transactionRepo;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public abstract Transaction transactionBankService();
}
