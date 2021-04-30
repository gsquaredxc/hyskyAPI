package com.gsquaredxc.hyskyAPI.events.chat;

import net.minecraft.util.IChatComponent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LimboMessageEvent extends EventChat {
    public static Pattern patternToMatch = Pattern.compile("^§cYou are AFK\\. Move around to return from AFK\\.§r$");

    public LimboMessageEvent(IChatComponent message) {
        super(message);
    }

    public static LimboMessageEvent generateEvent(IChatComponent message, Matcher matcher){
        return new LimboMessageEvent(message);
    }
}
