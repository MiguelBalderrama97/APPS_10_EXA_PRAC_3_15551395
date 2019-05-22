package com.example.miguel.mediaplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class PreviousService extends Service {
    public PreviousService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if(MainActivity.index >= MainActivity.songs.size()){
            MainActivity.index = 0;
        }
        if(MainActivity.index < 0){
            MainActivity.index = (MainActivity.songs.size() - 1);
        }
        MainActivity.nMultimedia = MediaPlayer.create(getApplicationContext(), MainActivity.songs.get(MainActivity.index--).getResource());
    }



    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        if(MainActivity.nMultimedia != null){
            MainActivity.nMultimedia.start();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(MainActivity.nMultimedia != null){
            MainActivity.nMultimedia.stop();
            MainActivity.nMultimedia.release();
        }
    }
}
