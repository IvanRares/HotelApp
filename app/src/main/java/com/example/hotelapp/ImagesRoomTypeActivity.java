package com.example.hotelapp;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.hotelapp.entities.Image;
import com.example.hotelapp.fragments.RoomTypeImagesRecyclerViewAdapter;
import com.example.hotelapp.fragments.RoomTypeRecyclerViewAdapter;
import com.example.hotelapp.pojos.RoomTypeAndImage;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ImagesRoomTypeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {



        ArrayList<byte[]> images = (ArrayList<byte[]>) getIntent().getSerializableExtra("images");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images_room_type);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        RecyclerView listView=findViewById(R.id.activity_images_room_type_list_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(linearLayoutManager);
        listView.setAdapter(new RoomTypeImagesRecyclerViewAdapter(images,this));





    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }



}

