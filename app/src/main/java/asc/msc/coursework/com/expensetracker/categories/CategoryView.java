package asc.msc.coursework.com.expensetracker.categories;

import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;

import asc.msc.coursework.com.expensetracker.MainActivity;
import asc.msc.coursework.com.expensetracker.dao.Serializer;
import asc.msc.coursework.com.expensetracker.dto.Category;
import asc.msc.coursework.com.expensetracker.dto.Expense;
import asc.msc.coursework.com.expensetracker.dto.Transaction;

public class CategoryView {


    public void createCategoryView(ViewPageAdapter viewPageAdapter) {
        ArrayList<Category> categories = MainActivity.dataManipulation.getCategories();
        ArrayList<Transaction> transactions = MainActivity.dataManipulation.getTransactions();
        int i=0;
        for(Category category:categories) {
            Bundle args = new Bundle();

            ArrayList<Transaction> transactionsForCategory=new ArrayList<>();
            CategoryItemFragment categoryItemFragment = new CategoryItemFragment();
            for(Transaction transaction:transactions){
                if(transaction instanceof Expense){
                    Expense expense= (Expense) transaction;
                    if(expense.getCategoryId() == i)
                        transactionsForCategory.add(transaction);
                }
            }
            String serializeObject ="";
            try {
                serializeObject  = Serializer.serializeObject(transactionsForCategory);
            } catch (IOException e) {
                e.printStackTrace();
            }
            args.putString("transactions", serializeObject);
            args.putInt("categoryID", i);
            i++;
            categoryItemFragment.  setArguments(args);
            viewPageAdapter.addFragment(categoryItemFragment, category.getCategoryName());

        }
    }

}
