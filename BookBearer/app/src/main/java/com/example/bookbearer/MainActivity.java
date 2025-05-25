package com.example.bookbearer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.bookbearer.search.SearchActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.dashboardFragment);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener(){
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
           Fragment selectedFragment = null;

           switch(item.getItemId()){
               case R.id.dashboardFragment:
                   selectedFragment = new DashboardFragment();
                   break;
               case R.id.profileFragment:
                   selectedFragment = new ProfileFragment();
                   break;
               case R.id.settingsFragment:
                   selectedFragment = new SettingsFragment();
                   break;
           }
           getSupportFragmentManager().beginTransaction().replace(R.id.fragment,selectedFragment).commit();
           return true;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);

        MenuItem menuitem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuitem.getActionView();
        searchView.setQueryHint("Inserisci il titolo per intero");
        searchView.setOnQueryTextListener(searchListener);


        return super.onCreateOptionsMenu(menu);
    }

    private SearchView.OnQueryTextListener searchListener = new SearchView.OnQueryTextListener(){
        @Override
        public boolean onQueryTextSubmit(String s) {
            Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
            intent.putExtra("Title", s);
            startActivity(intent);
            return true;
        }

        @Override
        public boolean onQueryTextChange(String s) {
            return false;
        }
    };
}