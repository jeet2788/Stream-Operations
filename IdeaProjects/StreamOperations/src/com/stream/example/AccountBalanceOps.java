package com.stream.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class AccountBalanceOps {

    public static void main(String[] args) {
        Account a1 = new Account("J", "C", 90.7f);
        Account a4 = new Account("J", "C", 290.7f);
        Account a2 = new Account("B", "N", 60.7f);
        Account a3 = new Account("C", "K", 1900.7f);
        Account a5 = new Account("B", "N", 60.7f);

        List<Account> accList = new ArrayList<>();
        accList.add(a1);
        accList.add(a2);
        accList.add(a3);
        accList.add(a4);
        accList.add(a5);

        float total = totalAccBalByPerson(accList, "J", "C");
        System.out.println(" Total balance for J C: " + total);

        float balanceBelow1000 = totalBalaceBelow1000(accList);
        System.out.println(" Balance Below 1000 "+balanceBelow1000);

        float byName = sumOfBalanceByName(accList, "J", "C");
        System.out.println("Starts By Name : "+byName);

        List<Account> distinctAccounts = listDistictfNamelNameCombination(accList);
        System.out.println("Distinct Accounts (by Name): " + distinctAccounts.size());

        Map<String,Double> groupNameAndSum = groupAccountByfNameOrlNameAndSum(accList);
        groupNameAndSum.forEach((firstName,totalBalance)->{
            System.out.println("First Name: " + firstName + " => Total Balance: " + totalBalance);
        });

        Map<String,Long> groupNameAndCount = groupAccountByFirstNameAndCount(accList);
        groupNameAndCount.forEach((firstName,count)->{
            System.out.println("First Name :"+firstName + " "+"Count :"+count);
        });
    }
    /*
     *  Filtering and Summing
     */


    /**
     * @param accList
     * @param fName
     * @param lName
     * @return
     */
    private static float totalAccBalByPerson(List<Account> accList, String fName, String lName) {
        return (float)
                accList.stream()
                        .filter(e->e.getFirstName().equals(fName) && e.getLastName().equals(lName))
                        .mapToDouble(Account::getBalance)
                        .sum();
    }

    /**
     * @param accList
     * @return
     */
    private static float  totalBalaceBelow1000(List<Account> accList){
        return (float) accList.stream().filter(e->e.getBalance()<1000).mapToDouble(Account::getBalance).sum();
    }

    /**
     * @param accList
     * @param fName
     * @param lName
     * @return
     */
    private static float sumOfBalanceByName(List<Account> accList, String fName, String lName){
        return (float) accList.stream().filter(e->e.getFirstName().startsWith("J") && e.getLastName().startsWith("J")).mapToDouble(Account::getBalance).sum();
    }

    /*
    Distinct and Grouping
    */


    /**
     * List all distinct (firstName, lastName) combinations.
     */

    /**
     * @param accList
     * @return
     */
    private static List<Account> listDistictfNamelNameCombination(List<Account> accList){
        return accList.stream().distinct().collect(Collectors.toList());
    }

    /**
     * Group accounts by first name or last name and calculate sum  per group.
     */
    private static Map<String,Double> groupAccountByfNameOrlNameAndSum(List<Account> accList) {
        return  accList.stream().collect(Collectors.groupingBy(Account::getFirstName,
                Collectors.summingDouble(Account::getBalance)
        ));
    }

    /**
     * Group accounts by first name or last name and calculate  count per group.
     */
    private static Map<String, Long> groupAccountByFirstNameAndCount(List<Account> accList) {
        return accList.stream().collect(Collectors.groupingBy(Account::getFirstName,
                Collectors.counting()
        ));
    }

}

class Account {
    private String firstName;
    private String lastName;
    private float balance;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Float.compare(balance, account.balance) == 0 && Objects.equals(firstName, account.firstName) && Objects.equals(lastName, account.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, balance);
    }

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


