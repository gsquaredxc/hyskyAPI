package com.gsquaredxc.hyskyAPI.utils;

import com.gsquaredxc.hyskyAPI.annotations.EventListener;
import com.gsquaredxc.hyskyAPI.events.packets.ChatMessagePacketOutEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayDeque;

public class SafeMessageSender {
    public static final SafeMessageSender SAFE_MESSAGE_SENDER = new SafeMessageSender();

    public static final Minecraft mc = Minecraft.getMinecraft();
    private static final ArrayDeque<String> sendMessageQueue = new ArrayDeque<>();
    private static long lastChatMessage = 0;

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;
        if (mc.thePlayer != null && sendMessageQueue.size() > 0 && System.currentTimeMillis() - lastChatMessage > 200) {
            String msg = sendMessageQueue.pollFirst();
            if (msg != null) {
                mc.thePlayer.sendChatMessage(msg);
            }
        }
    }

    /*Listener callback for C01 packets*/
    @EventListener(id = "INTERNALonSendPacket")
    public static boolean onSendPacket(ChatMessagePacketOutEvent event) {
        lastChatMessage = System.currentTimeMillis();
        return false;
    }

    public void queueMessage(String message){
        sendMessageQueue.add(message);
    }
}
