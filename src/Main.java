import Controller.AccountController;
import Controller.TransactionController;
import Entity.Account;
import Entity.Customer;
import Entity.Transaction;
import Repository.AccountRepo;
import Repository.CustomerRepo;
import Repository.TransactionRepo;
import Service.CalculateProfitThread;
import Service.DepositThread;
import Service.TransactionHistory;
import Service.WithdrawalThread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static Controller.AccountController.*;
import static Controller.TransactionController.*;

public class Main {
    public static void main(String[] args) {
        String selected;
        boolean flag = false;
        float percentProfit = 0.2F;
        int time;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Customer customer = new Customer();
        Account account = new Account();
        Transaction transaction = new Transaction();

        List<Customer> customers = new ArrayList<>();
        List<Account> accounts = new ArrayList<>();
        List<Transaction> transactions = new ArrayList<>();

        String rootPath = System.getProperty("user.dir");
        String customerPath = rootPath + "/data/customersIn.txt";
        String accountPath = rootPath + "/data/accountsIn.txt";
        String transactionPath = rootPath + "/data/transactionsIn.txt";
        String transactionHistoryPath = rootPath + "/data/Account.id_transaction_history.txt";


        CustomerRepo customerRepo = new CustomerRepo(customers);
        AccountRepo accountRepo = new AccountRepo(accounts, customerRepo);
        TransactionRepo transactionRepo = new TransactionRepo(transactions, accountRepo);
        TransactionHistory transactionHistory = new TransactionHistory(transactions);

        customerRepo.getAll(customerPath);
        accountRepo.getAll(accountPath);
        transactionRepo.getAll(transactionPath);

        TransactionController.accountRepo = accountRepo;
        TransactionController.transactionRepo = transactionRepo;
        TransactionController.transaction = transaction;
        TransactionController.transactionHistory = transactionHistory;

        AccountController.accountRepo = accountRepo;
        AccountController.transactions = transactions;
        AccountController.transactionHistory = transactionHistory;

//        23232534 11-11-2020 11-11-2024
        do {
            try {
                System.out.println("=========== MENU ===========");
                System.out.println("1. Deposit");
                System.out.println("2. Withdrawal");
                System.out.println("3. Balance");
                System.out.println("4. Transaction History");
                System.out.println("5. Check Profit");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                selected = br.readLine();
                switch (selected) {
                    case "1":
                        System.out.println("------- Deposit -------");
                        deposit();//input for deposit
                        DepositThread depositThread = new DepositThread(
                                accounts
                                , transaction
                                , accountRepo
                                , transactionRepo
                                , transactionPath
                                , accountPath
                        );
                        Thread thread = new Thread(depositThread);
                        thread.start();
                        try {
                            thread.join();
                        }catch (InterruptedException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "2":
                        System.out.println("------- Withdrawal -------");
                        withdraw();//input for withdraw
                        WithdrawalThread withdrawalThread = new WithdrawalThread(
                                accounts
                                , transaction
                                , accountRepo
                                , transactionRepo
                                , transactionPath
                                , accountPath
                        );
                        Thread thread2 = new Thread(withdrawalThread);
                        thread2.start();
                        try {
                            thread2.join();
                        }catch (InterruptedException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "3":
                        System.out.println("------- Balance -------");
                        System.out.println("Balance: " + inputGetAccount().getBalance());
                        break;
                    case "4":
                        System.out.println("------- Transaction History -------");
                        inputTimespan(transactionHistoryPath);//input for get transaction by date
                        break;
                    case "5":
                        System.out.println("------- Check Profit -------");
                        Account resultAccount = inputGetAccount();
                        time = 30;
                        Account accountChecked = checkAccountProfit(resultAccount, time);//get account meets the conditions for interest accrual

                        if (accountChecked == null) {//if no account meets the conditions
                            System.out.println("Account does not meet the conditions for interest accrual.");
                        }

                        if (accountChecked != null){
                            //calculator profit thread
                            CalculateProfitThread calculateProfitThread = new CalculateProfitThread(transaction, percentProfit, accountChecked);
                            // deposit profit thread
                            DepositThread depositProfitThread = new DepositThread(
                                    accounts
                                    , transaction
                                    , accountRepo
                                    , transactionRepo
                                    , transactionPath
                                    , accountPath
                            );
                            Thread thread3 = new Thread(calculateProfitThread);
                            Thread thread4 = new Thread(depositProfitThread);
                            thread3.start();
                            thread4.start();
                            try {
                                thread3.join();
                                thread4.join();
                            }catch (InterruptedException e){
                                System.out.println(e.getMessage());
                            }
                        }
                        break;
                    case "6":
                        System.out.println("Thank you for using our service");
                        flag = true;
                        break;
                    default:
                        System.out.println("Invalid choice");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!flag);

    }
}