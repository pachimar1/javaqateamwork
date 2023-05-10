package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test
    public void shouldAddToPositiveBalance() { // проверка на корректную работу метода "add" (пополнение счета)
        CreditAccount account = new CreditAccount(
                1,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(3_001, account.getBalance());
    }

    @Test
    public void depositAmountNegative1() { // проверка, что сумма пополнения отрицательное значение (меньше 0)
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        boolean actual = account.add(-1);
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void depositAmountNegative2() { // проверка, что сумма пополнения равна 0
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        boolean actual = account.add(0);
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkRateForNegativeValue() {  // проверка на поломку программы при условии отрицательного значения "rate"
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    0,
                    5_000,
                    -15
            );
        });
    }

    @Test
    public void checkInitialBalanceForNegativeValue() {  // проверка на поломку программы при условии отрицательного значения "initialBalance"
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    -5_000,
                    5_000,
                    15
            );
        });
    }

    @Test
    public void checkCreditLimitForNegativeValue() {  // проверка на поломку программы при условии отрицательного значения "creditLimit"
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    5_000,
                    -5_000,
                    15
            );
        });
    }

    @Test
    public void balanceChangedAfterPurchase() { // проверка корректного выполнения метода "pay"
        CreditAccount account = new CreditAccount(
                1000,
                5_000,
                15
        );
        boolean actual = false;
        account.pay(100);
        if (account.getBalance() == 900) {
            actual = true;
        }
        ;
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void afterPurchaseBalanceBecameIrrelevant() { // проверка, что баланс (temporaryBalance) после вычета суммы покупки (amount) будет меньше кредитного лемита (creditLimit)
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        boolean actual = account.pay(10_000);
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void purchaseAmountNegative() { // проверка, что сумма покупки отрицательное значение (меньше 0)
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        boolean actual = account.pay(-1);
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void BalanceValuesPositive() { // проверка, при условии положительного баланса
        CreditAccount account = new CreditAccount(
                200,
                5_000,
                15
        );

        int actual = account.yearChange();
        int expected = 0;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkingRegularOperationOfMethodYearChange() { // проверка корректности работы метода yearChange
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );
        account.pay(1_000);
        int actual = account.yearChange();
        int expected = -150;
        Assertions.assertEquals(expected, actual);
    }


}
