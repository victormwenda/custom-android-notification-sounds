package com.marvik.demo.customnotificationsounds.prefs.notifications;

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
public interface AppNotificationPreferences {

    static final String MOMENTS_NOTIFICATIONS = "moments_notifications";

    static final String MOMENTS_SOUND_URI = "moments_sound_uri";

    boolean shouldSendMomentsNotifications();

    void sendMomentsNotifications(boolean sendMomentsNotifications);

    void setMomentsNotificationSound(String mediaUri);

    String getMomentsNotificationSound();

    static final String CHAT_NOTIFICATIONS = "chat_notifications";

    static final String CHAT_SOUND_URI = "chat_sound_uri";

    boolean shouldSendChatNotifications();

    void sendChatNotifications(boolean sendChatNotifications);

    void setChatNotificationSound(String mediaUri);

    String getChatNotificationSound();

}
