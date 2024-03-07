package ru.inno.task;

public class Main {

    public static void main(String[] args) {
        System.out.println("Задание №1");

        Account account = new Account("Маличенко Татьяна Владимировна");
        System.out.println(account.getName());

        account.setName("Маличенко Павел Викторович");
        System.out.println(account.getName());

        account.setValues(Currency.USD, 40);
        account.setValues(Currency.RUB, 50);
        account.setValues(Currency.EUR, 10);
        account.doVoice();
        account.printValues();
        System.out.println("-------------------------");

        account.undo().undo();
        account.doVoice();
        account.printValues();
        System.out.println("-------------------------");

        account.setName("Маличенко Анастасия Павловна");
        account.setValues(Currency.RUB, 150);
        account.doVoice();
        account.printValues();
        System.out.println("-------------------------");

        account.undo().undo();
        account.doVoice();
        account.printValues();
        System.out.println("-------------------------");

        Loadable qs1 = account.Save();
        account.setName("Маличенко Дарья Павловна");
        account.setValues(Currency.RUB, 250);
        account.setValues(Currency.USD, 60);
        account.setValues(Currency.EUR, 130);
        account.doVoice();
        account.printValues();
        System.out.println("-------------------------");
        qs1.load();
        account.doVoice();
        account.printValues();
        System.out.println("-------------------------");
    }
}
