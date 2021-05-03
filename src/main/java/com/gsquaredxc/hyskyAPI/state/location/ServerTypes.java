package com.gsquaredxc.hyskyAPI.state.location;

public enum ServerTypes {
    PrivateIsland(""),
    DungeonHub(""),
    UNKNOWN("");

    public final String tab;

    ServerTypes(final String tab) {
        this.tab = tab;
    }

    public static ServerTypes getFromTab(final String tab){
        for (final ServerTypes s: ServerTypes.values()){
            if (s.tab.equals(tab)){
                return s;
            }
        }
        return UNKNOWN;
    }
}
