package asc.msc.coursework.com.expensetracker.dto;

import java.util.Date;

/**
 * 
 */
public class Account {

    /**
     * Default constructor
     */
    public Account() {
    }

    /**
     * 
     */
    private String type;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private Double initialBalance;

    /**
     * 
     */
    private Date asof;

    /**
     *
     * @param type
     * @param name
     * @param balance
     * @param asofDate
     */
    public void addAccount(String type, String name, Double balance, Date asofDate) {
        // TODO implement here
    }

    /**
     *
     * @param account
     */
    public void deleteAccount(Account account) {
        // TODO implement here
    }

    /**
     *
     * @param updatableValue
     */
    public void updateAccount(Account updatableValue) {
        // TODO implement here
    }

    /**
     * @return
     */
    public Account viewAccount() {
        // TODO implement here
        return null;
    }

}