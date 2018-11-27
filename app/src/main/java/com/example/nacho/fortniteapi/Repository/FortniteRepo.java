package com.example.nacho.fortniteapi.Repository;

import com.example.nacho.fortniteapi.Services.FortniteApi;
import com.example.nacho.fortniteapi.Values.Player;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Call;


public class FortniteRepo {

    private static volatile FortniteRepo ourInstance = new FortniteRepo();

   public static FortniteRepo getInstance() {
        if (ourInstance == null) {
            synchronized (FortniteRepo.class) {
                if (ourInstance == null) {
                    ourInstance = new FortniteRepo();
                }
            }
        }
        return ourInstance;
    }

    FortniteRepo() {
    }

    private FortniteApi fortniteApi =FortniteApi.create();

    public Observable<Player> getPlayer(String platform, String epic_name){
        return fortniteApi.loadPlayer(platform,epic_name);
    }



}

