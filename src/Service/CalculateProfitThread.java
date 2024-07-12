package Service;

import Entity.Account;
import Entity.EType;
import Entity.Transaction;

public class CalculateProfitThread implements Runnable {
    private Transaction transaction;
    private float percentProfit;
    private Account account;

    public CalculateProfitThread(Transaction transaction, float percentProfit, Account account) {
        this.transaction = transaction;
        this.percentProfit = percentProfit;
        this.account = account;
    }

    public void calculateProfit() {
        transaction.setAccount(account);
        transaction.setType(EType.DEPOSIT);
        double profit = account.getBalance() * percentProfit / 100;
        transaction.setAmount(profit);
        System.out.println("Profit : " + profit);
    }

    @Override
    public void run() {
        calculateProfit();
    }
}
