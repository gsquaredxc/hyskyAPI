package com.gsquaredxc.hyskyAPI.events.chat;

import com.gsquaredxc.hyskyAPI.eventListeners.EventCancellable;
import net.minecraft.util.IChatComponent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventChat extends EventCancellable {
    public final IChatComponent originalMessage;

    public static Pattern patternToMatch;

    public EventChat(IChatComponent message){
        this.originalMessage = message;
    }

    @Override
    public String toString() {
        return "EventChat{" +
                "originalMessage=" + originalMessage +
                '}';
    }

    public static EventChat generateEvent(IChatComponent message, Matcher matcher) {
        return new EventChat(message);
    }
}
