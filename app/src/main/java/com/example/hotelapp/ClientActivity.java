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

import com.example.hotelapp.entities.User;
import com.example.hotelapp.fragments.ClientOffersFragment;
import com.example.hotelapp.fragments.ClientPriceFragment;
import com.example.hotelapp.fragments.RoomTypesFragment;
import com.example.hotelapp.fragments.RoomsFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.Optional;

public class ClientActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private User currentUser;
    private AppDatabase db;
    private int userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        db = AppDatabase.getInstance(this);
        if(getIntent().getExtras()!=null) {
            userId = getIntent().getExtras().getInt("userId");
            currentUser = db.userDao().getUserById(userId);
        }

        dl = (DrawerLayout) findViewById(R.id.client_activity);
        t = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView) findViewById(R.id.nv);
        hideItems();
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                FragmentManager fragmentManager = getSupportFragmentManager();
                Bundle args=new Bundle();
                args.putInt("userId",userId);
                switch (id) {
                    case R.id.navigation_offers:
                        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView2, ClientOffersFragment.class, args)
                                .setReorderingAllowed(true)
                                .addToBackStack("name")
                                .commit();
                        break;

                    case R.id.navigation_roomTypes:
                        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView2, RoomTypesFragment.class, null)
                                .setReorderingAllowed(true)
                                .addToBackStack("name")
                                .commit();
                        break;

                    case R.id.navigation_rooms:
                        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView2, RoomsFragment.class, null)
                                .setReorderingAllowed(true)
                                .addToBackStack("name")
                                .commit();
                        break;
                    case R.id.navigation_prices:
                        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView2, ClientPriceFragment.class, null)
                                .setReorderingAllowed(true)
                                .addToBackStack("name")
                                .commit();
                        break;
                    case R.id.navigation_makeBooking:
                        Intent i = new Intent(getApplicationContext(), ClientMakeBookingActivity.class);
                        i.putExtra("userId",userId);
                        i.putExtra("option","Booking");
                        startActivity(i);
                        break;
                    case R.id.navigation_logout:
                        i=new Intent(getApplicationContext(),MainActivity.class);
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
        if (getIntent().getExtras()==null) {
            navMenu.findItem(R.id.navigation_makeBooking).setVisible(false);
            navMenu.findItem(R.id.navigation_logout).setTitle("Exit");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}