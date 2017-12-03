package org.quantcero.autochat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiOptions;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.quantcero.autochat.config.Constants;
import org.quantcero.autochat.config.Values;


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

    @SubscribeEvent
    public void onWorldTick(TickEvent.WorldTickEvent event) {

        if (Minecraft.getSystemTime() > AutoChat.LASTTIME && AutoChat.NOTFIRSTCOMMAND && Values.AUTOMATICISACTIVE) {
            AutoChat.LASTTIME = Minecraft.getSystemTime() + (Values.AUTOCOMMANDTIMER * 60000);
            Minecraft.getMinecraft().player.sendChatMessage("/" + Values.AUTOCOMMAND);
        }
        else if (Minecraft.getSystemTime() > AutoChat.LASTTIME && ! AutoChat.NOTFIRSTCOMMAND && Values.AUTOMATICISACTIVE) {
            AutoChat.NOTFIRSTCOMMAND = true;
            AutoChat.LASTTIME = Minecraft.getSystemTime() + (Values.AUTOCOMMANDTIMER * 60000);
        }

    }

}
