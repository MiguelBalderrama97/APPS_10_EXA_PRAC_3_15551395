package com.example.miguel.mediaplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView txtName;
    private ImageButton btnPrev, btnNext, btnPlay, btnPause;
    private Switch swRandom;
    private ImageView imgIcon;

    private Intent inNext, inPrev;

    public static MediaPlayer nMultimedia = null;

    public static List<Song> songs;
    public static int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = findViewById(R.id.txtName);
        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);
        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        swRandom = findViewById(R.id.swRandom);
        imgIcon = findViewById(R.id.imgIcon);

        songs = getAllSongs();

        inNext = new Intent(MainActivity.this, NextService.class);
        inPrev = new Intent(MainActivity.this, PreviousService.class);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(inNext);
                stopService(inPrev);
                startService(inNext);
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(inNext);
                stopService(inPrev);
                startService(inPrev);
            }
        });
    }

    private List<Song> getAllSongs(){
        List<Song> customSongs = new ArrayList<>();
//        TODO: Llenar con canciones
        customSongs.add(new Song(R.raw.cancion, R.mipmap.ic_launcher, "Rockera!"));
        customSongs.add(new Song(R.raw.welcome, R.mipmap.ic_launcher, "Welcome to the jungle"));
        customSongs.add(new Song(R.raw.sweet, R.mipmap.ic_launcher, "Sweet Child O' Mine"));
        return customSongs;
    }
}
