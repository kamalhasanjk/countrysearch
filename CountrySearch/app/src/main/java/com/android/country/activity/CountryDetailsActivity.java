package com.android.country.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.country.R;
import com.android.country.db.AppDatabase;
import com.android.country.model.Country;
import com.android.country.model.LanguageConverter;
import com.android.country.model.StringArrayConverter;
import com.android.country.utils.AppUtils;
import com.android.country.utils.ImageLoader;

public class CountryDetailsActivity extends AppCompatActivity {

    ImageView countryImage;
    TextView name,capital,currency, timezone;
    TextView callingcode,region,subregion, language;
    Button save;
    private AppDatabase mDb;

    private Country mCountry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);
        setTitle("Country Details");
        int position = 0;
        boolean isOnline = false;
        if(getIntent() != null ){
            position = getIntent().getIntExtra("countryPos",0);
            isOnline = getIntent().getBooleanExtra("isOnline",true);
        }
        mCountry = AppUtils.getInstance().getCountries().get(position);

        mDb = AppDatabase.getInstance(getApplicationContext());

        countryImage = (ImageView)findViewById(R.id.country_image);
        name = (TextView) findViewById(R.id.name);
        capital = (TextView) findViewById(R.id.capital);
        currency = (TextView) findViewById(R.id.currency);
        timezone = (TextView) findViewById(R.id.timezone);

        callingcode = (TextView) findViewById(R.id.callingcode);
        region = (TextView) findViewById(R.id.region);
        subregion = (TextView) findViewById(R.id.subregion);
        language = (TextView) findViewById(R.id.language);

        save = (Button)findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCountry();
            }
        });
        if(!isOnline){
            save.setVisibility(View.GONE);
        }else{
            save.setVisibility(View.VISIBLE);
        }
        loadCountryDetails(position);
    }

    public void loadCountryDetails(int position){

        name.setText(mCountry.getName());
        capital.setText(mCountry.getCapital());
        currency.setText(mCountry.getCurrencies()[0].getName()+" ("+mCountry.getCurrencies()[0].getSymbol()+")");
        timezone.setText(mCountry.getTimezones()[0]);

        callingcode.setText(new StringArrayConverter().toString(mCountry.getCallingCodes()));
        region.setText(mCountry.getRegion());
        subregion.setText(mCountry.getSubRegion());
        language.setText(new LanguageConverter().toStringTranslation(mCountry.getLanguages()));

        ImageLoader.loadImage(this,mCountry.getFlag(),countryImage);
    }


    public void saveCountry() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                mDb.countryModel().insertCountry(mCountry);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                    Toast toast=Toast.makeText(getApplicationContext(),"Country Details Saved.",Toast.LENGTH_SHORT);
                    toast.show();

            }
        }.execute();
    }

}
