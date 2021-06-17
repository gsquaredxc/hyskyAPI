package com.gsquaredxc.hyskyAPI.eventListeners;

import java.util.ArrayList;

public class ActiveChatListeners {

    private static final ArrayList<EventChatListener> activeListeners = new ArrayList<>();

    public static void addToChatListeners(final EventChatListener e){
        activeListeners.add(e);
    }

    public static void removeFromChatListeners(final EventChatListener e) {
        activeListeners.remove(e);
    }

    public static ArrayList<EventChatListener> getActiveListeners() {
        return activeListeners;
    }

}
