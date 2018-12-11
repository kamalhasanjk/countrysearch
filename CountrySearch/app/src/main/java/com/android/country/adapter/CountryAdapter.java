package com.android.country.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.country.R;

import com.android.country.model.Country;
import com.android.country.utils.ImageLoader;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.squareup.picasso.Picasso;

import java.util.List;


public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private static final String TAG = CountryAdapter.class.getSimpleName();

    private List<Country> countrys;
    private int rowLayout;
    private Context context;
    private Activity activity;
    public static final String IMAGE_URL_BASE_PATH="http://image.tmdb.org/t/p/w342//";

    public CountryAdapter(List<Country> countrys, int rowLayout, Context context, Activity activity) {
        this.countrys = countrys;
        this.rowLayout = rowLayout;
        this.context = context;
        this.activity = activity;
    }

    //A view holder inner class where we get reference to the views in the layout using their ID

    public static class CountryViewHolder extends RecyclerView.ViewHolder {
        LinearLayout countrysLayout;
        TextView countryTitle;
        TextView countryCapital;
       // TextView countryCurrency;
        ImageView countryImage;

        public CountryViewHolder(View v) {
            super(v);
            countrysLayout = (LinearLayout) v.findViewById(R.id.country);
            countryImage = (ImageView) v.findViewById(R.id.country_image);
            countryTitle = (TextView) v.findViewById(R.id.country_name);
            countryCapital = (TextView) v.findViewById(R.id.country_capital);
           // countryCurrency = (TextView) v.findViewById(R.id.country_currency);

        }
    }


    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CountryViewHolder holder, final int position) {
        String image_url =  countrys.get(position).getFlag();
        Log.d(TAG,"Image URL "+image_url);

//        Picasso.with(context)
//                .load(image_url)
//                .placeholder(android.R.drawable.sym_def_app_icon)
//                .error(android.R.drawable.sym_def_app_icon)
//                .into(holder.countryImage);
        ImageLoader.loadImage(activity,image_url,holder.countryImage);
       // loadImage(image_url,holder.countryImage);
        holder.countryTitle.setText(countrys.get(position).getName());
        holder.countryCapital.setText(countrys.get(position).getCapital());
        //holder.countryCurrency.setText(countrys.get(position).getCurrencies()[0].getName());

    }

    @Override
    public int getItemCount() {
        return countrys.size();
    }


    public void loadImage(String url, ImageView drawableView){


        RequestBuilder<PictureDrawable> requestBuilder = GlideToVectorYou
                .init()
                .with(activity)
                .getRequestBuilder();


        //Uri uri = Uri.parse("http://www.clker.com/cliparts/u/Z/2/b/a/6/android-toy-h.svg");
        Uri uri = Uri.parse(url);
        requestBuilder
                .load(uri)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(new RequestOptions()
                        .centerCrop())
                .into(drawableView);

    }










}
