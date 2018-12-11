package com.android.country.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
//import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.country.R;
import com.android.country.adapter.CountryAdapter;
import com.android.country.db.AppDatabase;
import com.android.country.model.Country;
import com.android.country.rest.API;
import com.android.country.utils.AppUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;


public class CountryActivity extends AppCompatActivity {
    private static final String TAG = CountryActivity.class.getSimpleName();

    private Context context;
    private RecyclerView recyclerView = null;
    private ImageView imageView;
    private EditText searchTxt;
    private List<Country> countries;
    private AppDatabase mDb;
    private boolean isOnline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        setTitle("Search Country");
        mDb = AppDatabase.getInstance(getApplicationContext());

        String backupDBPath = mDb.getOpenHelper().getWritableDatabase().getPath();


        String currentDBPath=getDatabasePath("country.db").getAbsolutePath();


        context = getApplicationContext();
        searchTxt = (EditText) findViewById(R.id.searchTxt);

        searchTxt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if(searchTxt.getText() != null && searchTxt.getText().toString().length() > 0){

                        searchCountry(searchTxt.getText().toString().trim());
                    }
                    return true;
                }
                return false;
            }
        });
        searchTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchCountry(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        imageView = (ImageView) findViewById(R.id.searchicon);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchTxt.getText() != null && searchTxt.getText().toString().length() > 0){
                    searchCountry(searchTxt.getText().toString().trim());
                }
            }
        });
        //Find vies
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), CountryDetailsActivity.class);
                        intent.putExtra("countryPos",position);
                        intent.putExtra("isOnline",isOnline);
                        startActivity(intent);
                    }
                }, 400);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        loadCountries();

    }


    public void loadCountries() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                countries=  mDb.countryModel().loadAllCountries();
                AppUtils.getInstance().setCountries(countries);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if(countries != null && countries.size() > 0) {
                    isOnline = false;
                    recyclerView.setAdapter(new CountryAdapter(countries, R.layout.country, getApplicationContext(), CountryActivity.this));
                }else{
//                    Toast toast=Toast.makeText(getApplicationContext(),"No countries found.",Toast.LENGTH_SHORT);
//                    toast.show();
                }
            }
        }.execute();
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public void searchCountry(String country){
        if(country != null && country.length()> 2) {
            if (isNetworkAvailable()) {
                searchCountryOnline(country);
            } else {
                searchCountryOffline(country);
            }
        }
    }


    public void searchCountryOnline(final String country){
        final ProgressDialog dialog = ProgressDialog.show(this, "Loading", "Please wait...", true);
        API.countries().findByName(country).enqueue(new retrofit2.Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                dialog.dismiss();
                countries = response.body();
                if(countries != null && countries.size() > 0) {
                    isOnline = true;
                    AppUtils.getInstance().setCountries(countries);
                    recyclerView.setAdapter(new CountryAdapter(countries, R.layout.country, getApplicationContext(), CountryActivity.this));
                }else{
                   // Toast toast=Toast.makeText(getApplicationContext(),"No countries found.",Toast.LENGTH_SHORT);
                    //toast.show();
                }
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable throwable) {
                dialog.dismiss();
            }
        });
    }

    public void searchCountryOffline(final String country){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                countries=  mDb.countryModel().searchCountry(country);
                AppUtils.getInstance().setCountries(countries);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if(countries != null && countries.size() > 0) {
                    isOnline = false;
                    recyclerView.setAdapter(new CountryAdapter(countries, R.layout.country, getApplicationContext(), CountryActivity.this));
                }else{
                    //Toast toast=Toast.makeText(getApplicationContext(),"No countries found.",Toast.LENGTH_SHORT);
                    //toast.show();
                }
            }
        }.execute();
    }



    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {

            this.clickListener = clickListener;

            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    public interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }



}
