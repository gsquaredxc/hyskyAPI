package com.gsquaredxc.hyskyAPI.events.chat;

import net.minecraft.util.IChatComponent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MVPSpamEvent extends EventChat {
    public static Pattern patternToMatch = Pattern.compile("^§r§b\\[MVP§.\\+§b] (?<name>.*?)§f §6joined the lobby!§r$");
    String username;

    public MVPSpamEvent(IChatComponent message, String username){
        super(message);
        this.username = username;
    }

    @Override
    public String toString() {
        return "MVPSpamEvent{" +
                "originalMessage=" + originalMessage.toString() +
                ", username='" + username + '\'' +
                '}';
    }

    public static MVPSpamEvent generateEvent(IChatComponent message, Matcher matcher){
        return new MVPSpamEvent(message, matcher.group(1));
    }
}
