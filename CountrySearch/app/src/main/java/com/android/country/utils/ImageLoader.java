package com.android.country.utils;

import android.app.Activity;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

public class ImageLoader {

    public static void loadImage(Activity activity, String url, ImageView imageView){

        RequestBuilder<PictureDrawable> requestBuilder = GlideToVectorYou
                .init()
                .with(activity)
                .getRequestBuilder();


        Uri uri = Uri.parse(url);
        requestBuilder
                .load(uri)
                .transition(DrawableTransitionOptions.withCrossFade())
               // .apply(new RequestOptions().centerCrop())
                .into(imageView);
    }
}
