package Repository;

import Entity.Account;
import Entity.EStatus;
import Entity.EType;
import Entity.Transaction;
import Generic.IBankRepository;
import Global.Format;
import Global.Validation;

import java.io.*;
import java.util.List;
import java.util.Optional;

public class TransactionRepo implements IBankRepository<Transaction> {
    public static List<Transaction> transactions;

    @Override
    public List<Transaction> getAll(String filePath) {
        try {
            if (!Validation.fileExists(filePath)) throw new Exception("File does not exist!");
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null){
                String[] data = line.split(";");
                Transaction transaction = new Transaction();
                transaction.setId(Integer.parseInt(data[0]));

                Account account = new Account();
                account.setId(data[1]);
                transaction.setAccount(Optional.of(account));

                transaction.setAmount(Double.parseDouble(data[2]));
                transaction.setType(EType.valueOf(data[3]));
                transaction.setDatetime(Format.toLocalDateTime(data[4]));
                transaction.setStatus(EStatus.valueOf(data[5]));
                transactions.add(transaction);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return transactions;
    }

    @Override
    public List<Transaction> writeAll(List<Transaction> list, String filePath) {
        return transactions;
    }

    @Override
    public Optional<Transaction> findById(String id) {
        return transactions.stream().filter(transaction -> transaction.getId() == Integer.parseInt(id)).findFirst();
    }

    @Override
    public Transaction addT(Transaction obj) {
        obj.setId(transactions.size() + 1);
        transactions.add(obj);
        return obj;
    }

    @Override
    public Transaction insertT(String filePath , Transaction obj) {
        try {
            if (!Validation.fileExists(filePath)) throw new Exception("File does not exist!");
            BufferedWriter bwn = new BufferedWriter(new FileWriter(filePath));
            bwn.write(obj.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Transaction updateT(String filePath, Transaction obj) {
        return null;
    }

    public void writeTransactionsResult(List<Transaction> list, String filePath) {
        try {
            if (!Validation.fileExists(filePath)) throw new Exception("File does not exist!");
            BufferedWriter bwn = new BufferedWriter(new FileWriter(filePath));
            list.forEach(t-> {
                try {
                    bwn.write(t + "\n");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            });
            bwn.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
