package asc.msc.coursework.com.expensetracker.categoryview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import asc.msc.coursework.com.expensetracker.MainActivity;
import asc.msc.coursework.com.expensetracker.R;
import asc.msc.coursework.com.expensetracker.dao.Serializer;
import asc.msc.coursework.com.expensetracker.dto.Category;
import asc.msc.coursework.com.expensetracker.dto.Transaction;
import asc.msc.coursework.com.expensetracker.expenselistview.ExpenseList;

public class CategoryItemFragment extends Fragment  implements ViewPager.OnPageChangeListener {

    View view;
    ViewPageAdapter viewPageAdapter;

    public CategoryItemFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.content_main, container, false);
        if(viewPageAdapter == null) {
            viewPageAdapter = new ViewPageAdapter(getChildFragmentManager());
        }
        Bundle arguments = getArguments();
        String transactionsString = arguments.getString("transactions");
        int categoryID = arguments.getInt("categoryID");

        ArrayList<Transaction> transactions = null;
        try {
            transactions = (ArrayList<Transaction>) Serializer.deserialize(transactionsString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FloatingActionButton viewById = view.findViewById(R.id.fab);
        TextView totalVal = view.findViewById(R.id.totalValue);
        TextView totalText = view.findViewById(R.id.totalText);
        totalText.setText("Remaining Budget  $");
        totalText.setTextSize(18);
        RecyclerView expenseListView = view.findViewById(R.id.expenseList);

        LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
        ArrayList<Category> categories = MainActivity.dataManipulation.getCategories();
        BigDecimal totalSpentValue = new BigDecimal(0);
        for (Transaction transaction : transactions) {
            BigDecimal value = transaction.getValue();
            totalSpentValue = totalSpentValue.add(value);
        }
        Category category = categories.get(categoryID);

        ExpenseList expenseList = new ExpenseList(view.getContext(), transactions, categories, (TextView) view.findViewById(R.id.totalValue));
        BigDecimal budget = category.getBudget();
        totalSpentValue = budget.subtract(totalSpentValue);
        totalVal.setText(totalSpentValue.toString());
        expenseListView.setLayoutManager(manager);
        expenseListView.setAdapter(expenseList);
        viewById.setVisibility(View.GONE);
        return view;
    }


    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
