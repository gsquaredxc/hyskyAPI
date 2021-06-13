package com.gsquaredxc.hyskyAPI.eventListeners;

import java.util.ArrayList;

public class ActiveChatListeners {
    public static ActiveChatListeners self = new ActiveChatListeners();

    private final ArrayList<EventChatListener> activeListeners = new ArrayList<>();

    public void addToChatListeners(final EventChatListener e){
        activeListeners.add(e);
    }

    public void removeFromChatListeners(final EventChatListener e) {
        activeListeners.remove(e);
    }

    public ArrayList<EventChatListener> getActiveListeners() {
        return activeListeners;
    }

}
