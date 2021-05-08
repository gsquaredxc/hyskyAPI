package com.gsquaredxc.hyskyAPI.events.chat;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraft.util.IChatComponent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LocRawEvent extends EventChat {
    public static Pattern patternToMatch = Pattern.compile("^§f(\\{.+?})§r$");

    public static LocRawEvent generateEvent(final IChatComponent message, final Matcher matcher) {
        try {
            System.out.println(matcher.group(1));
            final JsonObject obj = new Gson().fromJson(matcher.group(1), JsonObject.class);
            if(obj.has("server")) {
                final String server = obj.get("server").getAsString();
                final String gametype = obj.get("gametype").getAsString();
                if(obj.has("mode") && obj.has("map")) {
                    final String mode = obj.get("mode").getAsString();
                    final String map = obj.get("map").getAsString();
                    return new LocRawEvent(message, server, gametype, mode, map);
                } else {
                    return new LocRawEvent(message, server, gametype);
                }
            }
        } catch(final Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String server, gametype, mode, map;

    public LocRawEvent(final IChatComponent message, final String server, final String gametype, final String mode, final String map) {
        super(message);
        this.server = server;
        this.gametype = gametype;
        this.mode = mode;
        this.map = map;
    }
    public LocRawEvent(final IChatComponent message, final String server, final String gametype) {
        super(message);
        this.server = server;
        this.gametype = gametype;
    }
}
