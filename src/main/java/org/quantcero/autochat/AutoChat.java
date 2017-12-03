package org.quantcero.autochat;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quantcero.autochat.config.Constants;
import org.quantcero.autochat.config.Values;

import net.minecraft.client.resources.I18n;

import net.minecraftforge.common.MinecraftForge;

import net.minecraftforge.common.config.Property;
import net.minecraftforge.common.config.Configuration;

import net.minecraftforge.fml.common.Mod;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Constants.MODID,
        name = Constants.NAME,
        version = Constants.VERSION,
        clientSideOnly=true,
        guiFactory="org.quantcero.autochat.gui.AutoChatGuiFactory",
        acceptedMinecraftVersions = "[1.12.2,)",
        dependencies = "required-after:forge@[14.23.1.2557,);")
public class AutoChat {

    public static Property COMMANDS;
    public static Property BINDINGS;
    public static Property MODIFIERS;
    public static Configuration config;
    public static KeyHandler keyHandler;
    public static AutoChat instance;
    public AutoChat() {}
    public static Logger logger;

    public static void syncConfig(){
        System.out.println("Starting synchronisation..");
        Values.COMMANDS = COMMANDS.getStringList();
        Values.BINDINGSTORAGE = BINDINGS.getIntList();
        Values.MODIFIERS = MODIFIERS.getIntList();
        if(config.hasChanged()) {
            config.save();
            System.out.println("Saving config..");
        }

        if(keyHandler != null) {
            keyHandler.reload();
            System.out.println("Reloading keyhandler..");
        }
    }


    @Mod.EventHandler
    @SideOnly(Side.CLIENT)
    public void preInit(FMLPreInitializationEvent event) {

        config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        System.out.println("Loaded config..");
        COMMANDS = config.get(Configuration.CATEGORY_GENERAL, Values.COMMANDS_NAME, Values.COMMANDS_DEFAULT, I18n.format(Values.COMMANDS_NAME+".tooltip"));
        BINDINGS = config.get("hidden", Values.BINDINGSTORAGE_NAME, Values.BINDINGSTORAGE_DEFAULT);
        MODIFIERS = config.get("hidden", Values.MODIFIERS_NAME, Values.MODIFIERS_DEFAULT);
        syncConfig();

    }

    @Mod.EventHandler
    @SideOnly(Side.CLIENT)
    public void init(FMLInitializationEvent event) {

        keyHandler = new KeyHandler();
        MinecraftForge.EVENT_BUS.register(new ClientEvents());
        System.out.println("Registered new click event");
        MinecraftForge.EVENT_BUS.register(keyHandler);
        System.out.println("Registered "+ keyHandler);

    }

    @Mod.EventHandler
    @SideOnly(Side.CLIENT)
    public void postInit(FMLLoadCompleteEvent event) {

    }
}
