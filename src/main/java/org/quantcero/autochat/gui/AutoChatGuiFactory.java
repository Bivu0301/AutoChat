package org.quantcero.autochat.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;
import org.quantcero.autochat.AutoChat;
import org.quantcero.autochat.config.Constants;

import java.util.Set;


public abstract class AutoChatGuiFactory implements IModGuiFactory {

    @Override
    public void initialize(Minecraft minecraftInstance) {}

    @Override
    public GuiScreen createConfigGui(GuiScreen parentScreen){
        return new ConfigGui(parentScreen);
    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }

    @Override
    public boolean hasConfigGui() {
        return true;
    }

    public static class ConfigGui extends GuiConfig {

        public ConfigGui(GuiScreen parent) {
            super(parent, new ConfigElement(AutoChat.instance.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
                    Constants.MODID, false, false,
                    GuiConfig.getAbridgedConfigPath(AutoChat.instance.config.toString()));
        }

    }

}
