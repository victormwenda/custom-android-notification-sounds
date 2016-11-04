package com.briansourcecode.zone.settingsdemo;

import android.app.Activity;
import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;


/**
 * Created by User on 04-Nov-16.
 */
public class NotificationFragment extends Fragment {

    NotificationFragmentCallbacks callbacks;

    public static final int CHAT_SOUND_REQUEST_CODE;
    public static final int MOMENT_SOUND_REQUEST_CODE;

    private AppPrefsManager appPrefsManager;

    private SwitchCompat sChat;
    private SwitchCompat sMoment;

    private LinearLayout llChat;
    private LinearLayout llMoment;

    private Button btChatsNotification;
    private Button btMomentsNotification;

    private AppCompatTextView tvChatSound;
    private AppCompatTextView tvMomentsSound;
    private AppCompatButton btSendNotification;

    static {
        CHAT_SOUND_REQUEST_CODE = 0x0001;
        MOMENT_SOUND_REQUEST_CODE = 0X0002;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appPrefsManager = new AppPrefsManager(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        sChat = (SwitchCompat) view.findViewById(R.id.fragment_notifications_switch_chat_enable);
        sMoment = (SwitchCompat) view.findViewById(R.id.fragment_notifications_switch_moments_enable);

        llChat = (LinearLayout) view.findViewById(R.id.ll_chat_prefs);
        llMoment = (LinearLayout) view.findViewById(R.id.ll_moments_prefs);

        btChatsNotification = (Button) view.findViewById(R.id.button_chat_notification);
        btMomentsNotification = (Button) view.findViewById(R.id.button_moments_notification);


        tvChatSound = (AppCompatTextView) view.findViewById(R.id.fragment_notifications_textView_chat_sound);
        tvMomentsSound = (AppCompatTextView) view.findViewById(R.id.fragment_notifications_textView_moments_sound);

        btSendNotification = (AppCompatButton) view.findViewById(R.id.button_send_notification);

        btSendNotification.setOnClickListener(onClickView);
        tvChatSound.setOnClickListener(onClickView);
        tvMomentsSound.setOnClickListener(onClickView);

        btChatsNotification.setOnClickListener(onClickView);
        btMomentsNotification.setOnClickListener(onClickView);

        sMoment.setOnCheckedChangeListener(onCheckChange);
        sChat.setOnCheckedChangeListener(onCheckChange);

        updateUI(false);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callbacks = (NotificationFragmentCallbacks) getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public AppPrefsManager getAppPrefsManager() {
        return appPrefsManager;
    }

    private void updateUI(boolean calledFromCallback) {

        if (!calledFromCallback) {
            sChat.setChecked(getAppPrefsManager().shouldSendChatNotifications());
            sMoment.setChecked(getAppPrefsManager().shouldSendMomentsNotifications());
        }

        llChat.setEnabled(getAppPrefsManager().shouldSendChatNotifications());
        llMoment.setEnabled(getAppPrefsManager().shouldSendMomentsNotifications());
    }

    private CompoundButton.OnCheckedChangeListener onCheckChange = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean enabled) {
            if (compoundButton == sChat) {
                getAppPrefsManager().sendChatNotifications(enabled);
            }
            if (compoundButton == sMoment) {
                getAppPrefsManager().sendMomentsNotifications(enabled);
            }
            updateUI(true);
        }
    };
    private View.OnClickListener onClickView = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == btSendNotification) {
                sendChatNotification();
                sendMomentNotification();
            }
            if (view == btChatsNotification) {
                sendChatNotification();
            }
            if (view == btMomentsNotification) {
                sendMomentNotification();
            }
            if (view == tvChatSound) {
                sendChooseNotificationToneIntent(CHAT_SOUND_REQUEST_CODE);
            }
            if (view == tvMomentsSound) {
                sendChooseNotificationToneIntent(MOMENT_SOUND_REQUEST_CODE);
            }
        }
    };

    private void sendChatNotification() {
        if (!getAppPrefsManager().shouldSendChatNotifications()) {
            Toast.makeText(getActivity(), "Enable chat notifications", Toast.LENGTH_LONG).show();
            return;
        }
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getActivity());
        notificationBuilder.mContentTitle = "Chat Title";
        notificationBuilder.mContentText = "This is the chat message";
        if (getAppPrefsManager().getChatNotificationSound() != null) {
            notificationBuilder.setSound(Uri.parse(getAppPrefsManager().getChatNotificationSound()));
        }
        notificationBuilder.mLargeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        sendNotification(notificationBuilder.build(), 1);
    }

    private void sendMomentNotification() {
        if (!getAppPrefsManager().shouldSendMomentsNotifications()) {
            Toast.makeText(getActivity(), "Enable moments notifications", Toast.LENGTH_LONG).show();
            return;
        }
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getActivity());
        notificationBuilder.mContentTitle = "Moment Title";
        notificationBuilder.mContentText = "This is the moment message";
        if (getAppPrefsManager().getMomentsNotificationSound() != null) {
            notificationBuilder.setSound(Uri.parse(getAppPrefsManager().getMomentsNotificationSound()));
        }
        notificationBuilder.mLargeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        sendNotification(notificationBuilder.build(), 2);
    }

    private void sendNotification(Notification notification, int id) {
        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id, notification);
    }

    private void sendChooseNotificationToneIntent(int requestCode) {
        callbacks.selectNotificationRingtoneSound(requestCode);
    }
}
