package com.gsquaredxc.hyskyAPI;

import com.gsquaredxc.hyskyAPI.commands.APILoadedCommand;
import com.gsquaredxc.hyskyAPI.eventListeners.EventRegister;
import com.gsquaredxc.hyskyAPI.listeners.ChatListener;
import com.gsquaredxc.hyskyAPI.mods.ModList;
import com.gsquaredxc.hyskyAPI.state.location.SLocationHelpers;
import com.gsquaredxc.hyskyAPI.utils.SafeMessageSender;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


@Mod(modid = HyskyAPI.MODID,name = "hyskyAPI", version = HyskyAPI.VERSION,clientSideOnly = true)
public class HyskyAPI
{
    public static final String MODID = "hyskyAPI";
    public static final String VERSION = "1.0";

    private final ModList modList = new ModList();
    
    @EventHandler
    public void preinit(final FMLPreInitializationEvent event)
    {
        PublicListeners.registerEvents();
        PrivateListeners.registerPrivate();
        ClientCommandHandler.instance.registerCommand(new APILoadedCommand());
    }

    @EventHandler
    public void init(final FMLInitializationEvent event){
        MinecraftForge.EVENT_BUS.register(new ChatListener());
        MinecraftForge.EVENT_BUS.register(new SafeMessageSender());
        //Shit event so not fucking using it.
        //MinecraftForge.EVENT_BUS.register(new WorldLoadListener());
        EventRegister.register(SafeMessageSender.class);
        EventRegister.register(SLocationHelpers.class);
        //ChatMessagePacketListenerO.register(new EventCallback(onSendPacket, "SafeMessageSender"));
    }
}
