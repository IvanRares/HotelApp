package com.example.hotelapp.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.hotelapp.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RoomsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RoomsFragment extends Fragment {

    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private DatePickerDialog endDatePickerDialog;
    private Button endDateButton;


    public RoomsFragment() {
        // Required empty public constructor
    }


    public static RoomsFragment newInstance() {
        RoomsFragment fragment = new RoomsFragment();
        Bundle args = new Bundle();


        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_rooms, container, false);
        initDatePicker(v.getContext());
        dateButton = v.findViewById(R.id.fragment_rooms_datePickerButton);
        dateButton.setText(getTodaysDate());
        dateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openDatePicker(v);
            }
        });
        endDateButton = v.findViewById(R.id.fragment_rooms_endDatePickerButton);
        endDateButton.setText(getNextDayDate());
        endDateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openEndDatePicker(v);
            }
        });
        return v;
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
                    day=day+1;
                    callendar.set(year,month-1,day);
                    endDateButton.setText(makeDateString(day,month,year));
                    endDatePickerDialog.getDatePicker().setMinDate(callendar.getTimeInMillis());





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

            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;


        cal.set(year+1,month,day);
        datePickerDialog = new DatePickerDialog(context, style, dateSetListener, year, month, day);
        endDatePickerDialog = new DatePickerDialog(context, style, endDateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
        datePickerDialog.getDatePicker().setMaxDate(cal.getTimeInMillis());
        cal.set(year+1,month,day+1);
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