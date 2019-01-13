package asc.msc.coursework.com.expensetracker.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * 
 */
public class Expense extends Transaction implements Serializable {

    private int categoryId;

    public Expense(String name, String comment, ArrayList<Integer> date, BigDecimal value, int categoryId, boolean isRecurring) {
        super(name, comment, date, value, isRecurring);
        this.categoryId =categoryId;
    }


    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}