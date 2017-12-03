package org.quantcero.autochat;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
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
    public static Property AUTOCOMMAND;
    public static Property AUTOCOMMANDTIMER;
    public static Property AUTOMATICISACTIVE;
    public static Configuration config;
    public static KeyHandler keyHandler;
    public static Long LASTTIME = Minecraft.getSystemTime();
    public static Boolean NOTFIRSTCOMMAND = false;


    public static void syncConfig(){
        Values.COMMANDS = COMMANDS.getStringList();
        Values.BINDINGSTORAGE = BINDINGS.getIntList();
        Values.MODIFIERS = MODIFIERS.getIntList();
        Values.AUTOCOMMAND = AUTOCOMMAND.getString();
        Values.AUTOCOMMANDTIMER = AUTOCOMMANDTIMER.getInt();
        Values.AUTOMATICISACTIVE = AUTOMATICISACTIVE.getBoolean();
        if(config.hasChanged()) {
            config.save();
        }

        if(keyHandler != null) {
            keyHandler.reload();
        }
    }


    @Mod.EventHandler
    @SideOnly(Side.CLIENT)
    public void preInit(FMLPreInitializationEvent event) {

        config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        COMMANDS = config.get(Configuration.CATEGORY_GENERAL, Values.COMMANDS_NAME, Values.COMMANDS_DEFAULT, I18n.format(Values.COMMANDS_NAME+".tooltip"));
        BINDINGS = config.get("hidden", Values.BINDINGSTORAGE_NAME, Values.BINDINGSTORAGE_DEFAULT);
        MODIFIERS = config.get("hidden", Values.MODIFIERS_NAME, Values.MODIFIERS_DEFAULT);
        AUTOCOMMAND = config.get("autocommand", Values.AUTOCOMMAND_NAME, Values.AUTOCOMMAND_DEFAULT);
        AUTOCOMMANDTIMER = config.get("autocommand", Values.AUTOCOMMANDTIMER_NAME, Values.AUTOCOMMANDTIMER_DEFAULT);
        AUTOMATICISACTIVE = config.get("autocommand", Values.AUTOMATICISACTIVE_NAME, Values.AUTOMATICISACTIVE_DEFAULT);
        syncConfig();

    }

    @Mod.EventHandler
    @SideOnly(Side.CLIENT)
    public void init(FMLInitializationEvent event) {

        keyHandler = new KeyHandler();
        MinecraftForge.EVENT_BUS.register(new ClientEvents());
        MinecraftForge.EVENT_BUS.register(keyHandler);
    }

    @Mod.EventHandler
    @SideOnly(Side.CLIENT)
    public void postInit(FMLLoadCompleteEvent event) {


    }
}
