package com.example.hotelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.hotelapp.fragments.ClientPriceFragment;
import com.example.hotelapp.fragments.EmployeeBookingFragment;
import com.example.hotelapp.fragments.ClientOffersFragment;
import com.example.hotelapp.fragments.RoomTypesFragment;
import com.example.hotelapp.fragments.RoomsFragment;
import com.google.android.material.navigation.NavigationView;

public class EmployeeActivity extends AppCompatActivity {
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        dl = (DrawerLayout) findViewById(R.id.employee_activity);
        t = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Rooms");

        nv = (NavigationView) findViewById(R.id.nv);
        hideItems();
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                FragmentManager fragmentManager = getSupportFragmentManager();
                switch (id) {
                    case R.id.navigation_offers:
                        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView2, ClientOffersFragment.class, null)
                                .setReorderingAllowed(true)
                                .addToBackStack("name")
                                .commit();
                        getSupportActionBar().setTitle("Offers");
                        break;

                    case R.id.navigation_roomTypes:
                        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView2, RoomTypesFragment.class, null)
                                .setReorderingAllowed(true)
                                .addToBackStack("name")
                                .commit();
                        getSupportActionBar().setTitle("Room Types");
                        break;

                    case R.id.navigation_rooms:
                        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView2, RoomsFragment.class, null)
                                .setReorderingAllowed(true)
                                .addToBackStack("name")
                                .commit();
                        getSupportActionBar().setTitle("Rooms");
                        break;
                    case R.id.navigation_bookings:
                        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView2, EmployeeBookingFragment.class, null)
                                .setReorderingAllowed(true)
                                .addToBackStack("name")
                                .commit();
                        getSupportActionBar().setTitle("Bookings");
                        break;
                    case R.id.navigation_prices:
                        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView2, ClientPriceFragment.class, null)
                                .setReorderingAllowed(true)
                                .addToBackStack("name")
                                .commit();
                        getSupportActionBar().setTitle("Prices");
                        break;
                    case R.id.navigation_logout:
                        Intent i=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i);
                        finish();
                    default:
                        break;

                }
                dl.closeDrawer(GravityCompat.START);
                return true;


            }
        });

    }

    private void hideItems() {
        Menu navMenu = nv.getMenu();
        navMenu.findItem(R.id.navigation_amenities).setVisible(false);
        navMenu.findItem(R.id.navigation_images).setVisible(false);
        navMenu.findItem(R.id.navigation_makeBooking).setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    public void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }
}