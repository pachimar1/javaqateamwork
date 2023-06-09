package ru.netology.javaqadiplom;

/**
 * Кредитный счёт
 * Может иметь баланс вплоть до отрицательного, но до указанного кредитного лимита.
 * Имеет ставку - количество процентов годовых на сумму на балансе, если она меньше нуля.
 * При создании, баланс кредитного счёта изначально выставляется в кредитный лимит.
 */
public class CreditAccount extends Account {
    protected int creditLimit;


    /**
     * Создаёт новый объект кредитного счёта с заданными параметрами.
     * Если параметры некорректны (кредитный лимит отрицательный и так далее), то
     * должно выкидываться исключения вида IllegalArgumentException.
     *
     * @param initialBalance - неотрицательное число, начальный баланс для счёта
     * @param creditLimit    - неотрицательное число, максимальная сумма которую можно задолжать банку
     * @param rate           - неотрицательное число, ставка кредитования для расчёта долга за отрицательный баланс
     */
    public CreditAccount(int initialBalance, int creditLimit, int rate) {
        // Андрей Ц.  потребуется 2 дополнительных исключения для initialBalance и creditLimit
        if (rate <= 0) {
            throw new IllegalArgumentException(
                    "Накопительная ставка не может быть отрицательной, а у вас: " + rate
            );
        }
        if (creditLimit <= 0) {
            throw new IllegalArgumentException(
                    "Кредитный лимит не может быть отрицательной, а у вас: " + creditLimit
            );
        }
        if (initialBalance < 0) {
            throw new IllegalArgumentException(
                    "Текущий баланс не может быть отрицательной, а у вас: " + initialBalance
            );
        }
        this.balance = initialBalance;
        this.creditLimit = creditLimit;
        this.rate = rate;
    }

    /**
     * Операция оплаты с карты на указанную сумму.
     * В результате успешного вызова этого метода, баланс должен уменьшиться
     * на сумму покупки. Если же операция может привести к некорректному
     * состоянию счёта (например, баланс может уйти в минус), то операция должна
     * завершиться вернув false и ничего не поменяв на счёте.
     *
     * @param amount - сумма покупки
     * @return true если операция прошла успешно, false иначе.
     */
//    @Override
//    public boolean pay(int amount) {
//        if (amount <= 0) {
//            return false;
//        }
//        balance = balance - amount;  // рекомендую здесь создать новую переменную для оценки состояния баланса после вычета суммы покупки (например int temporaryBalance = balance - amount)
//        if (balance > -creditLimit) { // далее temporaryBalance сравниваем с -creditLimit (с возможным долгом)
//            balance = -amount;  // в случае успеха в этой строке сумма покупки должна вычитаться из баланса, т.е. balance = balance - amount или balance -= amount (обратить внимание на последовательность "-" и "=")
//            return true;
//        } else {
//            return false;
//        }
//    }
    @Override
    public boolean pay(int amount) {
        if (amount <= 0) {
            return false;
        }
        if (balance - amount < -creditLimit) {
            return false;
        }
        balance -= amount;
        return true;
    }

    /**
     * Операция пополнения карты на указанную сумму.
     * В результате успешного вызова этого метода, баланс должен увеличиться
     * на сумму покупки *(опечатка - сумма пополнения). Если же операция может привести к некорректному
     * состоянию счёта, то операция должна
     * завершиться вернув false и ничего не поменяв на счёте.
     *
     * @param amount - сумма пополнения
     * @param amount *(-?)
     * @return true если операция прошла успешно, false иначе.
     * @return *(-?)
     */
    @Override
    public boolean add(int amount) {
        if (amount <= 0) {
            return false;
        }
        balance += amount; // нет действия пополнения счета. должно быть balance += amount
        return true;
    }

    /**
     * Операция расчёта процентов на отрицательный баланс счёта при условии, что
     * счёт не будет меняться год. Сумма процентов приводится к целому
     * числу через отбрасывание дробной части (так и работает целочисленное деление).
     * Пример: если на счёте -200 рублей, то при ставке 15% ответ должен быть -30.
     * Пример 2: если на счёте 200 рублей, то при любой ставке ответ должен быть 0.
     *
     * @return
     */
    @Override

    public int yearChange() {
        if (balance >= 0) {
            return 0;
        }
        return balance / 100 * rate;
        // тут вообще необходимо внедрить логику с if.
        // При проверке баланса, если balance >= 0, то всегда возвращать 0.
        // Если нет, то уже выполнять выше описанное действие
    }

    public int getCreditLimit() {
        return creditLimit;
    }

}