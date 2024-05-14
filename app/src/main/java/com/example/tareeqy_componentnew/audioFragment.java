package com.example.tareeqy_componentnew;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class audioFragment extends Service {
    private MediaPlayer player;
    public audioFragment() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public void onCreate(){
        player = MediaPlayer.create(getApplicationContext(),R.raw.car);
        player.setLooping(false);
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // Stop the service after playing once
                stopSelf();
            }
        });
    }
    public int onStartCommand(Intent intent,int flags, int startID){
        player.start();
        return  super.onStartCommand(intent, flags, startID);
    }
    public void onDestroy(){
        player.stop();
        player.release();
    }
}