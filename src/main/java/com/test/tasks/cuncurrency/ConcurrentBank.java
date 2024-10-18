package com.test.tasks.cuncurrency;

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

    public synchronized void transfer(BankAccount account1, BankAccount account2, Double amount) {
        BankAccount from = bankAccount.getAccounts().get(fromAccount);
        BankAccount to = accounts.get(toAccount);

        if (from == null || to == null) {
            System.out.println("One of the accounts does not exist.");
            return;
        }

        BankAccount firstLock = from.hashCode() < to.hashCode() ? from : to;
        BankAccount secondLock = firstLock == from ? to : from;

        synchronized (firstLock) {
            synchronized (secondLock) {
                if (from.getBalance() >= amount) {
                    from.withdraw(amount);
                    to.deposit(amount);
                    System.out.println("Transferred " + amount + " from " + fromAccount + " to " + toAccount);
                } else {
                    System.out.println("Insufficient funds for transfer from " + fromAccount);
                }
            }
        }
    }
    }
}
