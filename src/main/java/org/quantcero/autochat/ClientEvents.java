package org.quantcero.autochat;

import net.minecraft.client.gui.GuiOptions;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.quantcero.autochat.config.Constants;


public class ClientEvents {

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {

        if (eventArgs.getModID().equals(Constants.MODID)) {
            AutoChat.syncConfig();
        }

    }

    @SubscribeEvent
    public void guiScreenEvent(GuiScreenEvent event) {

        if (event.getGui() instanceof GuiOptions) {
            AutoChat.keyHandler.saveBindings();
        }

    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {

        for (int i = 0; i < AutoChat.keyHandler.keyTimer.length; i++) {
            if (AutoChat.keyHandler.keyTimer[i] > 0){
                AutoChat.keyHandler.keyTimer[i]--;
            }
        }

    }

}
