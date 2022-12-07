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
import com.example.hotelapp.EditOfferActivity;
import com.example.hotelapp.EditPriceActivity;
import com.example.hotelapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClientPriceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClientPriceFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    public ClientPriceFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ClientPriceFragment newInstance(int columnCount) {
        ClientPriceFragment fragment = new ClientPriceFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_price_item_list, container, false);
        RecyclerView recyclerView=view.findViewById(R.id.admin_price_list);
        AppDatabase db=AppDatabase.getInstance(getContext());
        // Set the adapter
        if (recyclerView instanceof RecyclerView) {
            Context context = view.getContext();
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            ClientPriceRecyclerViewAdapter adapter=new ClientPriceRecyclerViewAdapter(getContext());
            db.priceDao().getPrices().observe(getViewLifecycleOwner(),data->adapter.setData(data));
            recyclerView.setAdapter(adapter);
        }

        return view;
    }
}