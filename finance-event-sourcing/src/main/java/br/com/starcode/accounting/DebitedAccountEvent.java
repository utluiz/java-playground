package br.com.starcode.accounting;

public class DebitedAccountEvent implements AccountEvent {

    private final String client;
    private final long value;

    public DebitedAccountEvent(final String client, final long value) {
        this.client = client;
        this.value = value;
    }

    @Override
    public void process(final AccountStore accountStore) {
        final Account current = accountStore.get(client);
        try {
            accountStore.save(current.debit(value));
        } catch (NoFundsException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void revert(final AccountStore accountStore) {
        final Account current = accountStore.get(client);
        accountStore.save(current.credit(value));
    }

}
