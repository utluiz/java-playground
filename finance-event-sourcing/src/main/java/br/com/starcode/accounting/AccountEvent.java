package br.com.starcode.accounting;

public interface AccountEvent {

    void process(AccountStore accountStore);

    void revert(AccountStore accountStore);

}
