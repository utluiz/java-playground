package br.com.starcode.accounting;

public class Account {

    private final String client;
    private final long balance;

    public Account(final String client, final long balance) {
        this.client = client;
        this.balance = balance;
    }

    public String client() {
        return client;
    }

    public long balance() {
        return balance;
    }

    public Account credit(final long creditValue) {
        return new Account(client, balance + creditValue);
    }

    public Account debit(final long debitValue) throws NoFundsException {
        if (balance < debitValue) {
            throw new NoFundsException();
        }
        return new Account(client, balance - debitValue);
    }

}
