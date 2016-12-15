package br.com.starcode.accounting;

public class OpenAccountEvent implements AccountEvent {

    private final String client;
    private final Account account;

    public OpenAccountEvent(final String client) {
        this.client = client;
        this.account = new Account(client, 0);
    }

    @Override
    public void process(final AccountStore accountStore) {
        if (accountStore.get(client) != null) {
            throw new IllegalArgumentException("Client already exists!");
        }
        accountStore.save(account);
    }

    @Override
    public void revert(final AccountStore accountStore) {
        accountStore.remove(account);
    }

}
