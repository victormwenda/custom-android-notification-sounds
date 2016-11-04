package com.briansourcecode.zone.settingsdemo;

import android.app.Activity;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;

import static com.briansourcecode.zone.settingsdemo.NotificationFragment.MOMENT_SOUND_REQUEST_CODE;
import static com.briansourcecode.zone.settingsdemo.NotificationFragment.CHAT_SOUND_REQUEST_CODE;

/**
 * Created by User on 04-Nov-16.
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
