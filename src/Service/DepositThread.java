package Service;

import Entity.Account;
import Entity.EStatus;
import Entity.EType;
import Entity.Transaction;
import Repository.TransactionRepo;

import java.time.LocalDateTime;

public class DepositThread extends BankService implements Runnable {


    public DepositThread(Transaction transaction, TransactionRepo transactionRepo, String transactionPath) {
        super(transaction, transactionRepo, transactionPath);
    }

    @Override
    public Transaction transactionBankService() {
        super.getTransaction().getAccount().setBalance(
                super.getTransaction().getAmount() + super.getTransaction().getAccount().getBalance()
        );
        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(super.getTransaction().getAmount());
        newTransaction.setAccount(super.getTransaction().getAccount());
        newTransaction.setType(EType.DEPOSIT);
        newTransaction.setStatus(EStatus.C);
        newTransaction.setDatetime(LocalDateTime.now());
        super.getTransactionRepo().addT(newTransaction);

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
