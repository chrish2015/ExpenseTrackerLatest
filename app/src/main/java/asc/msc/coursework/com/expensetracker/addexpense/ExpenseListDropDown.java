package asc.msc.coursework.com.expensetracker.addexpense;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import asc.msc.coursework.com.expensetracker.dto.Category;

public class ExpenseListDropDown {


    public void createDropDown(Spinner spinner, Context context, List<Category> categoryList){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }

                return v;
            }

            @Override
            public int getCount() {
                return super.getCount()-1; // you dont display last item. It is used as hint.
            }

        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayList<String> strings = new ArrayList<>();
        for(Category category:categoryList){
            strings.add(category.getCategoryName());
        }
        adapter.addAll(strings);
        adapter.add("Select Category");
        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getCount());
    }
}
