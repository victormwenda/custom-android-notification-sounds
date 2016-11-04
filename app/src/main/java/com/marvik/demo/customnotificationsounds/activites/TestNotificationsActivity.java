package com.marvik.demo.customnotificationsounds.activites;

import android.app.Activity;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;

import com.marvik.demo.customnotificationsounds.R;
import com.marvik.demo.customnotificationsounds.callbacks.NotificationFragmentCallbacks;
import com.marvik.demo.customnotificationsounds.fragments.NotificationFragment;
import com.marvik.demo.customnotificationsounds.prefs.AppPrefsManager;

import static com.marvik.demo.customnotificationsounds.fragments.NotificationFragment.CHAT_SOUND_REQUEST_CODE;
import static com.marvik.demo.customnotificationsounds.fragments.NotificationFragment.MOMENT_SOUND_REQUEST_CODE;

/**
 * Project - custom-android-notification-sounds
 * Package - com.marvik.demo.customnotificationsounds
 * <p>
 * Victor Mwenda
 * +254(0)718034449
 * vmwenda.vm@gmail.com
 * <p>
 * Android App Development Laptop
 * Created by victor on 11/4/2016 at 4:21 PM.
 */
public class TestNotificationsActivity extends Activity implements NotificationFragmentCallbacks {

    private AppPrefsManager appPrefsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_notifications);

        appPrefsManager = new AppPrefsManager(getApplicationContext());

        getFragmentManager().beginTransaction().replace(R.id.container, new NotificationFragment()).commit();
    }

    public AppPrefsManager getAppPrefsManager() {
        return appPrefsManager;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == Activity.RESULT_OK) {

            Uri uri = intent.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);

            if (requestCode == MOMENT_SOUND_REQUEST_CODE) {
                getAppPrefsManager().setMomentsNotificationSound(uri.toString());
            }
            if (requestCode == CHAT_SOUND_REQUEST_CODE) {
                getAppPrefsManager().setChatNotificationSound(uri.toString());
            }
        }
    }

    @Override
    public void selectNotificationRingtoneSound(int requestCode) {
        Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_NOTIFICATION);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Select Tone");
        //intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, (Uri) null);
        startActivityForResult(intent, requestCode);
    }
}
