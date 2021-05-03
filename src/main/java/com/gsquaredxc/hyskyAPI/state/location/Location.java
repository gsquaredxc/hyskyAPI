package com.gsquaredxc.hyskyAPI.state.location;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.gsquaredxc.hyskyAPI.annotations.EventListener;
import com.gsquaredxc.hyskyAPI.events.chat.EventChat;
import com.gsquaredxc.hyskyAPI.events.packets.*;
import com.gsquaredxc.hyskyAPI.utils.SafeMessageSender;
import com.gsquaredxc.hyskyAPI.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IChatComponent;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.gsquaredxc.hyskyAPI.PrivateListeners.PlayerListAddInListenerO;
import static com.gsquaredxc.hyskyAPI.PrivateListeners.PlayerListUpdateInListenerO;
import static com.gsquaredxc.hyskyAPI.state.PlayerStates.LocationState;

public class Location {
    private static final Minecraft mc = Minecraft.getMinecraft();
    private static final Pattern serverNamePattern = Pattern.compile("^ §7⏣ §.(.*)$");

    private UUID areaUUID;

    private boolean isDirty = true;
    private boolean isOnHypixel = false;
    private boolean isInSkyblock = false;
    private ServerTypes serverType;

    private String server, gametype, mode, map;

    private boolean LOCK = false;

    public Location(){

    }

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

    public void markDirty(){
        isDirty = true;
    }

    public void updateWithLocraw(){
        isDirty = true;
        if (isOnHypixel) {
            sendLocraw();
        }
    }

    @EventListener(id="INTERNALjoinGame")
    public static boolean joinGame(final JoinGameInEvent trash){
        PlayerListAddInListenerO.reregister("INTERNALreceivePlayerAdd");
        LocationState.markDirty();
            new Thread(()-> {
                try {
                    Thread.sleep(5000);
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }
                if (!LocationState.getLocked() && LocationState.isDirty && LocationState.isOnHypixel){
                    LocationState.lock();
                    LocationState.sendLocraw();
                    LocationState.unlock();
                }
            }).start();
        return false;
    }

    public void updateAfterLocRaw(){
        if (gametype.equals("SKYBLOCK")){
            isInSkyblock = true;
        }
        isDirty = false;
        System.out.println(server+gametype+mode+map+"ez");
    }

    public void sendLocraw(){
        SafeMessageSender.SAFE_MESSAGE_SENDER.queueMessage("/locraw");
    }

    @EventListener(id="INTERNALrecieveLocaw")
    public static boolean receiveLocaw(final LocRawEvent event){
        if (!LocationState.getLocked() && LocationState.isDirty) {
            LocationState.lock();
            LocationState.server = event.server;
            LocationState.gametype = event.gametype;
            LocationState.mode = event.mode;
            LocationState.map = event.map;
            LocationState.updateAfterLocRaw();
            LocationState.unlock();
        }
        return true;
    }

    @EventListener(id="INTERNALreceiveScoreboard")
    public static boolean receiveScoreboard(final ScoreboardObjectiveInEvent event){
        if (!LocationState.getLocked() && LocationState.isDirty) {
            LocationState.lock();
            LocationState.isInSkyblock = event.unformattedValue.contains("SKYBLOCK");
            LocationState.isDirty = false;
            LocationState.unlock();
        }
        return false;
    }

    @EventListener(id="INTERNALreceiveTeams")
    public static boolean receiveTeams(final ScoreboardTeamInEvent event){
        final Matcher matcher = serverNamePattern.matcher(event.unformattedValue);
        if (matcher.find()){
            System.out.println(Utils.removeFormatting(matcher.group(1)));
        }
        return false;
    }

    @EventListener(id="INTERNALreceivePlayerAdd")
    public static boolean receivePlayerAdd(final PlayerListAddEvent event){
        if (event.username.equals("!C-b")){
            LocationState.areaUUID = event.UUID;
            PlayerListAddInListenerO.deregister("INTERNALreceivePlayerAdd");
            PlayerListUpdateInListenerO.reregister("INTERNALreceivePlayerUpdate");
        }
        return false;
    }

    @EventListener(id="INTERNALreceivePlayerUpdate")
    public static boolean receivePlayerUpdate(final PlayerListUpdateEvent event){
        if (event.UUID.equals(LocationState.areaUUID)){
            LocationState.isDirty = false;
            LocationState.serverType = ServerTypes.getFromTab(event.displayName.getUnformattedText());
            PlayerListUpdateInListenerO.deregister("INTERNALreceivePlayerUpdate");
        }
        return false;
    }

    public static class LocRawEvent extends EventChat {
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
}
