package com.gsquaredxc.hyskyAPI.events.chat;

import net.minecraft.util.IChatComponent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MiningSpeedBoostEvent extends EventChat {
    public static Pattern patternToMatch = Pattern.compile("^§r§6Mining Speed Boost §r§ais now available!§r$");

    public MiningSpeedBoostEvent(IChatComponent message) {
        super(message);
    }

    public static MiningSpeedBoostEvent generateEvent(IChatComponent message, Matcher matcher) {
        return new MiningSpeedBoostEvent(message);
    }
}
