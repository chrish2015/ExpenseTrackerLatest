package asc.msc.coursework.com.expensetracker.categories;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import asc.msc.coursework.com.expensetracker.MainActivity;
import asc.msc.coursework.com.expensetracker.R;
import asc.msc.coursework.com.expensetracker.dao.Serializer;
import asc.msc.coursework.com.expensetracker.dto.Transaction;
import asc.msc.coursework.com.expensetracker.expenselist.ExpenseList;

public class CategoryItemFragment extends Fragment {

    View view;
    public CategoryItemFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.content_main,container,false);
        Bundle arguments = getArguments();
        String transactionsString = arguments.getString("transactions");

        ArrayList<Transaction> transactions = null;
        try {
            transactions = (ArrayList<Transaction>) Serializer.deserialize(transactionsString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FloatingActionButton viewById = (FloatingActionButton) view.findViewById(R.id.fab);
        RecyclerView expenseListView = (RecyclerView) view.findViewById(R.id.expenseList);

        LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
        ExpenseList expenseList = new ExpenseList(view.getContext(), transactions, MainActivity.dataManipulation.getCategories());
        expenseListView.setLayoutManager(manager);
        expenseListView.setAdapter(expenseList);
        viewById.setVisibility(View.GONE);
        return view;
    }
}
