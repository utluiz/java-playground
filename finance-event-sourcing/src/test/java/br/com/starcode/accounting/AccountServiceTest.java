package br.com.starcode.accounting;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AccountServiceTest {

    private static final String client = "Luiz";
    private AccountService accountService;

    @BeforeEach
    public void setup() {
        accountService = new AccountService(new AccountStore(), new AccountEventStore());
    }

    @Test
    public void intialCalance() {
        accountService.open(client);
        assertThat(accountService.balance(client), is(0L));
    }

    @Test
    public void deposit() {
        accountService.open(client);
        accountService.deposit(client, 100L);
        assertThat(accountService.balance(client), is(100L));
    }

    @Test
    public void depositAndWithdrawn() {
        accountService.open(client);
        accountService.deposit(client, 100L);
        accountService.withdrawn(client, 75L);
        assertThat(accountService.balance(client), is(25L));
    }

    @Test
    public void depositAndWithdrawnWithoutFunds() {
        Assertions.assertThrows(NoFundsException.class, () -> {});

        accountService.open(client);
        accountService.deposit(client, 100);
        accountService.withdrawn(client, 101);
    }

    @Test
    public void replayTransactionsOnAnotherAccountService() {
        accountService.open(client);
        accountService.deposit(client, 100L);
        accountService.withdrawn(client, 75L);

        final AccountService otherAccountService = new AccountService(new AccountStore(), new AccountEventStore());
        otherAccountService.replay(accountService);
        assertThat(otherAccountService.balance(client), is(25L));
    }

    @Test
    public void undoTransaction() {
        accountService.open(client);
        accountService.deposit(client, 100L);
        accountService.withdrawn(client, 25L);
        accountService.revert();
        assertThat(accountService.balance(client), is(100L));
    }

}
