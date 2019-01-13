package asc.msc.coursework.com.expensetracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import asc.msc.coursework.com.expensetracker.addexpense.AddExpenseDialog;
import asc.msc.coursework.com.expensetracker.categories.CategoryView;
import asc.msc.coursework.com.expensetracker.categories.ViewPageAdapter;
import asc.msc.coursework.com.expensetracker.category.AddCategoryDialog;
import asc.msc.coursework.com.expensetracker.dao.DataManipulation;
import asc.msc.coursework.com.expensetracker.expenselist.ExpenseList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static RecyclerView expenseListView;
    public static DataManipulation dataManipulation = new DataManipulation();
    public static LinearLayout ll;
    LinearLayoutManager manager;
    public static SharedPreferences sharedPreferences;
    public static ExpenseList expenseList;
    public static TextView totalValue;
    public static FragmentManager supportFragmentManager;
    public static boolean isExpenseView = true;
    public static ViewPageAdapter viewPageAdapter;
    MenuItem addCategoryMenu;
    public static MainActivity mainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity=this;
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ll = findViewById(R.id.contentLayout);

        setSharedPreferences();
        dataManipulation.dataInitialization();
        supportFragmentManager = getSupportFragmentManager();


        expenseListView = findViewById(R.id.expenseList);
        totalValue = findViewById(R.id.totalValue);

        manager = new LinearLayoutManager(this);
        expenseList = new ExpenseList(this, dataManipulation.getTransactions(), dataManipulation.getCategories(), totalValue);
        expenseListView.setLayoutManager(manager);
        expenseListView.setAdapter(expenseList);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddExpense();
            }
    });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    private void showAddExpense() {
        AddExpenseDialog addExpenseDialog = new AddExpenseDialog();
        addExpenseDialog.show(supportFragmentManager, "AddExpenses");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        addCategoryMenu = menu.findItem(R.id.addCategory);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.expense_view) {
            isExpenseView=true;
            addCategoryMenu.setVisible(false);
//            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.contentLayout);
            LinearLayout linearLayout = findViewById(R.id.contentLayout);

            linearLayout.removeAllViews(); // remove previous view, add 2nd layout
            linearLayout.addView(LayoutInflater.from(this).inflate(R.layout.content_main, ll, false));
            RecyclerView expenseListView = findViewById(R.id.expenseList);
            LinearLayoutManager manager = new LinearLayoutManager(this);
            expenseList = new ExpenseList(this, dataManipulation.getTransactions(), dataManipulation.getCategories(), (TextView) findViewById(R.id.totalValue));

            expenseListView.setLayoutManager(manager);
            expenseListView.setAdapter(expenseList);

            FloatingActionButton fab = findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showAddExpense();
                }
            });

        } else if (id == R.id.category_view) {
            isExpenseView = false;
            createView();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void createView() {
        TabLayout tabLayout;
        ViewPager viewPager;

        ll.removeAllViews(); // remove previous view, add 2nd layout
        ll.addView(LayoutInflater.from(this).inflate(R.layout.categories_layout, ll, false));
        addCategoryMenu.setVisible(true);

        viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.viewPager);
        CategoryView categoryView = new CategoryView();
        categoryView.createCategoryView(viewPageAdapter);
        viewPager.setAdapter(viewPageAdapter);


        tabLayout = findViewById(R.id.tabLayout);

        tabLayout.setupWithViewPager(viewPager);
    }


    /**
     * Initialize shared preference for data saving and retrieval.
     */
    private void setSharedPreferences() {
        sharedPreferences = getSharedPreferences("asc.msc.coursework.com.expensetracker", Context.MODE_PRIVATE);
    }

    public void addCategoryAction(MenuItem item) {
        AddCategoryDialog addCategoryDialog = new AddCategoryDialog();
        addCategoryDialog.show(supportFragmentManager, "addCategory");
    }
}
