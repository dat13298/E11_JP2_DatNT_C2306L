package Service;

import Entity.Account;
import Entity.EStatus;
import Entity.EType;
import Entity.Transaction;
import Repository.AccountRepo;
import Repository.TransactionRepo;

import java.time.LocalDateTime;
import java.util.List;

public class WithdrawalThread extends BankService implements Runnable {
    public WithdrawalThread(Transaction transaction, TransactionRepo transactionRepo, String transactionPath) {
        super(transaction, transactionRepo, transactionPath);
    }

    @Override
    public Transaction transactionBankService() {
        if (super.getTransaction().getAmount() > super.getTransaction().getAccount().getBalance()) {
            super.getTransaction().setStatus(EStatus.R);
            System.out.println("Withdrawal failed");
        }
        if (super.getTransaction().getStatus() != EStatus.R) {
            super.getTransaction().getAccount().setBalance(
                    super.getTransaction().getAccount().getBalance() - super.getTransaction().getAmount()
            );
            super.getTransaction().setStatus(EStatus.C);
            System.out.println("Withdrawal successful");
        }
        super.getTransaction().setDatetime(LocalDateTime.now());
        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(super.getTransaction().getAmount());
        newTransaction.setAccount(super.getTransaction().getAccount());
        newTransaction.setType(EType.WITHDRAWAL);
        newTransaction.setStatus(super.getTransaction().getStatus());
        newTransaction.setDatetime(LocalDateTime.now());
        super.getTransactionRepo().addT(newTransaction);
        return super.getTransaction();
    }

    @Override
    public void run() {
        transactionBankService();
    }
}
