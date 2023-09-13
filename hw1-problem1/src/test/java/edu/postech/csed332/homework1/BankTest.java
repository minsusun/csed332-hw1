package edu.postech.csed332.homework1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankTest {
    Bank wb;

    @BeforeEach
    void setup() {
        wb = new Bank();
    }

    @Test
    void testCreateAccount() {
        var account = wb.createAccount("Thomas", 90000, 0.01, false);
        assertNotNull(account);
        assertEquals(account.getClass(), SimpleInterestAccount.class);
        assertEquals(90000, account.getBalance());
        assertEquals("Thomas", account.getOwner());
        assertEquals(100000, account.getAccountNumber());
    }

    @Test
    void testCreateSecondAccount() {
        wb.createAccount("Thomas", 90000, 0.01, false);
        var account = wb.createAccount("Peter", 80000, 0.02, true);
        assertNotNull(account);
        assertEquals(account.getClass(), CompoundInterestAccount.class);
        assertEquals(80000, account.getBalance());
        assertEquals("Peter", account.getOwner());
        assertEquals(100001, account.getAccountNumber());
    }

    @Test
    void testTransferMoney() {
        var account1 = wb.createAccount("Thomas", 90000, 0.01, false);
        var account2 = wb.createAccount("Peter", 80000, 0.02, true);
        wb.transfer(account1, account2, 20000);
        assertEquals(70000, account1.getBalance());
        assertEquals(100000, account2.getBalance());
        wb.transfer(account2, account1, 10000);
        assertEquals(80000, account1.getBalance());
        assertEquals(90000, account2.getBalance());
    }

    @Test
    void testFindAccount() {
        wb.createAccount("Thomas", 90000, 0.01, false);
        wb.createAccount("Peter", 80000, 0.02, false);
        var reqAccount = wb.findAccount(100001);
        assertEquals("Peter", reqAccount.getOwner());
    }

    @Test
    void testCompoundInterestAccount() {
        wb.createAccount("Thomas", 90000, 0.01, true);
        var account = wb.findAccount(100000);
        assertNotNull(account);
        assertEquals(account.getClass(), CompoundInterestAccount.class);
        account.elapseTime(20);
        assertEquals(90049.08350, account.getBalance(), 0.01);

    }
}

