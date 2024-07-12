package Entity;

import java.time.LocalDateTime;

public class Transaction {
    private int id;
    private Account account;
    private double amount;
    private EType type;
    private LocalDateTime datetime;
    private EStatus status;

    public Transaction(){;}
    public Transaction(int id, Account account, double amount, EType type, LocalDateTime datetime, EStatus status) {
        this.id = id;
        this.account = account;
        this.amount = amount;
        this.type = type;
        this.datetime = datetime;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public EType getType() {
        return type;
    }

    public void setType(EType type) {
        this.type = type;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", account=" + account +
                ", amount=" + amount +
                ", type=" + type +
                ", datetime=" + datetime +
                ", status=" + status +
                '}';
    }
}
