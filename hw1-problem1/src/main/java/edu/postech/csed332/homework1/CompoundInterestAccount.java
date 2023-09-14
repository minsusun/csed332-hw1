package edu.postech.csed332.homework1;

/**
 * A compound interest account. Given the current balance B, the annual interest
 * rate r and the elapsed date d, the new balance is B * (1 + r)^(d/365)
 */
class CompoundInterestAccount extends AbstractAccount implements Account {
    //TODO implement this (including fields and a constructor if needed)
    CompoundInterestAccount(int number, double balance, double ir, String owner) {
        super(number, balance, ir, owner);
    }

    public void elapseTime(int elapsedDate) {
        //TODO implement this
        double currentBalance = getBalance();
        double delta = currentBalance * Math.pow(1 + getIR(), (double)elapsedDate / 365) - currentBalance;
        deposit(delta);
    }
}
