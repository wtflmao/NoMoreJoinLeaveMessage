package net.mrliam2614.nomorejoinleavemessage;

import lombok.Getter;
import net.fabricmc.api.ModInitializer;
import net.mrliam2614.nomorejoinleavemessage.config.ConfigManager;
import net.mrliam2614.nomorejoinleavemessage.config.impl.NotificationConfig;

public class NoMoreJoinLeaveMessage implements ModInitializer {
    @Getter
    private static NoMoreJoinLeaveMessage instance;
    @Getter
    private NotificationConfig notificationConfig;

    @Override
    public void onInitialize() {
        instance = this;
        loadConfigs();
    }
    private void loadConfigs() {
        this.notificationConfig = ConfigManager.loadConfig(NotificationConfig.class);
    }
}
