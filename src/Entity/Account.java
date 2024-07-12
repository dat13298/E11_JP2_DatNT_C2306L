package Entity;

import java.util.Optional;

public class Account {
    private String id;
    private Customer customer;
    private double balance;
    private ECurrency currency;

    public Account(){;}
    public Account(String id, Customer customer, double balance, ECurrency currency) {
        this.id = id;
        this.customer = customer;
        this.balance = balance;
        this.currency = currency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public void setCustomer(Optional<Customer> customer){
        this.customer = customer.orElse(null);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ECurrency getCurrency() {
        return currency;
    }

    public void setCurrency(ECurrency currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        return builder.append(id)
                .append(";")
                .append(customer.getId())
                .append(";")
                .append(balance)
                .append(";")
                .append(currency.toString())
                .toString();
    }
}
