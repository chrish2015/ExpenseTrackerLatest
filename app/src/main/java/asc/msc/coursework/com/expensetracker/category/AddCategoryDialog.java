package asc.msc.coursework.com.expensetracker.category;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

import asc.msc.coursework.com.expensetracker.MainActivity;
import asc.msc.coursework.com.expensetracker.R;
import asc.msc.coursework.com.expensetracker.addexpense.ExpenseListDropDown;
import asc.msc.coursework.com.expensetracker.dao.DataManipulation;
import asc.msc.coursework.com.expensetracker.dto.Category;
import asc.msc.coursework.com.expensetracker.dto.Expense;
import asc.msc.coursework.com.expensetracker.dto.Income;
import asc.msc.coursework.com.expensetracker.util.Util;

public class AddCategoryDialog extends DialogFragment {
    private String current = "";
    DataManipulation dataManipulation = new DataManipulation();

    View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            dismiss();
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View closeButton = view.findViewById(R.id.closeButton);
        closeButton.setOnClickListener(onClickListener);
        final EditText value = (EditText) view.findViewById(R.id.budget);
        Button addCategory=view.findViewById(R.id.add_category);
        value.addTextChangedListener(new TextWatcher() {
            DecimalFormat dec = new DecimalFormat("0.00");

            @Override
            public void afterTextChanged(Editable arg0) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    value.removeTextChangedListener(this);
                    Util util=new Util();
                    String formatted = util.getDollerConvertedString(s);

                    current = formatted;
                    value.setText(formatted);
                    value.setSelection(formatted.length());

                    value.addTextChangedListener(this);
                }
            }
        });
        final EditText categoryName = view.findViewById(R.id.categoryName);
        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoryNameText = categoryName.getText().toString();
                String budget = value.getText().toString();

                BigDecimal enteredValue = new BigDecimal(current.replace("$", "").replace(",", ""));

               dataManipulation.addCategories(new Category(categoryNameText,enteredValue));
                MainActivity.expenseList.setArrayList(dataManipulation.getTransactions());
                MainActivity.viewPageAdapter.notifyDataSetChanged();
                dismiss();
            }
        });
    }
}
