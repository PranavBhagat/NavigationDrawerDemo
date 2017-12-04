package com.example.navigationdrawerdemo;


import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout; /*This is to add the Hamburger button to work on the layout*/
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentTransaction fragmentTransaction;

    /*TO MAKE ON CLICK ACTIVITY*/
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      toolbar=(Toolbar) findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);

      drawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);

      actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);

      drawerLayout.setDrawerListener(actionBarDrawerToggle);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container, new EventFragment());
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Home ...");

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.home_id:
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.main_container,new EventFragment());
                    fragmentTransaction.commit();
                    getSupportActionBar().setTitle("Event Fragment ");
                    item.setChecked(true);
                    drawerLayout.closeDrawers();
                    break;

                    case R.id.message_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container,new SubscribeFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Subscribe Fragment In");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                }
                return false;
            }
        });



    }

    @Override
    public void onPostCreate( Bundle savedInstanceState)/*This OnPostCreate is also overriden to  make the toggle button work*/
    {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
}
