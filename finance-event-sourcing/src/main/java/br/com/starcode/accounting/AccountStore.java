package br.com.starcode.accounting;

import java.util.HashMap;
import java.util.Map;

public class AccountStore {

    private final Map<String, Account> contas = new HashMap<>();

    public Account get(final String cliente) {
        return contas.get(cliente);
    }

    public void save(final Account account) {
        contas.put(account.client(), account);
    }

    public void remove(final Account account) {
        contas.remove(account.client());
    }

}
