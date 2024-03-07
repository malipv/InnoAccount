package ru.inno.task;

//import org.junit.Assert;
import org.junit.jupiter.api.Test;

class AccountTest {

    @Test
    void getName() {
    }
    @Test
    public void Test()
    {
        Account acc = new Account("Malichenko Pavel Victorovich");
        acc.setValues(Currency.RUB, 60);
        acc.setValues(Currency.USD, 10);
        acc.setName("Malichenko Tatiana Vladimirovna");
        acc.setValues(Currency.RUB, 80);
        acc.setValues(Currency.EUR, 20);
        Loadable qst1 = acc.Save();
        acc.undo();
    }
}