package com.github.moduth.blockcanary.ui;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import com.github.moduth.blockcanary.OnBlockEventInterceptor;
import com.github.moduth.blockcanary.R;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.O;

public class Notifier implements OnBlockEventInterceptor {

    private static String BLOCK_CHANNEL_ID = "block_channel";

    @Override
    public void onBlockEvent(Context context, String timeStart) {
        Intent intent = new Intent(context, DisplayBlockActivity.class);
        intent.putExtra("show_latest", timeStart);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent, FLAG_UPDATE_CURRENT);
        String contentTitle = context.getString(R.string.block_canary_class_has_blocked, timeStart);
        String contentText = context.getString(R.string.block_canary_notification_message);
        show(context, contentTitle, contentText, pendingIntent);
    }

    @TargetApi(O)
    private void show(Context context, String contentTitle, String contentText, PendingIntent pendingIntent) {
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationChannel channel = notificationManager.getNotificationChannel(BLOCK_CHANNEL_ID);

        if (channel == null) {
            CharSequence channelName = "Block channel";
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel notificationChannel = new NotificationChannel(BLOCK_CHANNEL_ID, channelName, importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Notification.Builder builder;
        if (SDK_INT >= O) {
            builder = new Notification.Builder(context, BLOCK_CHANNEL_ID);
        } else {
            builder = new Notification.Builder(context);
        }

        builder
                .setSmallIcon(R.drawable.block_canary_notification)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setContentIntent(pendingIntent)
                .setPriority(Notification.PRIORITY_DEFAULT);

        notificationManager.notify(0xDEAFBEEF, builder.build());
    }
}
