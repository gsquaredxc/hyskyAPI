package com.gsquaredxc.hyskyAPI.listeners;

import com.gsquaredxc.hyskyAPI.eventListeners.ActiveChatListeners;
import com.gsquaredxc.hyskyAPI.eventListeners.EventChatListener;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;

public class ChatListener {
    public static Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent(receiveCanceled = true, priority = EventPriority.HIGHEST)
    public void onChat(ClientChatReceivedEvent event) {
        String formattedText = event.message.getFormattedText();
        /* Chat messages */
        if (event.type == 0) {
            System.out.println(formattedText);
            for (EventChatListener listener : ActiveChatListeners.self.getActiveListners()) {
                try {
                    Matcher matcher = listener.getPatternToMatch().matcher(formattedText);
                    if (matcher.find()) {
                        if (listener.eventHappens(listener.generateEvent(event.message, matcher))) {
                            event.setCanceled(true);
                            return;
                        }
                    }
                } catch (NoSuchFieldException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e){
                    e.printStackTrace();
                }
            }
        }
    }

}
