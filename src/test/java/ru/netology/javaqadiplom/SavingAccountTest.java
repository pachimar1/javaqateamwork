package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    @Test
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }

    @Test
    public void shouldPositivePay() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(1_000);

        Assertions.assertEquals(1_000, account.getBalance());
    }
    @Test
    public void shouldNegativePay() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(3_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }
    @Test
    public void shouldYearChangeToNegativeBalance() {
        SavingAccount account = new SavingAccount(
                -200,
                1_000,
                10_000,
                15
        );


        Assertions.assertEquals(0, account.yearChange());
    }
    @Test
    public void shouldYearChangeToPositiveBalance() {
        SavingAccount account = new SavingAccount(
                200,
                1_000,
                10_000,
                15
        );


        Assertions.assertEquals(30, account.yearChange());
    }
}
