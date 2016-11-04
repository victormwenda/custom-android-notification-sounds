package com.marvik.demo.customnotificationsounds.prefs;

import android.content.Context;
import android.support.annotation.NonNull;

import com.marvik.demo.customnotificationsounds.prefs.notifications.AppNotificationPreferences;
import com.marvik.libs.android.preferences.PreferencesManager;

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
public class AppPrefsManager extends PreferencesManager implements AppNotificationPreferences {

    public AppPrefsManager(@NonNull Context context) {
        super(context);
    }

    @Override
    public boolean shouldSendMomentsNotifications() {
        return read(MOMENTS_NOTIFICATIONS, Boolean.class, false);
    }

    @Override
    public void sendMomentsNotifications(boolean sendMomentsNotifications) {
        commit(MOMENTS_NOTIFICATIONS, sendMomentsNotifications);
    }

    @Override
    public void setMomentsNotificationSound(String mediaUri) {
        commit(MOMENTS_SOUND_URI, mediaUri);
    }

    @Override
    public String getMomentsNotificationSound() {
        return read(MOMENTS_SOUND_URI, String.class, null);
    }

    @Override
    public boolean shouldSendChatNotifications() {
        return read(CHAT_NOTIFICATIONS, Boolean.class, false);
    }

    @Override
    public void sendChatNotifications(boolean sendChatNotifications) {
        commit(CHAT_NOTIFICATIONS, sendChatNotifications);
    }

    @Override
    public void setChatNotificationSound(String mediaUri) {
        commit(CHAT_SOUND_URI, mediaUri);
    }

    @Override
    public String getChatNotificationSound() {
        return read(CHAT_SOUND_URI, String.class, null);
    }
}
