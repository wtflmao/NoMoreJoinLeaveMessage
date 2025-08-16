package net.mrliam2614.nomorejoinleavemessage.mixin;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.mrliam2614.nomorejoinleavemessage.NoMoreJoinLeaveMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Function;

@Mixin(PlayerList.class)
public class PlayerConnectionMixin {
    @Inject(method = {"broadcastSystemMessage(Lnet/minecraft/network/chat/Component;Ljava/util/function/Function;Z)V"}, at = {@At("HEAD")}, cancellable = true)
    public void broadcastSystemMessage(Component component, Function<ServerPlayer, Component> function, boolean bl, CallbackInfo ci) {
        String messageText = component.getString();
        if (component.getContents() instanceof TranslatableContents translatableContents) {
            String key = translatableContents.getKey();
            if (key.equals("multiplayer.player.joined") && !NoMoreJoinLeaveMessage.getInstance().getNotificationConfig().isJoinMessage()) {
                ci.cancel();
            } else if (key.equals("multiplayer.player.left") && !NoMoreJoinLeaveMessage.getInstance().getNotificationConfig().isLeaveMessage()) {
                ci.cancel();
            }
        }
        else {
            if (messageText.contains("joined the game") && !NoMoreJoinLeaveMessage.getInstance().getNotificationConfig().isJoinMessage()) {
                ci.cancel();
            } else if (messageText.contains("left the game") && !NoMoreJoinLeaveMessage.getInstance().getNotificationConfig().isLeaveMessage()) {
                ci.cancel();
            }
            if (messageText.contains("加入了游戏") && !NoMoreJoinLeaveMessage.getInstance().getNotificationConfig().isJoinMessage()) {
                ci.cancel();
            } else if (messageText.contains("退出了游戏") && !NoMoreJoinLeaveMessage.getInstance().getNotificationConfig().isLeaveMessage()) {
                ci.cancel();
            }
        }
    }
}