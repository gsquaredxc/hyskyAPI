package com.gsquaredxc.hyskyAPI.eventListeners;

import java.util.ArrayList;

public class ActiveChatListeners {
    public static ActiveChatListeners self = new ActiveChatListeners();

    private final ArrayList<EventChatListener> activeListners = new ArrayList<>();

    public void addToChatListeners(EventChatListener e){
        activeListners.add(e);
    }

    public void removeFromChatListeners(EventChatListener e) {
        activeListners.remove(e);
    }

    public ArrayList<EventChatListener> getActiveListners() {
        return activeListners;
    }

}
