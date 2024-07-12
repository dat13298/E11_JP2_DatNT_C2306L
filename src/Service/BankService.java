package Service;

import Entity.Account;
import Entity.Transaction;
import Repository.AccountRepo;
import Repository.TransactionRepo;

import java.util.List;

public abstract class BankService {
    private Transaction transaction;
    private TransactionRepo transactionRepo;
    private String transactionPath;

    public BankService(Transaction transaction, TransactionRepo transactionRepo, String transactionPath) {
        this.transaction = transaction;
        this.transactionRepo = transactionRepo;
        this.transactionPath = transactionPath;
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
