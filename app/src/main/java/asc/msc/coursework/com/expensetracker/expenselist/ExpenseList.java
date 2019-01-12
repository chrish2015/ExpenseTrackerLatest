package asc.msc.coursework.com.expensetracker.expenselist;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.transition.TransitionManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import asc.msc.coursework.com.expensetracker.MainActivity;
import asc.msc.coursework.com.expensetracker.R;
import asc.msc.coursework.com.expensetracker.addexpense.AddExpenseDialog;
import asc.msc.coursework.com.expensetracker.dao.DataManipulation;
import asc.msc.coursework.com.expensetracker.dto.Category;
import asc.msc.coursework.com.expensetracker.dto.Expense;
import asc.msc.coursework.com.expensetracker.dto.Income;
import asc.msc.coursework.com.expensetracker.dto.Transaction;
import asc.msc.coursework.com.expensetracker.util.Util;

public class ExpenseList extends RecyclerView.Adapter<ExpenseList.ListHolder> {

    private final LayoutInflater layoutInflater;
    private final Context context;
    private ArrayList<Transaction> arrayList;
    private ArrayList<Category> categories;
    BigDecimal total = new BigDecimal(0);
    private int mExpandedPosition = -1;
    private Util util = new Util();
    TextView totalView;


    public ExpenseList(Context context, ArrayList<Transaction> list, ArrayList<Category> categories, TextView totalView) {
        this.context = context;
        this.totalView = totalView;
        this.setCategories(categories);
        this.setArrayList(list);
        layoutInflater = LayoutInflater.from(context);
    }

    private void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    /**
     * Called every time when the TextView area is generated for the recycle view.
     *
     * @param viewGroup
     * @param i
     * @return
     */
    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.expense_list, viewGroup, false);
        ListHolder listHolder = new ListHolder(view);
        return listHolder;
    }

    /**
     * Called when data is populated to the View Holders.
     *
     * @param listHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull final ListHolder listHolder, final int i) {
        final Transaction transaction = arrayList.get(i);

        BigDecimal value = new BigDecimal(transaction.getValue().toString());
        total.add(value);
        listHolder.value.setText(String.valueOf(value));
        if(!MainActivity.isExpenseView){
            listHolder.edit.setVisibility(View.GONE);
            listHolder.delete.setVisibility(View.GONE);
        }


        final boolean isExpanded = i == mExpandedPosition;
        if (isExpanded) {
            listHolder.contentTxt.setText("Name    : " + transaction.getName());
            listHolder.dateTxt.setText("Date           : " + util.getDateString(transaction.getDate()));
            listHolder.commentTxtView.setText("Comment  : " + transaction.getComment());
            listHolder.value.setTypeface(listHolder.value.getTypeface(), Typeface.BOLD_ITALIC);

        } else {
            listHolder.contentTxt.setText(transaction.getName());
            listHolder.dateTxt.setText(util.getDateString(transaction.getDate()));
            listHolder.commentTxtView.setText(transaction.getComment());
            listHolder.linearLayoutMain.setBackground(ContextCompat.getDrawable(context, R.drawable.expense_list_click));
            listHolder.value.setTypeface(null, Typeface.BOLD);

        }

        if (transaction instanceof Expense) {
            listHolder.value.setTextColor(Color.RED);
            Expense expense = (Expense) transaction;
            if (isExpanded) {
                listHolder.linearLayoutMain.setBackground(ContextCompat.getDrawable(context, R.drawable.expense_list_clicked_red));
                listHolder.categoryTxtView.setText("Category   : " + categories.get(expense.getCategoryId()).getCategoryName());
            }
        } else if (transaction instanceof Income) {
            listHolder.value.setTextColor(Color.GREEN);
            Income income = (Income) transaction;
            if (isExpanded) {
                listHolder.linearLayoutMain.setBackground(ContextCompat.getDrawable(context, R.drawable.expense_list_clicked_green));
                listHolder.categoryTxtView.setText("Source       : " + categories.get(income.getSourceId()).getCategoryName());
            }

        }
        listHolder.hiddenLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        listHolder.itemView.setActivated(isExpanded);
        listHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpandedPosition = isExpanded ? -1 : i;
                TransitionManager.beginDelayedTransition(MainActivity.expenseListView);
                notifyDataSetChanged();
            }
        });

        listHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.remove(i);
                DataManipulation dataManipulation = new DataManipulation();
                dataManipulation.setToTransactions(arrayList);
                notifyDataSetChanged();
                setArrayList(arrayList);
            }
        });

        listHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transaction transaction1 = arrayList.get(i);

                Bundle args = new Bundle();
                args.putInt(AddExpenseDialog.POSITION, i);
                args.putString(AddExpenseDialog.NAME, transaction1.getName());
                args.putString(AddExpenseDialog.COMMENT, transaction1.getComment());
                args.putIntegerArrayList(AddExpenseDialog.DATE, transaction.getDate());
                args.putString(AddExpenseDialog.VLAUE, transaction1.getValue().toString());
                if (transaction instanceof Expense) {
                    int categoryId = ((Expense) transaction1).getCategoryId();
                    args.putInt(AddExpenseDialog.CATEGORY, ((Expense) transaction1).getCategoryId());
                    args.putInt(AddExpenseDialog.SOURCE, -1);

                } else {
                    int categoryId = ((Income) transaction1).getSourceId();
                    args.putInt(AddExpenseDialog.SOURCE, ((Income) transaction1).getSourceId());
                    args.putInt(AddExpenseDialog.CATEGORY, -1);


                }

                AddExpenseDialog addExpenseDialog = new AddExpenseDialog();

                addExpenseDialog.setArguments(args);
                addExpenseDialog.show(MainActivity.supportFragmentManager, "AddExpenses");
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void setArrayList(ArrayList<Transaction> arrayList) {
        this.arrayList = arrayList;
        total = new BigDecimal(0);
        for (Transaction transaction : arrayList) {
            if (transaction instanceof Income)
                total = total.add(transaction.getValue());
            else
                total = total.subtract(transaction.getValue());
        }
        totalView.setText(total.toString());

    }

    public static class ListHolder extends RecyclerView.ViewHolder {
        TextView contentTxt;
        TextView dateTxt;
        TextView value;
        TextView totalValue;
        TextView commentTxtView;
        TextView categoryTxtView;
        LinearLayout hiddenLayout;
        LinearLayout linearLayoutMain;
        ImageButton delete;
        ImageButton edit;

        public ListHolder(@NonNull View itemView) {
            super(itemView);
            contentTxt = (TextView) itemView.findViewById(R.id.contentTxt);
            dateTxt = (TextView) itemView.findViewById(R.id.dateTxt);
            value = (TextView) itemView.findViewById(R.id.value);
            totalValue = itemView.findViewById(R.id.totalValue);
            commentTxtView = itemView.findViewById(R.id.commentTxtView);
            categoryTxtView = itemView.findViewById(R.id.categoryTxtView);
            hiddenLayout = itemView.findViewById(R.id.hiddenLayout);
            linearLayoutMain = itemView.findViewById(R.id.linearLayoutMain);
            delete = itemView.findViewById(R.id.delete);
            edit = itemView.findViewById(R.id.edit);
        }
    }
    private int getCheckedItem(NavigationView navigationView) {
        Menu menu = navigationView.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            if (item.isChecked()) {
                return i;
            }
        }

        return -1;
    }
}

