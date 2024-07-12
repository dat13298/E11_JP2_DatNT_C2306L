package Service;

import Entity.Account;
import Entity.EStatus;
import Entity.Transaction;
import Repository.AccountRepo;
import Repository.TransactionRepo;

import java.time.LocalDateTime;
import java.util.List;

public class WithdrawalThread extends BankService implements Runnable {
    public WithdrawalThread(List<Account> accounts, Transaction transaction, AccountRepo accountRepo, TransactionRepo transactionRepo, String transactionPath, String accountPath) {
        super(accounts, transaction, accountRepo, transactionRepo, transactionPath, accountPath);
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
        super.getTransactionRepo().addT(super.getTransaction());
        super.getTransactionRepo().insertT(super.getTransactionPath(), super.getTransaction());
        return super.getTransaction();
    }

    @Override
    public void run() {
        transactionBankService();
    }
}
