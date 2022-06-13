package com.jazbass.jbtaxis;

import static android.app.Notification.DEFAULT_ALL;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.IBinder;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class TimerService extends Service {

    public void launchNotification(){
        String channelId = "NotificationTaxi";
        String channelName= "JB Taxis";
        int channelImportance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel notificationChannel = new
                NotificationChannel(channelId, channelName, channelImportance);
        notificationChannel.enableVibration(true);
        notificationChannel.setLightColor(Color.RED);
        notificationChannel.enableLights(true);

        NotificationManager notificationManager = (NotificationManager) getApplicationContext()
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);
        NotificationCompat.Builder builder = new
                NotificationCompat.Builder(getApplicationContext(), channelId)
                .setDefaults(DEFAULT_ALL)
                .setSmallIcon(R.drawable.icono_taxi_mini)
                .setChannelId(channelId)
                .setContentText("The taxi is waiting in your current location");
        notificationManager.notify(1, builder.build());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final Intent i = intent;
        int waitTime = i.getIntExtra("waitTime",0);
        CountDownTimer timer = new CountDownTimer(waitTime* 6000L, waitTime* 6000L) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                getApplicationContext().stopService(i);
                launchNotification();
                Intent i = new Intent(TimerService.this, HistoryActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        };
        timer.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
