package com.gsquaredxc.hyskyAPI.events.chat;

import net.minecraft.util.IChatComponent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmptyMessageEvent extends EventChat {
    public static Pattern patternToMatch = Pattern.compile("^§r§8 §r§8 §r§1 §r§3 §r§3 §r§7 §r§8 §r$");

    public EmptyMessageEvent(final IChatComponent message) {
        super(message);
    }

    public static EmptyMessageEvent generateEvent(final IChatComponent message, final Matcher matcher) {
        return new EmptyMessageEvent(message);
    }
}
