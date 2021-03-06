package com.example.nacho.fortniteapi.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


import com.example.nacho.fortniteapi.Repository.FortniteRepo;
import com.example.nacho.fortniteapi.Values.FinalStats;
import com.example.nacho.fortniteapi.Values.Player;
import com.example.nacho.fortniteapi.Values.PlayerStatsDetails;

public class FortniteVM extends AndroidViewModel {

    private FortniteRepo fr = FortniteRepo.getInstance();
    public MutableLiveData<List<FinalStats>> playerInfo = new MutableLiveData<>();
    public List<FinalStats> statsList = new ArrayList<FinalStats>();

    public FortniteVM(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<FinalStats>> getPlayerInfo() {
        return playerInfo;
    }

    public void getData(String platform, String epic_name) {
        fr.getPlayer(platform, epic_name)
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Player>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Player player) {
                        //liveData.postValue(forniteObjects);
                        if (player != null) {
                            statsList.clear();
                            PlayerStatsDetails data = player.getPlayerStats().getPlayerStatsDetails();
                            statsList.add(data.getScore());
                            statsList.add(data.getKDA());
                            statsList.add(data.getMatches());
                            statsList.add(data.getTRNRating());
                            playerInfo.postValue(statsList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("El servicio ha fallado", "err: " + e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}





