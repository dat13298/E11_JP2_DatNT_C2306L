package Controller;

import Entity.Account;
import Entity.EType;
import Entity.Transaction;
import Repository.AccountRepo;
import Repository.TransactionRepo;
import Service.TransactionHistory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static Global.Format.toLocalDate;

public class TransactionController {
    public static AccountRepo accountRepo;
    public static TransactionRepo transactionRepo;
    public static Transaction transaction;
    public static TransactionHistory transactionHistory;

    public static void deposit() {
        processTransaction(EType.DEPOSIT);
    }

    public static void withdraw() {
        processTransaction(EType.WITHDRAWAL);
    }

    private static void processTransaction(EType type) {
        String accountNumber;
        double amount;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Input Account number: ");
            accountNumber = br.readLine();
            Optional<Account> accountOpt = accountRepo.findById(accountNumber);
            if (accountOpt.isEmpty()) throw new Exception("Account not found");

            System.out.print("Input Amount: ");
            amount = Double.parseDouble(br.readLine());

            if (type == EType.WITHDRAWAL && amount % 10 != 0) {
                throw new Exception("Amount must be divisible by 10");
            }

            transaction.setAccount(accountOpt.get());
            transaction.setAmount(amount);
            transaction.setType(type);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void inputTimespan(String filePath){
        String accountNumber, startDate, endDate, selected;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
//            GET ACCOUNT BY ID
            System.out.print("Enter Account number: ");
            accountNumber = br.readLine();
            Optional<Account> accountOpt = accountRepo.findById(accountNumber);
            if (accountOpt.isEmpty()) throw new Exception("Account not found");
//            INPUT date
            System.out.print("Enter start date(dd-MM-yyyy): ");
            startDate = br.readLine();
            System.out.print("Enter end date(dd-MM-yyyy): ");
            endDate = br.readLine();
//          get transaction by date with Map
            Map<Account, List<Transaction>> transactionMap = transactionHistory.getTransactionByDate(
                    toLocalDate(startDate), toLocalDate(endDate)
            );
//          get transaction in Map by account
            List<Transaction> transactionList = transactionMap.get(accountOpt.get());
            transactionList.forEach(System.out::println);

            System.out.print("Save? (Y/N): ");
            selected = br.readLine();
            if (selected.equalsIgnoreCase("y")) {
                transactionRepo.writeTransactionsResult(transactionList, filePath);
            }
            if (selected.equalsIgnoreCase("n")) {
                System.out.println("Yep");
            }
            if (!selected.equalsIgnoreCase("n") && !selected.equalsIgnoreCase("y")) {
                throw new Exception("Invalid input");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


}
