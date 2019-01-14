package asc.msc.coursework.com.expensetracker.modles;

import android.content.SharedPreferences;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import asc.msc.coursework.com.expensetracker.dto.Category;
import asc.msc.coursework.com.expensetracker.dto.Expense;
import asc.msc.coursework.com.expensetracker.dto.Income;
import asc.msc.coursework.com.expensetracker.dto.Transaction;

public class DataManipulation {

    public static final String CATEGORIES = "categories";
    public static final String TRANSACTIONS = "transactions";
    private SharedPreferences sharedPreferences;

    public DataManipulation(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void addTransaction(Transaction transaction) {
        ArrayList<Transaction> transactions = getTransactions();
        transactions.add(transaction);
        setToTransactions(transactions);
    }

    public void addTransaction(Transaction transaction, int i) {
        ArrayList<Transaction> transactions = getTransactions();
        transactions.set(i, transaction);
        setToTransactions(transactions);
    }

    public void addCategories(Category category) {
        ArrayList<Category> categories = getCategories();
        categories.add(category);
        setToCategories(categories);
    }

    /**
     * Generate the initial data for the first screen.
     * Check if there is a transaction variable in the preferences and
     * if not it will add default values to it.
     *
     * @return
     */
    public void dataInitialization() {
        String categories = getSerializedCatogories();
        String transactions = getSerializedTransactions();

        if (categories == null) {
            Category foodCategory = new Category("Food", new BigDecimal(10000.00));
            Category clothingsCategory = new Category("Clothing", new BigDecimal(10000.00));
            Category smallExpenses = new Category("Small Expenses", new BigDecimal(10000.00));
            ArrayList<Category> categoryList = new ArrayList<>(Arrays.asList(foodCategory, clothingsCategory, smallExpenses));
            setToCategories(categoryList);
        }
        if (transactions == null) {
            Transaction income = new Income("Example Income", "Salary", new ArrayList<Integer>(Arrays.asList(01, 00, 2019)), new BigDecimal("1000.00"), 0, false);
            Transaction expense = new Expense("Example Expense", "Salary", new ArrayList<Integer>(Arrays.asList(01, 00, 2019)), new BigDecimal("1000.00"), 0, false);
            ArrayList<Transaction> transactionArrayList = new ArrayList<>(Arrays.asList(income, expense));
            setToTransactions(transactionArrayList);
        }
    }

    private String getSerializedTransactions() {
        return sharedPreferences.getString(TRANSACTIONS, null);
    }

    private String getSerializedCatogories() {
        return sharedPreferences.getString(CATEGORIES, null);
    }


    public void setToCategories(ArrayList<Category> categories) {
        String categoryArrayListSerialized = Serializer.serializeObject(categories);
        sharedPreferences.edit().putString(CATEGORIES, categoryArrayListSerialized).apply();


    }

    public ArrayList<Category> getCategories() {
        String categories = getSerializedCatogories();
        ArrayList<Category> deserializeCategories = null;
        deserializeCategories = (ArrayList<Category>) Serializer.deserialize(categories);
        return deserializeCategories;
    }

    public void setToTransactions(ArrayList<Transaction> transactions) {
        String transactionArrayListSerialized = Serializer.serializeObject(transactions);
        sharedPreferences.edit().putString(TRANSACTIONS, transactionArrayListSerialized).apply();

    }

    public ArrayList<Transaction> getTransactions() {
        String transactions = getSerializedTransactions();
        ArrayList<Transaction> deserializeTransactions = null;
        deserializeTransactions = (ArrayList<Transaction>) Serializer.deserialize(transactions);
        return deserializeTransactions;
    }
}
