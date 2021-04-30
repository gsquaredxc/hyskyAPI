package com.gsquaredxc.hyskyAPI.events.chat;

import net.minecraft.util.IChatComponent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmptyMessageEvent extends EventChat {
    public static Pattern patternToMatch = Pattern.compile("^§r§8 §r§8 §r§1 §r§3 §r§3 §r§7 §r§8 §r$");

    public EmptyMessageEvent(IChatComponent message) {
        super(message);
    }

    public static EmptyMessageEvent generateEvent(IChatComponent message, Matcher matcher) {
        return new EmptyMessageEvent(message);
    }
}
