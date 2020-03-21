package ua.redrain47.batch.model;

import java.math.BigDecimal;

public class User {
    private static final BigDecimal MIN_BALANCE = new BigDecimal(10);
    private Long id;
    private String fullName;
    private String email;
    private BigDecimal balance;

    public User(Long id, String fullName, String email, BigDecimal balance) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public boolean isLowBalance() {
        return balance.compareTo(MIN_BALANCE) < 0;
    }
}
