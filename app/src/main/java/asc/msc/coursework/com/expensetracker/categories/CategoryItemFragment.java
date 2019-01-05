package asc.msc.coursework.com.expensetracker.categories;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import asc.msc.coursework.com.expensetracker.R;

public class CategoryItemFragment extends Fragment {

    View view;
    public CategoryItemFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.items,container,false);
        return view;
    }
}
