package Repository;

import Entity.Customer;
import Generic.IBankRepository;
import Global.Validation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Optional;

public class CustomerRepo implements IBankRepository<Customer> {
    private final List<Customer> customers;

    public CustomerRepo(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public List<Customer> getAll(String filePath) {
        try {
            if (!Validation.fileExists(filePath)) throw new Exception("File does not exist!");
            BufferedReader bfr = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = bfr.readLine()) != null){
                String[] data = line.split(";");
                Customer customer = new Customer();
                customer.setId(Integer.parseInt(data[0]));
                customer.setName(data[1]);
                customer.setPhone(data[2]);
                customers.add(customer);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return customers;
    }

    @Override
    public List<Customer> writeAll(List<Customer> list, String filePath) {
        return customers;
    }

    @Override
    public Optional<Customer> findById(String id) {
        return Optional.empty();
    }
    public Optional<Customer> getById(int id) {
        return customers.stream().filter(c->c.getId() == id).findFirst();
    }

    @Override
    public Customer addT(Customer obj) {
        return null;
    }

    @Override
    public Customer insertT(String filePath, Customer obj) {
        return null;
    }

    @Override
    public Customer updateT(String filePath, Customer obj) {
        return null;
    }
}
