package com.example.expensetracker.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.expensetracker.R;
import com.example.expensetracker.fragments.AnalysisFragment;
import com.example.expensetracker.fragments.ExpenseListFragment;
import com.example.expensetracker.fragments.SettingFragment;
import com.example.expensetracker.fragments.StatFragment;
import com.example.expensetracker.utils.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.list,
            R.drawable.bars,
            R.drawable.settings
    };
    private static SharedPreferences prefs;
    private NavigationView navigatioView;
    private SessionManager sessionManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

        sessionManager = new SessionManager(MainActivity.this);

        setNavigationViewListener();

//        navigatioView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                Toast.makeText(MainActivity.this, menuItem.getItemId(), Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });


        getSupportFragmentManager().beginTransaction().add(R.id.frame_container, new ExpenseListFragment()).commit();



        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.b_navigate);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.nav_list:
                        selectedFragment = new ExpenseListFragment();
                        Toast.makeText(MainActivity.this, "Listing", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_stat:
                        selectedFragment = new StatFragment();
                        Toast.makeText(MainActivity.this, "Stats", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_analysis:
                        selectedFragment = new AnalysisFragment();
                        Toast.makeText(MainActivity.this, "Analysis", Toast.LENGTH_SHORT).show();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, selectedFragment).commit();


                return true;
            }
        });


        FloatingActionButton floatButton = (FloatingActionButton) findViewById(R.id.add_new_expense);

        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewExpenseActivity.class);
                startActivity(intent);
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


    }

    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        Toast.makeText(this, "ID: " + item.getItemId(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {

            case R.id.logout: {
                sessionManager.logout();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                break;
            }
        }
        //close navigation drawer
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    private void setupViewPager(ViewPager viewPager) {
//        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
//
//        adapter.addFragment(new ExpenseListFragment(), "List");
//        adapter.addFragment(new StatFragment(), "Stat");
//        adapter.addFragment(new SettingFragment(), "Setting");
//        viewPager.setAdapter(adapter);
//
//        // icon color code rgba(255, 196, 0, 1)
//    }


//    class ViewPagerAdapter extends FragmentPagerAdapter {
//        private final List<Fragment> mFragmentList = new ArrayList<>();
//        private final List<String> mFragmentTitleList = new ArrayList<>();
//
//        public ViewPagerAdapter(FragmentManager manager) {
//            super(manager);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return mFragmentList.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return mFragmentList.size();
//        }
//
//        public void addFragment(Fragment fragment, String title) {
//            mFragmentList.add(fragment);
//            mFragmentTitleList.add(title);
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//
////            return mFragmentTitleList.get(position);
//            return null;
//        }
//    }


}
