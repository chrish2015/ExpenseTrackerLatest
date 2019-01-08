package asc.msc.coursework.com.expensetracker.categories;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import java.io.IOException;
import java.util.ArrayList;

import asc.msc.coursework.com.expensetracker.MainActivity;
import asc.msc.coursework.com.expensetracker.R;
import asc.msc.coursework.com.expensetracker.addexpense.AddExpenseDialog;
import asc.msc.coursework.com.expensetracker.dao.DataManipulation;
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
            i++;
            categoryItemFragment.setArguments(args);

            viewPageAdapter.addFragment(categoryItemFragment, category.getCategoryName());

        }
    }

}
