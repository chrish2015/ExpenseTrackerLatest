package asc.msc.coursework.com.expensetracker.dto;

import java.io.Serializable;

import asc.msc.coursework.com.expensetracker.dao.Serializer;

/**
 *
 */
public class Category implements Serializable {

    private String categoryName;
    private double budget;

    public Category(String categoryName, double budget) {
        this.categoryName = categoryName;
        this.budget = budget;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}