package com.example.appteka.core;

import android.content.Context;

import com.squareup.picasso.Picasso;

import javax.inject.Singleton;
import com.squareup.picasso.OkHttp3Downloader;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class AndroidModule {

    private Context context;

    public AndroidModule(Context context){
        this.context = context;
    }

    @Provides
    @Singleton
    public Picasso providePicasso(OkHttpClient client) {
        return new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(client))
                .build();
    }

}
