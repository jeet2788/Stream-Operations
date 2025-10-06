package com.stream.example;

import java.util.*;
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

        float balanceBelow1000 = totalBalanceBelow1000(accList);
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

        Optional<Account> maxBalanceForGivenPerson = maxAccountBalanceForGivenPerson(accList,"J");
        if(maxBalanceForGivenPerson.isPresent()){
            maxBalanceForGivenPerson.ifPresent(System.out::println);
        }
        long count = countNumberOfAccountForAPerson(accList, "J");
        System.out.println("Number of accounts for J: " + count);

        List<Float> balances = extractAndCollectBalance(accList);
        System.out.println("All balances: " + balances);

        List<String> accountStrings = transformAccountsToString(accList);
        accountStrings.forEach(System.out::println);

        DoubleSummaryStatistics stats = collectBalanceStatistics(accList);

        System.out.println("Count: " + stats.getCount());
        System.out.println("Sum: " + stats.getSum());
        System.out.println("Average: " + stats.getAverage());
        System.out.println("Max: " + stats.getMax());
        System.out.println("Min: " + stats.getMin());

        List<Account> sortedAsc = sortAccountByBalanceAscending(accList);
        System.out.println("Ascending: " + sortedAsc);

        List<Account> sortedDesc = sortAccountByBalanceDescending(accList);
        System.out.println("Descending: " + sortedDesc);

        List<Account> sortedAccounts = sortAccountByLastNameThenFirstName(accList);
        sortedAccounts.forEach(System.out::println);


    }
    /***************************************  Filtering and Summing ************************************ */

    /**
     *  This method returns the total account balance for  a given person
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
     * sum total balance and return which are less than 1000
     * @param accList
     * @return
     */
    private static float  totalBalanceBelow1000(List<Account> accList){
        return (float) accList.stream().filter(e->e.getBalance()<1000).mapToDouble(Account::getBalance).sum();
    }

    /**
     *
     * @param accList
     * @param fName
     * @param lName
     * @return
     */
    private static float sumOfBalanceByName(List<Account> accList, String fName, String lName){
        return (float) accList.stream().filter(e->e.getFirstName().startsWith("J") && e.getLastName().startsWith("J")).mapToDouble(Account::getBalance).sum();
    }

    /*************************************   Distinct and Grouping  *******************************************************/

    /**
     * List all distinct (firstName, lastName) combinations.
     */
    /**
     *
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
    /**
     * Find an account with the maximum or minimum balance for a given person
     */
    private static Optional<Account> maxAccountBalanceForGivenPerson(List<Account> accList, String firstName){
        return accList.stream().filter(e->e.getFirstName().equals(firstName))
                .max(Comparator.comparingDouble(Account::getBalance));
    }
    /**
     * Count the number of accounts for a specific person.
     */
    private static long countNumberOfAccountForAPerson(List<Account> accList,String fName){
        return accList.stream().filter(e->e.getFirstName().equals(fName)).count();
    }

    /*********************************** Mapping and Transformation *************************************************/

    /**
     * Extract and collect all account balances into a list.
     */

    private static List<Float> extractAndCollectBalance(List<Account> accountList){
        return accountList.stream().map(Account::getBalance).collect(Collectors.toList());
    }

    /**
     * Transform account objects into strings like "FirstName LastName: Balance".
     */
    private static  List<String> transformAccountsToString(List<Account> accList){
        return accList.stream().map(e->e.getFirstName() +" "+e.getLastName() +" "+e.getBalance()).collect(Collectors.toList());
    }
    /**
     * Convert balances to double and collect statistics (average, max, min, sum, count)
     */
    private static DoubleSummaryStatistics collectBalanceStatistics(List<Account> accountList) {
        return accountList.stream()
                .mapToDouble(Account::getBalance) // converts float to double automatically
                .summaryStatistics(); // collects count, sum, min, max, average
    }

    /*************************************** Sorting *************************************************************/

    /**
     * Sort accounts by balance ascending .
     */
    private static  List<Account> sortAccountByBalanceAscending(List<Account> accList){
        return accList.stream().sorted(Comparator.comparingDouble(Account::getBalance)).collect(Collectors.toList());
    }

    /**
     * Sort accounts by balance descending.
     */
    private static  List<Account> sortAccountByBalanceDescending(List<Account> accList){
        return accList.stream().sorted(Comparator.comparingDouble(Account::getBalance).reversed()).collect(Collectors.toList());
    }

    /**
     * Sort accounts by last name, then first name.
     */
    private static List<Account> sortAccountByLastNameThenFirstName(List<Account> accList){
        return accList.stream().sorted(Comparator.comparing(Account::getLastName).thenComparing(Account::getFirstName))
                .collect(Collectors.toList());
    }

    /************************************* Combining and Collecting ************************************************/

}

class Account {
    private String firstName;
    private String lastName;

    @Override
    public String toString() {
        return "Account{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", balance=" + balance +
                '}';
    }

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


