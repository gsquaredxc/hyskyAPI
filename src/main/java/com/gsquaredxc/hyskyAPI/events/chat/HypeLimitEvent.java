package com.gsquaredxc.hyskyAPI.events.chat;

import net.minecraft.util.IChatComponent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HypeLimitEvent extends EventChat {

    public static Pattern patternToMatch = Pattern.compile("^§r  §r§f§l➤ §r§6You have reached your Hype limit! Add Hype to Prototype Lobby minigames by right-clicking with the Hype Diamond!§r$");

    public static HypeLimitEvent generateEvent(final IChatComponent message, final Matcher matcher){
        return new HypeLimitEvent(message);
    }

    public HypeLimitEvent(final IChatComponent message) {
        super(message);
    }

}
