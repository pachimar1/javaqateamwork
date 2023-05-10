package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {
    
    @Test
    public void shouldAddEqualsThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                3_000,
                1_000,
                10_000,
                5
        );

        account.add(7_000);

        Assertions.assertEquals(2_000 + 8_000, account.getBalance());
    }

    @Test
    public void shouldAddEqualsThanMinBalance() {
        SavingAccount account = new SavingAccount(
                1_000,
                1_000,
                10_000,
                5
        );

        account.add(0);

        Assertions.assertEquals(1_000, account.getBalance());
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
    public void shouldYearChangeToPositiveBalance() {
        SavingAccount account = new SavingAccount(
                2000,
                1_000,
                10_000,
                15
        );


        Assertions.assertEquals(300, account.yearChange());
    }

    @Test
    public void checkRateForNegativeValue() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    200,
                    1_000,
                    10_000,
                    -15
            );
        });
    }

    @Test
    public void checkMinBalanceForNegativeValue() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    200,
                    -1_000,
                    10_000,
                    15
            );
        });
    }

    @Test
    public void checkMaxBalanceForNegativeValue() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    200,
                    1_000,
                    -10_000,
                    15
            );
        });
    }

    @Test
    public void checkMinBalanceMoreMaxBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    200,
                    5_000,
                    2_000,
                    15
            );
        });
    }
}

