package com.example.nacho.fortniteapi;

import android.arch.lifecycle.ViewModelProviders;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.nacho.fortniteapi.Adapter.FortniteAdapter;
import com.example.nacho.fortniteapi.Values.FinalStats;
import com.example.nacho.fortniteapi.Values.Player;
import com.example.nacho.fortniteapi.Values.PlayerStatsDetails;
import com.example.nacho.fortniteapi.ViewModel.FortniteVM;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private FortniteAdapter fAdapter;
    private Spinner spinner;
    private EditText etPlayer;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton fabBuscar;
    private FortniteVM forniteViewModel;


    private String platform;
    private String epic_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findById();
        btnClick();

    }

    private void changeData(String plattaform, String epic_nickname) {
        forniteViewModel = ViewModelProviders.of(this).get(FortniteVM.class);
        forniteViewModel.playerInfo.observe(this, stadisticObjectData -> {
            if (stadisticObjectData != null) {
                Log.d("ServicioFornite", "Cambios: " + stadisticObjectData);
                generateForniteList(stadisticObjectData);
            }
        });
        forniteViewModel.getData(plattaform, epic_nickname);
    }

    private void generateForniteList(List<FinalStats> listFornite) {
        recyclerView = findViewById(R.id.my_recycler_view);
        fAdapter = new FortniteAdapter(listFornite);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(fAdapter);
    }

    private void btnClick() {
        fabBuscar.setOnClickListener(v -> {
            platform = spinner.getSelectedItem().toString();
            epic_name = etPlayer.getText().toString();
            changeData(platform, epic_name);
            setTitle(epic_name);
        });

    }

    public void findById() {
        recyclerView = findViewById(R.id.my_recycler_view);
        spinner = findViewById(R.id.my_spinner);
        etPlayer = findViewById(R.id.etSearch);
        fabBuscar = findViewById(R.id.fabSearch);
    }

}

