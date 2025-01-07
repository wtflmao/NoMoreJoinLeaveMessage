package net.mrliam2614.nomorejoinleavemessage.config.impl;


import lombok.Getter;
import lombok.Setter;
import net.mrliam2614.nomorejoinleavemessage.config.annotation.ConfigField;
import net.mrliam2614.nomorejoinleavemessage.config.annotation.ConfigFile;

@Getter
@Setter
@ConfigFile(name = "notification.yml", path = "./config/no-more-join-leave-message")
public class NotificationConfig {
    @ConfigField(path = "joinMessage")
    private boolean joinMessage = false;
    @ConfigField(path = "leaveMessage")
    private boolean leaveMessage = false;
}