package com.gsquaredxc.hyskyAPI.events.chat;

import net.minecraft.util.IChatComponent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MVPPSpamEvent extends EventChat {
    public static Pattern patternToMatch = Pattern.compile("^§r §b>§c>§a>§r §r§6\\[MVP§.\\+\\+§6] (?<name>.*?)§f §6joined the lobby!§r §a<§c<§b<§r$");
    String username;
    public MVPPSpamEvent(final IChatComponent message, final String username){
        super(message);
        this.username = username;
    }

    @Override
    public String toString() {
        return "MVPPSpamEvent{" +
                "originalMessage=" + originalMessage.toString() +
                ", username='" + username + '\'' +
                '}';
    }

    public static MVPPSpamEvent generateEvent(final IChatComponent message, final Matcher matcher){
        return new MVPPSpamEvent(message, matcher.group(1));
    }
}
