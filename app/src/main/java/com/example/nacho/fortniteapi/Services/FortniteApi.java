package com.example.nacho.fortniteapi.Services;

import com.example.nacho.fortniteapi.Values.Player;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface FortniteApi {

    @Headers({
         "TRN-Api-Key: 874ca12a-2634-492d-84ac-c62056a961a4",
    })

    @GET("/v1/profile/{platform}/{epic-name}")
    Observable<Player> loadPlayer(@Path("platform") String platform, @Path("epic-name") String epic_name);


    static  FortniteApi create(){
       /* Gson gson = new GsonBuilder()
                .setLenient()
                .create();*/

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.fortnitetracker.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(FortniteApi.class);
    }

}
