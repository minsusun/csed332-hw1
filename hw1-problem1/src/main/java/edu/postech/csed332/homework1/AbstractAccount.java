package edu.postech.csed332.homework1;

public abstract class AbstractAccount implements Account {
    //TODO implement this (including fields and a constructor if needed)

    @Override
    public int getAccountNumber() {
        //TODO implement this
        return 0;
    }

    @Override
    public double getBalance() {
        //TODO implement this
        return 0;
    }

    @Override
    public String getOwner() {
        //TODO implement this
        return null;
    }

    @Override
    public void deposit(double amount) {
        //TODO implement this
    }

    @Override
    public void withdraw(double amount) throws IllegalStateException {
        //TODO implement this
    }
}
