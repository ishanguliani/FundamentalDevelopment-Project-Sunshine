package com.example.android.sunshine.app;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A forecast fragment containing a simple view.
 */
public class ForecastFragment extends Fragment {

    public static ArrayAdapter<String> arrayAdapter ;
    public static Context context ;
    public static ListView listView ;

    public ForecastFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //enable the fragment to constitute a menu option
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.forecastfragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh) {

            FetchWeatherTask weatherTask = new FetchWeatherTask();
            weatherTask.execute("94043");

            return true;
        }


        return true;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my, container, false);

        String[] forecastArray = {
                "Today - Sunny - 88/63",
                "Tuesday - Sunny - 88/63",
                "Wednesday - Sunny - 88/63",
                "Thursday - Sunny - 88/63",
                "Friday - Sunny - 88/63",
                "Saturday - Sunny - 88/63",
                "Sunday - Sunny - 88/63",
                "Monday - Sunny - 88/63",
        };

        List<String> weekForecast = new ArrayList<String>(
                Arrays.asList(forecastArray)
        );

        context = getActivity() ;

        arrayAdapter = new ArrayAdapter<String>(
                context,
                R.layout.list_item_forecast,
                R.id.list_item_forecast_textview,
                weekForecast
        );

        listView = (ListView) rootView.findViewById(R.id.listview_forecast);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, arrayAdapter.getItem(position) , Toast.LENGTH_SHORT)
                        .show();
            }
        });

            /*Code to open connection to the openweather API and then retrieve the results in JSON format.
            We finally convert

             JSON -> INPUT STREAM
             INPUT STREAM -> BUFFERED READER
             BUFFERED READER -> STRING

            */
        return rootView;
    }




}


