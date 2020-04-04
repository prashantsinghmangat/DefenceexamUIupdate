package exam.defencepreparation.Services;

import android.content.Intent;


import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import exam.defencepreparation.common.Config;

/**
 * Created by hp on 12/10/2018.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        handleMessage(remoteMessage.getData().get(Config.STR_KEY));
    }

    private void handleMessage(String message) {

        Intent pushNotification = new Intent(Config.STR_PUSH);
        pushNotification.putExtra(Config.STR_MESSAGE,message);
        LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);
    }
}
