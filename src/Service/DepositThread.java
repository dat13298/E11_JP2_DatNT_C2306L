package Service;

import Entity.Account;
import Entity.EStatus;
import Entity.Transaction;
import Repository.AccountRepo;
import Repository.TransactionRepo;

import java.time.LocalDateTime;
import java.util.List;

public class DepositThread extends BankService implements Runnable {

    public DepositThread(List<Account> accounts, Transaction transaction, AccountRepo accountRepo, TransactionRepo transactionRepo, String transactionPath, String accountPath) {
        super(accounts, transaction, accountRepo, transactionRepo, transactionPath, accountPath);
    }

    @Override
    public Transaction transactionBankService() {
        super.getTransaction().getAccount().setBalance(
                super.getTransaction().getAmount() + super.getTransaction().getAccount().getBalance()
        );
        super.getTransaction().setStatus(EStatus.C);
        super.getTransaction().setDatetime(LocalDateTime.now());
        super.getTransactionRepo().addT(super.getTransaction());

        if (super.getTransaction().getStatus() == EStatus.C) {
            System.out.println("Deposit successful your Balance: " + super.getTransaction().getAccount().getBalance());
        } else System.out.println("Deposit failed");

        return super.getTransaction();
    }

    @Override
    public void run() {
        transactionBankService();
    }
}
