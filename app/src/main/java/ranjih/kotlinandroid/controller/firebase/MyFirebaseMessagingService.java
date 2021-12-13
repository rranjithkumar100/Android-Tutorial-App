package ranjih.kotlinandroid.controller.firebase;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import br.com.goncalves.pugnotification.notification.PugNotification;
import ranjih.kotlinandroid.R;
import ranjih.kotlinandroid.view.activity.MainActivity;

/**
 * Created by Ranjithkumar on 29-05-2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();

    private NotificationUtils notificationUtils;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(TAG, "From: " + remoteMessage.getFrom());
        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            String imageUrl="";
            if(remoteMessage.getNotification().getImageUrl()!= null)
            imageUrl= remoteMessage.getNotification().getImageUrl().toString();

            String title= remoteMessage.getNotification().getTitle();
            String msg= remoteMessage.getNotification().getBody();
            startNewsPage(title,msg,imageUrl);
        }
    }


    private void startNewsPage(String title, String description,String imageUrl){


        Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);
        resultIntent.putExtra("message", title);
        resultIntent.putExtra("isNews",true);

        resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        final PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_CANCEL_CURRENT
                );
        PugNotification.with(this)
                .load()
                .click(resultPendingIntent)
                .title(title)
                .message(description)
                .smallIcon(R.drawable.pugnotification_ic_launcher)
                .largeIcon(R.drawable.pugnotification_ic_launcher)
                .flags(Notification.DEFAULT_ALL)
                .color(android.R.color.background_dark)
                .custom()
                .background(imageUrl)
                .setPlaceholder(R.drawable.pugnotification_ic_placeholder)
                .build();

    }

    @Override
    public void onNewToken(@NonNull String refreshedToken) {
        super.onNewToken(refreshedToken);
        storeRegIdInPref(refreshedToken);

        Log.d(TAG, "onNewToken: "+refreshedToken);
        // Notify UI that registration has completed, so the progress indicator can be hidden.
        Intent registrationComplete = new Intent(Config.REGISTRATION_COMPLETE);
        registrationComplete.putExtra("token", refreshedToken);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

    private void storeRegIdInPref(String token) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("regId", token);
        editor.apply();
    }

    @Override
    public void onDestroy() {
        try {
            super.onDestroy();
        } catch (Exception e) {
            Log.e(TAG, "onDestroy: ", e);
        }
    }

    private void handleNotification(String message) {
        if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {
            // app is in foreground, broadcast the push message
            Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
            pushNotification.putExtra("message", message);
            LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);

            // play notification sound
            NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
            notificationUtils.playNotificationSound();
        } else {
            // If the app is in background, firebase itself handles the notification
        }
    }





}
