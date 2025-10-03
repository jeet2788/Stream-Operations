package com.stream.example;

import java.util.ArrayList;
import java.util.List;

public class AccountBalanceOps {
    public static void main(String[] args) {
        Account a1 = new Account("J", "C", 90.7f);
        Account a4 = new Account("J", "C", 290.7f);
        Account a2 = new Account("B", "N", 60.7f);
        Account a3 = new Account("C", "K", 1900.7f);

        List<Account> accList = new ArrayList<>();
        accList.add(a1);
        accList.add(a2);
        accList.add(a3);
        accList.add(a4);

        float total = totalAccBalByPerson(accList, "J", "C");
        System.out.println(" Total balance for J C: " + total);

        float balanceBelow1000 = totalBalaceBelow1000(accList);
        System.out.println(" Balance Below 1000 "+balanceBelow1000);

        float byName = sumOfBalanceByName(accList, "J", "C");
        System.out.println("Starts By Name : "+byName);
    }

    private static float totalAccBalByPerson(List<Account> accList, String fName, String lName) {
        return (float)
                accList.stream()
                        .filter(e->e.getFirstName().equals(fName) && e.getLastName().equals(lName))
                        .mapToDouble(Account::getBalance)
                        .sum();
    }
    private static float  totalBalaceBelow1000(List<Account> accList){
        return (float) accList.stream().filter(e->e.getBalance()<1000).mapToDouble(Account::getBalance).sum();
    }

    private static float sumOfBalanceByName(List<Account> accList, String fName, String lName){
        return (float) accList.stream().filter(e->e.getFirstName().startsWith("J") && e.getLastName().startsWith("J")).mapToDouble(Account::getBalance).sum();
    }
}

class Account {
    private String firstName;
    private String lastName;
    private float balance;

    public Account(String firstName, String lastName, float balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public float getBalance() {
        return balance;
    }
}


