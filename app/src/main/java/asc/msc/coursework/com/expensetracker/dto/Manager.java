package asc.msc.coursework.com.expensetracker.dto;

import java.util.List;
import java.util.Date;

/**
 * 
 */
public class Manager {

    /**
     * Default constructor
     */
    public Manager() {
    }

    /**
     * 
     */
    private List<Expense> expensesList;

    /**
     * 
     */
    private List<Income> incomeList;


    /**
     *
     * @param name
     * @param value
     * @param datetime
     */
    public void addExpenses(String name, Double value, Date datetime) {
        // TODO implement here
    }

    /**
     *
     * @param name
     * @param value
     * @param datetime
     */
    public void addIncome(String name, Double value, Date datetime) {
        // TODO implement here
    }

    /**
     *
     * @param name
     * @param value
     * @param date
     * @param period
     * @param endDate
     */
    public void addRecrringExpenses(String name, Double value, Date date, String period, Date endDate) {
        // TODO implement here
    }

    /**
     *
     * @param name
     * @param value
     * @param date
     * @param period
     * @param endDate
     */
    public void addRecurringIncome(String name, Double value, Date date,String period, Date endDate) {
        // TODO implement here
    }

    /**
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public List<Income> getIncomeInPeriod(Date startDate, Date endDate) {
        // TODO implement here
        return null;
    }

    /**
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public List<Expense> getExpensesInPeriod(Date startDate, Date endDate) {
        // TODO implement here
        return null;
    }

}