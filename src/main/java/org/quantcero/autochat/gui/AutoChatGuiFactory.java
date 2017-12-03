package org.quantcero.autochat.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;


import java.util.Set;


public abstract class AutoChatGuiFactory implements IModGuiFactory {

    @Override
    public void initialize(Minecraft minecraftInstance) {
        System.out.println("Initialization of the gui factory..");
    }

    @Override
    public GuiScreen createConfigGui(GuiScreen parentScreen){
        System.out.println("Creating config gui..");
        return new AutoChatConfigGui(parentScreen);
    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }

    @Override
    public boolean hasConfigGui() {
        return true;
    }

}
