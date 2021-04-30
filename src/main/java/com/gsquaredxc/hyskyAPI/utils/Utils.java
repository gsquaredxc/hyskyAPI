package com.gsquaredxc.hyskyAPI.utils;

import com.gsquaredxc.hyskyAPI.eventListeners.EventPacketSendListener;
import com.gsquaredxc.hyskyAPI.events.packets.ChatMessagePacketOutEvent;
import com.gsquaredxc.hyskyAPI.events.packets.PacketSendEvent;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C01PacketChatMessage;

import java.util.regex.Pattern;

public class Utils {
    public static Pattern specialFormattingCharacters = Pattern.compile("§[a-f0-9k-r]");

    public static boolean isModConnected(){
        return true;
    }

    public static String removeFormatting(final String toRemove){
        return specialFormattingCharacters.matcher(toRemove).replaceAll("");
    }
}
