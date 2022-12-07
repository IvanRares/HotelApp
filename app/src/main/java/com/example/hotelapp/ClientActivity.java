package com.example.hotelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.hotelapp.fragments.ClientOffersFragment;
import com.example.hotelapp.fragments.RoomTypesFragment;
import com.example.hotelapp.fragments.RoomsFragment;
import com.google.android.material.navigation.NavigationView;

public class ClientActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        dl = (DrawerLayout) findViewById(R.id.client_activity);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open,R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView) findViewById(R.id.nv);
        hideItems();
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                FragmentManager fragmentManager=getSupportFragmentManager();
                switch(id)
                {
                    case R.id.navigation_offers:
                        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView2, ClientOffersFragment.class,null)
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
                        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView2, RoomsFragment.class,null)
                                .setReorderingAllowed(true)
                                .addToBackStack("name")
                                .commit();
                        break;
                    default:
                        break;

                }
                dl.closeDrawer(GravityCompat.START);
                return true;



            }
        });

    }

    private void hideItems() {
        Menu navMenu=nv.getMenu();
        navMenu.findItem(R.id.navigation_amenities).setVisible(false);
        navMenu.findItem(R.id.navigation_images).setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}