package br.com.starcode.accounting;

import java.util.Deque;
import java.util.LinkedList;

public class AccountEventStore {

    private final Deque<AccountEvent> eventQueue = new LinkedList<>();

    public void process(final AccountEvent event, final AccountStore accountStore) {
        eventQueue.add(event);
        event.process(accountStore);
    }

    public void revert(final AccountStore accountStore) {
        final AccountEvent lastEvent = eventQueue.removeLast();
        lastEvent.revert(accountStore);
    }

    public void replay(final AccountEventStore another, final AccountStore accountStore) {
        another.eventQueue.forEach(event -> process(event, accountStore));
    }

}
