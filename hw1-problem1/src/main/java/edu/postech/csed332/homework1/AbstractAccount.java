package edu.postech.csed332.homework1;

public abstract class AbstractAccount implements Account {
    //TODO implement this (including fields and a constructor if needed)
    int accountNumber;
    double accountBalance;
    double accountIR;
    String accountOwner;

    AbstractAccount(int number, double balance, double ir, String owner) {
        this.accountNumber = number;
        this.accountBalance = balance;
        this.accountIR = ir;
        this.accountOwner = owner;
    }

    @Override
    public int getAccountNumber() {
        //TODO implement this
        return this.accountNumber;
    }

    @Override
    public double getBalance() {
        //TODO implement this
        return this.accountBalance;
    }

    @Override
    public double getIR() {
        return this.accountIR;
    }

    @Override
    public String getOwner() {
        //TODO implement this
        return this.accountOwner;
    }

    @Override
    public void deposit(double amount) {
        //TODO implement this
        this.accountBalance += amount;
    }

    @Override
    public void withdraw(double amount) throws IllegalStateException {
        //TODO implement this
        if(this.accountBalance < amount)
            throw new IllegalStateException();
        else
            this.accountBalance -= amount;
    }
}
