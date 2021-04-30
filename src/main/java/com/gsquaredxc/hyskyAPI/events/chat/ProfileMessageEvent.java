package com.gsquaredxc.hyskyAPI.events.chat;

import net.minecraft.util.IChatComponent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileMessageEvent extends EventChat {
    public static Pattern patternToMatch = Pattern.compile("^§aYou are playing on profile: §e(?<fruit>.*?)(?<coop>§b \\(Co-op\\))?§r$");
    String fruit;
    boolean coop;

    public ProfileMessageEvent(final IChatComponent message, final String fruit, final boolean coop){
        super(message);
        this.fruit = fruit;
        this.coop = coop;
    }

    @Override
    public String toString() {
        return "ProfileMessageEvent{" +
                "originalMessage=" + originalMessage +
                ", fruit='" + fruit + '\'' +
                ", coop=" + coop +
                '}';
    }
    public static ProfileMessageEvent generateEvent(final IChatComponent message, final Matcher matcher){
        if (matcher.groupCount() == 2){
            return new ProfileMessageEvent(message, matcher.group(1),true);
        }
        return new ProfileMessageEvent(message, matcher.group(1), false);
    }
}
