package Repository;

import Entity.Account;
import Entity.Customer;
import Entity.ECurrency;
import Generic.IBankRepository;
import Global.Validation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.List;

public class AccountRepo implements IBankRepository<Account> {
    private List<Account> accounts;
    private CustomerRepo customerRepo;

    public AccountRepo(List<Account> accounts, CustomerRepo accountRepo) {
        this.accounts = accounts;
        this.customerRepo = accountRepo;
    }

    @Override
    public List<Account> getAll(String filePath) {
        try {
            if (!Validation.fileExists(filePath)) throw new Exception("File does not exist!");
            BufferedReader bfr = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = bfr.readLine()) != null){
                String[] data = line.split(";");
                Account account = new Account();
                account.setId(data[0]);
                Customer customer = customerRepo.getById(Integer.parseInt(data[1])).get();
                account.setCustomer(customer);
                account.setBalance(Double.parseDouble(data[2]));
                account.setCurrency(ECurrency.valueOf(data[3]));
                accounts.add(account);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return accounts;
    }

    @Override
    public List<Account> writeAll(List<Account> list, String filePath) {
        return accounts;
    }

    @Override
    public Optional<Account> findById(String id) {
        return accounts.stream().filter(account -> account.getId().equals(id)).findFirst();
    }

    @Override
    public Account addT(Account obj) {
        return null;
    }

    @Override
    public Account insertT(String filePath, Account obj) {
        return null;
    }

    @Override
    public Account updateT(String filePath, Account obj) {
        return null;
    }


}
