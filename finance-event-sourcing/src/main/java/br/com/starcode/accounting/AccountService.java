package br.com.starcode.accounting;

public class AccountService {

    private final AccountStore accountStore;
    private final AccountEventStore accountEventStore;

    public AccountService(final AccountStore accountStore, final AccountEventStore accountEventStore) {
        this.accountStore = accountStore;
        this.accountEventStore = accountEventStore;
    }

    public void open(final String client) {
        final OpenAccountEvent event = new OpenAccountEvent(client);
        accountEventStore.process(event, accountStore);
    }

    public void deposit(final String client, final long value) {
        final CreditedAccountEvent event = new CreditedAccountEvent(client, value);
        accountEventStore.process(event, accountStore);
    }

    public void withdrawn(final String client, final long value) {
        final DebitedAccountEvent event = new DebitedAccountEvent(client, value);
        accountEventStore.process(event, accountStore);
    }

    public long balance(final String client) {
        return accountStore.get(client).balance();
    }

    public void replay(final AccountService another) {
        accountEventStore.replay(another.accountEventStore, accountStore);
    }

    public void revert() {
        accountEventStore.revert(accountStore);
    }

}
