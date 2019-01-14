package asc.msc.coursework.com.expensetracker.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * 
 */
public class Income extends Transaction implements Serializable {
    public Income(String name, String comment, ArrayList<Integer> date, BigDecimal value, boolean isRecurring) {
        super(name, comment, date, value, isRecurring);
    }
}