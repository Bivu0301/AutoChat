package org.quantcero.autochat.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import org.quantcero.autochat.AutoChat;
import org.quantcero.autochat.config.Constants;


public class AutoChatConfigGui extends GuiConfig{

    public AutoChatConfigGui(GuiScreen parentScreen) {
        super(parentScreen, new ConfigElement(AutoChat.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), Constants.MODID, true, true, I18n.format("ac.tooltip"));
    }

}
