package com.gsquaredxc.hyskyAPI.state.location;

import com.gsquaredxc.hyskyAPI.annotations.EventListener;
import com.gsquaredxc.hyskyAPI.events.chat.LocRawEvent;
import com.gsquaredxc.hyskyAPI.events.custom.PlayerListAddEvent;
import com.gsquaredxc.hyskyAPI.events.custom.PlayerListUpdateEvent;
import com.gsquaredxc.hyskyAPI.events.custom.ServerTypeKnownEvent;
import com.gsquaredxc.hyskyAPI.events.custom.SkyblockDisconnectEvent;
import com.gsquaredxc.hyskyAPI.events.packets.PacketReceiveEvent;
import com.gsquaredxc.hyskyAPI.utils.SafeMessageSender;
import net.minecraft.network.play.server.S01PacketJoinGame;
import net.minecraft.network.play.server.S3BPacketScoreboardObjective;
import net.minecraft.network.play.server.S3EPacketTeams;
import net.minecraft.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.gsquaredxc.hyskyAPI.PrivateListeners.*;
import static com.gsquaredxc.hyskyAPI.state.PlayerStates.LocationState;

public class SLocationHelpers {
    private static final Pattern serverNamePattern = Pattern.compile("^ §7⏣ §.(.*)$");

    @EventListener(id="INTERNALrecieveLocaw")
    public static boolean receiveLocaw(final LocRawEvent event){
        if (!LocationState.getLocked() && LocationState.isDirty) {
            LocationState.lock();
            LocationState.server = event.server;
            LocationState.gametype = event.gametype;
            LocationState.mode = event.mode;
            LocationState.map = event.map;
            updateAfterLocRaw();
            LocationState.unlock();
        }
        return true;
    }

    @EventListener(id="INTERNALreceiveScoreboard")
    public static boolean receiveScoreboard(final PacketReceiveEvent<S3BPacketScoreboardObjective> event){
        if (!LocationState.getLocked() && LocationState.isDirty) {
            LocationState.lock();
            final boolean cacheState = LocationState.isInSkyblock;
            LocationState.isInSkyblock = StringUtils.stripControlCodes(event.originalPacket.func_149337_d()).contains("SKYBLOCK");
            if (!LocationState.isInSkyblock && cacheState){
                SkyblockDisconnectListenerO.eventHappens(new SkyblockDisconnectEvent());
            }
            LocationState.isDirty = false;
            LocationState.unlock();
        }
        return false;
    }

    @EventListener(id="INTERNALreceiveTeams")
    public static boolean receiveTeams(final PacketReceiveEvent<S3EPacketTeams> event){
        final S3EPacketTeams packet = event.originalPacket;
        final Matcher matcher = serverNamePattern.matcher(packet.getPrefix() + packet.getSuffix());
        if (matcher.find()){
            System.out.println(StringUtils.stripControlCodes(matcher.group(1)));
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
            LocationState.serverType = ServerTypes.getFromTab(event.displayName.getUnformattedText().replace("Area: ",""));
            if (LocationState.getServerType() != ServerTypes.UNKNOWN) {
                ServerTypeKnownListenerO.eventHappens(new ServerTypeKnownEvent());
            }
            PlayerListUpdateInListenerO.deregister("INTERNALreceivePlayerUpdate");
        }
        return false;
    }

    @EventListener(id="INTERNALjoinGame")
    public static boolean joinGame(final PacketReceiveEvent<S01PacketJoinGame> trash){
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
                sendLocraw();
                LocationState.unlock();
            }
        }).start();
        return false;
    }

    public static void sendLocraw(){
        SafeMessageSender.SAFE_MESSAGE_SENDER.queueMessage("/locraw");
    }

    public static void updateAfterLocRaw(){
        final boolean cacheState = LocationState.isInSkyblock;
        LocationState.isInSkyblock = LocationState.gametype.equals("SKYBLOCK");
        if (!LocationState.isInSkyblock && cacheState){
            SkyblockDisconnectListenerO.eventHappens(new SkyblockDisconnectEvent());
        }
        LocationState.serverType = ServerTypes.getFromLocraw(LocationState.server);
        LocationState.isDirty = false;
        System.out.println(LocationState.server+LocationState.gametype+LocationState.mode+LocationState.map+"ez");
    }

    public static void updateWithLocraw(){
        LocationState.isDirty = true;
        if (LocationState.isOnHypixel) {
            sendLocraw();
        }
    }
}
