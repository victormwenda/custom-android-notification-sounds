package com.briansourcecode.zone.settingsdemo;

import android.content.Context;
import android.support.annotation.NonNull;

import com.marvik.libs.android.preferences.PreferencesManager;

/**
 * Created by User on 04-Nov-16.
 */
public class AppPrefsManager extends PreferencesManager implements AppNotificationPreferences {

    public AppPrefsManager(@NonNull Context context) {
        super(context);
    }

    @Override
    public boolean shouldSendMomentsNotifications() {
        return read(AppNotificationPreferences.MOMENTS_NOTIFICATIONS, Boolean.class, false);
    }

    @Override
    public void sendMomentsNotifications(boolean sendMomentsNotifications) {
        commit(AppNotificationPreferences.MOMENTS_NOTIFICATIONS, sendMomentsNotifications);
    }

    @Override
    public void setMomentsNotificationSound(String mediaUri) {
        commit(AppNotificationPreferences.MOMENTS_SOUND_URI, mediaUri);
    }

    @Override
    public String getMomentsNotificationSound() {
        return read(AppNotificationPreferences.MOMENTS_SOUND_URI, String.class, null);
    }

    @Override
    public boolean shouldSendChatNotifications() {
        return read(AppNotificationPreferences.CHAT_NOTIFICATIONS, Boolean.class, false);
    }

    @Override
    public void sendChatNotifications(boolean sendChatNotifications) {
        commit(AppNotificationPreferences.CHAT_NOTIFICATIONS, sendChatNotifications);
    }

    @Override
    public void setChatNotificationSound(String mediaUri) {
        commit(AppNotificationPreferences.CHAT_SOUND_URI, mediaUri);
    }

    @Override
    public String getChatNotificationSound() {
        return read(AppNotificationPreferences.CHAT_SOUND_URI, String.class, null);
    }
}
