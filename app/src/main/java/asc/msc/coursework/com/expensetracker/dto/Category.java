package asc.msc.coursework.com.expensetracker.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 */
public class Category implements Serializable {

    private String categoryName;
    private BigDecimal budget;

    public Category(String categoryName, BigDecimal budget) {
        this.categoryName = categoryName;
        this.budget = budget;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }
}