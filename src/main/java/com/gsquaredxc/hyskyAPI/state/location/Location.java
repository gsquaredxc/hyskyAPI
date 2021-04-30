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

    private boolean isUpdated = false;
    private boolean isOnHypixel = false;
    private boolean isInSkyblock = false;
    public String server, gametype, mode, map;

    public String serverPlayer;

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

    public void setIsOnHypixel(boolean isOnHypixel) {
        this.isOnHypixel = isOnHypixel;
    }

    public void update(){
        isUpdated = false;
        isOnHypixel = getIsOnHypixel();
    }

    public void updateWithLocraw(){
        isUpdated = false;
        isOnHypixel = getIsOnHypixel();
        if (isOnHypixel) {
            sendLocraw();
        }
    }

    @EventListener(id="INTERNALjoinGame")
    public static boolean joinGame(JoinGameInEvent trash){
        PlayerListAddInListenerO.reregister("INTERNALreceivePlayerAdd");
        LocationState.update();
            new Thread(()-> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!LocationState.getLocked() && !LocationState.isUpdated){
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
        isUpdated = true;
        System.out.println(server+gametype+mode+map+"ez");
    }

    public static boolean getIsOnHypixel() {
        try {
            if (mc != null && mc.theWorld != null && !mc.isSingleplayer()) {
                if (mc.thePlayer != null && mc.thePlayer.getClientBrand() != null) {
                    if (mc.thePlayer.getClientBrand().toLowerCase().contains("hypixel")) return true;
                }
                if (mc.getCurrentServerData() != null) return mc.getCurrentServerData().serverIP.toLowerCase().contains("hypixel");
            }
            return false;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void sendLocraw(){
        SafeMessageSender.SAFE_MESSAGE_SENDER.queueMessage("/locraw");
    }

    @EventListener(id="INTERNALrecieveLocaw")
    public static boolean receiveLocaw(LocRawEvent event){
        if (!LocationState.getLocked() && !LocationState.isUpdated) {
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
    public static boolean receiveScoreboard(ScoreboardObjectiveInEvent event){
        if (!LocationState.getLocked() && !LocationState.isUpdated) {
            LocationState.lock();
            LocationState.isInSkyblock = event.unformattedValue.contains("SKYBLOCK");
            LocationState.isUpdated = true;
            LocationState.unlock();
        }
        return false;
    }

    @EventListener(id="INTERNALreceiveTeams")
    public static boolean receiveTeams(ScoreboardTeamInEvent event){
        Matcher matcher = serverNamePattern.matcher(event.unformattedValue);
        if (matcher.find()){
            System.out.println(Utils.removeFormatting(matcher.group(1)));
        }
        return false;
    }

    @EventListener(id="INTERNALreceivePlayerAdd")
    public static boolean receivePlayerAdd(PlayerListAddEvent event){
        if (event.username.equals("!C-b")){
            LocationState.areaUUID = event.UUID;
            LocationState.serverPlayer = event.displayName.getUnformattedText();
            PlayerListAddInListenerO.deregister("INTERNALreceivePlayerAdd");
            PlayerListUpdateInListenerO.reregister("INTERNALreceivePlayerUpdate");
        }
        return false;
    }

    @EventListener(id="INTERNALreceivePlayerUpdate")
    public static boolean receivePlayerUpdate(PlayerListUpdateEvent event){
        if (event.UUID.equals(LocationState.areaUUID)){
            LocationState.serverPlayer = event.displayName.getUnformattedText();
            LocationState.isUpdated = true;
            PlayerListUpdateInListenerO.deregister("INTERNALreceivePlayerUpdate");
        }
        return false;
    }

    public static class LocRawEvent extends EventChat {
        public static Pattern patternToMatch = Pattern.compile("^§f(\\{.+?})§r$");

        public static LocRawEvent generateEvent(IChatComponent message, Matcher matcher) {
            try {
                System.out.println(matcher.group(1));
                JsonObject obj = new Gson().fromJson(matcher.group(1), JsonObject.class);
                if(obj.has("server")) {
                    String server = obj.get("server").getAsString();
                    String gametype = obj.get("gametype").getAsString();
                    if(obj.has("mode") && obj.has("map")) {
                        String mode = obj.get("mode").getAsString();
                        String map = obj.get("map").getAsString();
                        return new LocRawEvent(message, server, gametype, mode, map);
                    } else {
                        return new LocRawEvent(message, server, gametype);
                    }
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        public String server, gametype, mode, map;

        public LocRawEvent(IChatComponent message, String server, String gametype, String mode, String map) {
            super(message);
            this.server = server;
            this.gametype = gametype;
            this.mode = mode;
            this.map = map;
        }
        public LocRawEvent(IChatComponent message, String server, String gametype) {
            super(message);
            this.server = server;
            this.gametype = gametype;
        }
    }
}
