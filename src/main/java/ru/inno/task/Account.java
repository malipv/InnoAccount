package ru.inno.task;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

interface Loadable
{
    void load();
}

class NothingToUndo extends RuntimeException{}

enum Currency {RUB, USD, EUR}

interface Command {
    public void perform();
}

public class Account {

    public Loadable Save() {return new Snapshot();}

    private class Snapshot implements Loadable {
        private String name;
        private HashMap<Currency, Integer> values;

        public Snapshot()
        {
            this.name = Account.this.name;
            this.values = new HashMap<>(Account.this.values);
        }
        @Override
        public void load() {
            Account.this.name = this.name;
            Account.this.values = new HashMap<>(this.values);
        }
    }

    private Deque<Command> commands = new ArrayDeque<>();

    private Account(){};

    public Account undo() throws NothingToUndo {
        if (commands.isEmpty()) throw new NothingToUndo();
        commands.pop().perform();
        return this;
    }

    public Account(String name) {
        this.setName(name);
        this.values = new HashMap<>();
    }

    private String name;

    public String getName(){
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException();
        String oldName = this.name;
        this.commands.push(()->{this.name = oldName;});
        this.name = name;
    }

    public void doVoice()
    {
        System.out.println("\nI'm " + this.name);
    }

    private HashMap<Currency, Integer> values;

    public HashMap<Currency, Integer> getValues() {
        return new HashMap<Currency, Integer>(this.values);
    }

    public void setValues(Currency cur, Integer val) {
        if (val < 0) throw new IllegalArgumentException();

        if (values.containsKey(cur)) //если мы изменили существующее значение
        {
            this.commands.push(()->{this.values.put(cur, val);});
        }
        else //если мы добавили новое значение
        {
            this.commands.push(()->{this.values.remove(cur);});
        }
        this.values.put(cur, val);
    }

    public void printValues() {
        this.values.values().stream().forEach(System.out::println);
    }
}