package com.gsquaredxc.hyskyAPI.state.location;

import java.util.UUID;

public class Location {
    /*package-private*/

    /*package-private*/ UUID areaUUID;

    /*package-private*/ boolean isDirty = true;
    /*package-private*/ boolean isOnHypixel = false;
    /*package-private*/ boolean isInSkyblock = false;
    /*package-private*/ ServerTypes serverType = ServerTypes.UNKNOWN;

    /*internal use, unused currently*/
    /*package-private*/ String server, gametype, mode, map;

    private boolean LOCK = false;

    public synchronized void lock(){
        this.LOCK = true;
    }

    public synchronized void unlock(){
        this.LOCK = false;
    }

    public synchronized boolean getLocked(){
        return this.LOCK;
    }

    public void setIsOnHypixel(final boolean isOnHypixel) {
        this.isOnHypixel = isOnHypixel;
    }

    public boolean getIsOnHypixel(){
        return this.isOnHypixel;
    }

    public boolean getIsOnSkyblock(){
        return this.isInSkyblock;
    }

    public ServerTypes getServerType(){
        return this.serverType;
    }

    public void markDirty(){
        isDirty = true;
    }

}
