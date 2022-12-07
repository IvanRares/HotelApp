package com.example.hotelapp.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.hotelapp.AppDatabase;
import com.example.hotelapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClientOffersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClientOffersFragment extends Fragment {


    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private DatePickerDialog endDatePickerDialog;
    private Button endDateButton;
    private String startDate;
    private String endDate;
    private View view;

    public ClientOffersFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ClientOffersFragment newInstance(int columnCount) {
        ClientOffersFragment fragment = new ClientOffersFragment();
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
        view = inflater.inflate(R.layout.fragment_client_offers, container, false);
        initDatePicker(view.getContext());
        RecyclerView recyclerView=view.findViewById(R.id.client_offers_list);
        AppDatabase db=AppDatabase.getInstance(getContext());
        dateButton = view.findViewById(R.id.fragment_client_offers_datePickerButton);
        dateButton.setText(getTodaysDate());
        dateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openDatePicker(v);
            }
        });
        endDateButton = view.findViewById(R.id.fragment_client_offers_endDatePickerButton);
        endDateButton.setText(getNextDayDate());
        endDateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openEndDatePicker(v);
            }
        });
        // Set the adapter
        if (recyclerView instanceof RecyclerView) {
            Context context = view.getContext();
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            ClientOfferRecyclerViewAdapter adapter=new ClientOfferRecyclerViewAdapter(getContext());
            db.offerDao().getOffersByDate(startDate,endDate).observe(getViewLifecycleOwner(),data->adapter.setData(data));
            recyclerView.setAdapter(adapter);
        }


        return view;
    }
    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }
    private String getNextDayDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        day+=1;
        return makeDateString(day, month, year);
    }

    private void initDatePicker(Context context)
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                Calendar callendar=Calendar.getInstance();
                dateButton.setText(date);
                callendar.set(year,month-1,day);
                endDateButton.setText(makeDateString(day+1,month,year));
                endDatePickerDialog.getDatePicker().setMinDate(callendar.getTimeInMillis());
                String format = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
                startDate=sdf.format(callendar.getTime());
                callendar.set(year,month-1,day+1);
                endDate=sdf.format(callendar.getTime());

                RecyclerView recyclerView=view.findViewById(R.id.client_offers_list);
                AppDatabase db=AppDatabase.getInstance(getContext());
                ClientOfferRecyclerViewAdapter adapter=new ClientOfferRecyclerViewAdapter(getContext());
                db.offerDao().getOffersByDate(startDate,endDate).observe(getViewLifecycleOwner(),data->adapter.setData(data));
                recyclerView.setAdapter(adapter);



            }
        };
        DatePickerDialog.OnDateSetListener endDateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);

                endDateButton.setText(date);
                Calendar callendar=Calendar.getInstance();

                String format = "yyyy-MM-dd";
                callendar.set(year,month-1,day);
                SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
                endDate=sdf.format(callendar.getTime());

                RecyclerView recyclerView=view.findViewById(R.id.client_offers_list);
                AppDatabase db=AppDatabase.getInstance(getContext());
                ClientOfferRecyclerViewAdapter adapter=new ClientOfferRecyclerViewAdapter(getContext());
                db.offerDao().getOffersByDate(startDate,endDate).observe(getViewLifecycleOwner(),data->adapter.setData(data));
                recyclerView.setAdapter(adapter);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        String format = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);

        cal.set(year,month,day);
        startDate=sdf.format(cal.getTime());
        datePickerDialog = new DatePickerDialog(context, style, dateSetListener, year, month, day);
        endDatePickerDialog = new DatePickerDialog(context, style, endDateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
        cal.set(year,month,day+1);
        endDate=sdf.format(cal.getTime());

        endDatePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis());



    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }
    public void openEndDatePicker(View view)
    {
        endDatePickerDialog.show();
    }
}