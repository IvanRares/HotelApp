package com.example.hotelapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hotelapp.AppDatabase;
import com.example.hotelapp.EditAmenityActivity;
import com.example.hotelapp.EditRoomActivity;
import com.example.hotelapp.R;
import com.example.hotelapp.fragments.placeholder.PlaceholderContent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A fragment representing a list of Items.
 */
public class AdminAmenityFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AdminAmenityFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static AdminAmenityFragment newInstance(int columnCount) {
        AdminAmenityFragment fragment = new AdminAmenityFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_amenity_item_list, container, false);
        RecyclerView recyclerView=view.findViewById(R.id.admin_amenity_list);
        AppDatabase db=AppDatabase.getInstance(getContext());
        // Set the adapter
        if (recyclerView instanceof RecyclerView) {
            Context context = view.getContext();
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            AdminAmenityRecyclerViewAdapter adapter=new AdminAmenityRecyclerViewAdapter(getContext());
            db.amenityDao().getAmenities().observe(getViewLifecycleOwner(),data->adapter.setData(data));
            recyclerView.setAdapter(adapter);
        }

        FloatingActionButton addButton=view.findViewById(R.id.admin_amenity_item_list_fab);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getContext(), EditAmenityActivity.class);
                i.putExtra("Option","Add");
                startActivity(i);
            }
        });
        return view;
    }
}