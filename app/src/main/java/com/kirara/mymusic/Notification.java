package com.kirara.mymusic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.media.session.MediaSessionCompat;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Notification {
    public static final String CHANNEL_ID = "channel1";
    public static final String CHANNEL_PREV = "channel1";
    public static final String CHANNEL_PLAY = "channel1";
    public static final String CHANNEL_NEXT = "channel1";

    public static android.app.Notification notification;

    public static void createNotification(Context context, AudioModel audioModel, int playbutton, int pos, int size ){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
            MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(context, "tag");

            Bitmap icon = BitmapFactory.decodeResource(context.getResources(), audioModel.getImage());

            //create notification
            notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.mscicon)
                    .setContentTitle(audioModel.getTitle())
                    .setLargeIcon(icon)
                    .setOnlyAlertOnce(true)
                    .setShowWhen(false)
                    .setPriority(NotificationCompat.PRIORITY_LOW).build();

            notificationManagerCompat.notify(1, notification);
        }
    }
}
