package com.test.tasks.cuncurrency.concurrentBank;

import com.test.tasks.cuncurrency.concurrentBank.BankAccount;

public class ConcurrentBank {
    private final BankAccount bankAccount;

    public ConcurrentBank(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public synchronized void createAccount(Long accountId, Long balance) {
        for (int i = 0; i < bankAccount.getAccounts().size(); i++) {
            if (accountId == bankAccount.getAccounts().get(i).getAccountId()) {
                System.out.println("Аккаунт уже есть");
            } else {
                BankAccount account1 = new BankAccount(accountId, balance);
                bankAccount.getAccounts().add(account1);
            }
        }
    }

    public synchronized void transfer(Integer accountId1, Integer accountId2, Long amount) {
        BankAccount fromAccount = bankAccount.getAccounts().get(accountId1);
        BankAccount toAccount = bankAccount.getAccounts().get(accountId2);

        if (fromAccount == null || toAccount == null) {
            System.out.println("Один из аккаунтов не существует");
            return;
        }

        BankAccount firstLock = fromAccount.hashCode() < toAccount.hashCode() ? fromAccount : toAccount;
        BankAccount secondLock = firstLock == fromAccount ? toAccount : fromAccount;

        synchronized (firstLock) {
            synchronized (secondLock) {
                if (fromAccount.getBalance() >= amount) {
                    fromAccount.withdraw(fromAccount,amount);
                    toAccount.deposit(toAccount,amount);
                    System.out.println("Transferred " + amount + " from " + fromAccount + " to " + toAccount);
                } else {
                    System.out.println("Insufficient funds for transfer from " + fromAccount);
                }
            }
        }
    }

}
