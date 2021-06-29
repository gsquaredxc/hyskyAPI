package com.gsquaredxc.hyskyAPI.listeners;

import com.gsquaredxc.hyskyAPI.eventListeners.ActiveChatListeners;
import com.gsquaredxc.hyskyAPI.eventListeners.EventChatListener;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;

public class ChatListener {

    @SubscribeEvent(receiveCanceled = true, priority = EventPriority.HIGHEST)
    public void onChat(final ClientChatReceivedEvent event) {
        final String formattedText = event.message.getFormattedText();
        /* Chat messages */
        if (event.type == 0) {
            //System.out.println(formattedText);
            for (final EventChatListener listener : ActiveChatListeners.getActiveListeners()) {
                try {
                    final Matcher matcher = listener.getPatternToMatch().matcher(formattedText);
                    if (matcher.find()) {
                        if (listener.eventHappens(listener.generateEvent(event.message, matcher))) {
                            event.setCanceled(true);
                            return;
                        }
                    }
                } catch (final NoSuchFieldException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e){
                    e.printStackTrace();
                }
            }
        }
    }

}
