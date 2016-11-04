package com.briansourcecode.zone.settingsdemo;

/**
 * Created by User on 04-Nov-16.
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
