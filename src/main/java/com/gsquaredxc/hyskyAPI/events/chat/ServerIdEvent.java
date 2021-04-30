package com.gsquaredxc.hyskyAPI.events.chat;

import net.minecraft.util.IChatComponent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerIdEvent extends EventChat {
    public static Pattern patternToMatch = Pattern.compile("^§7Sending to server (?<servername>.*?)\\.\\.\\.§r$");

    String serverId;

    public ServerIdEvent(final IChatComponent message, final String serverId){
        super(message);
        this.serverId = serverId;
    }

    @Override
    public String toString() {
        return "MVPSpamEvent{" +
                "originalMessage=" + originalMessage.toString() +
                ", serverID='" + serverId + '\'' +
                '}';
    }
    public static ServerIdEvent generateEvent(final IChatComponent message, final Matcher matcher){
        return new ServerIdEvent(message, matcher.group(1));
    }
}
