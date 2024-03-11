package ru.inno.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AccountTest {

    @Test
    // проверка корректного значения, отрицательном значении сломается
    public void checkValue()
    {
        Account acc = new Account("Malichenko");
        acc.setValues(Currency.RUB, -60);
    }

    @Test
    // проверка корректности отмены
    void checkUndo() throws NothingToUndo {
        Account acc = new Account("ABCD");
        String oldName = acc.getName();
        acc.setName("QWERTY");
        acc.undo();
        Assertions.assertEquals(oldName, acc.getName());
    }

    @Test
    // проверка корректности загрузки состояния
    void checkLoad() throws RuntimeException {
        Account acc = new Account("ABCD");
        String oldName = acc.getName();
        Loadable qs1 = acc.Save();
        acc.setName("QWERTY");
        qs1.load();
        Assertions.assertEquals(oldName, acc.getName());
    }
}