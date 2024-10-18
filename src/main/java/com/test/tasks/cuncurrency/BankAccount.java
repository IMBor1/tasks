package com.test.tasks.cuncurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private Long accountId;
    private Long balance;
    private Lock lock = new ReentrantLock();
    private List<BankAccount> accounts = new ArrayList<>();

    public BankAccount() {

    }

    public BankAccount(List<BankAccount> accounts) {
        this.accounts = accounts;
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<BankAccount> accounts) {
        this.accounts = accounts;
    }

    public BankAccount(Long accountId, Long balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public void deposit(BankAccount account, long amount) {
        lock.lock();
        try {

            account.setBalance(account.getBalance() + amount);
        } finally {
            {
                lock.unlock();
            }
        }
    }

    public synchronized void withdraw(BankAccount account, long amount) {
        lock.lock();
        try {
            if (account.getBalance()>=amount) {
                account.setBalance(account.getBalance() - amount);
            }else {
                System.out.println("Не хватает денег");
            }
        } finally {
            lock.unlock();
        }
    }

    public long getTotalBalance() {
        long sum = 0;
        for (int i = 0; i < accounts.size(); i++) {
            sum += accounts.get(i).getBalance();
        }
            return sum;
    }
}
