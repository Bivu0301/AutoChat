package org.quantcero.autochat;


import org.quantcero.autochat.config.Values;

import static org.quantcero.autochat.config.Values.BINDINGSTORAGE;
import static org.quantcero.autochat.config.Values.MODIFIERS;

import org.lwjgl.input.Keyboard;
import org.apache.commons.lang3.ArrayUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;

import net.minecraftforge.client.settings.KeyModifier;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class KeyHandler {

    private static final String description = "key.ac";
    private KeyBinding[] keys;
    byte[] keyTimer;
    boolean isLoaded = false;

    public KeyHandler() {

        keys = new KeyBinding[Values.COMMANDS.length];
        keyTimer = new byte[keys.length];
        for (int i = 0; i < Values.COMMANDS.length; ++i) {

            while (BINDINGSTORAGE.length < Values.COMMANDS.length) {
                BINDINGSTORAGE = ArrayUtils.add(BINDINGSTORAGE, Keyboard.KEY_NONE);
            }

            while (MODIFIERS.length < Values.COMMANDS.length) {
                MODIFIERS = ArrayUtils.add(MODIFIERS, KeyModifier.NONE.ordinal());
            }

            keys[i] = new KeyBinding(I18n.format(description, Values.COMMANDS[i]), BINDINGSTORAGE[i], "key.ac.category");
            keys[i].setKeyModifierAndCode(KeyModifier.values()[MODIFIERS[i]], BINDINGSTORAGE[i]);

            ClientRegistry.registerKeyBinding(keys[i]);

        }

        isLoaded = true;

    }


    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {


        for(int i=0; i < Values.COMMANDS.length;++i) {

            if (i < keys.length && i < keyTimer.length) {
                if (keyTimer[i] <= 0)
                    if (keys[i].isPressed() && (KeyModifier.values()[MODIFIERS[i]].equals(KeyModifier.NONE) || KeyModifier.values()[MODIFIERS[i]].isActive()))
                        command(Values.COMMANDS[i]);
            }

        }

    }


    public void command(String command) {

        if (Minecraft.getMinecraft().inGameHasFocus) {

            Minecraft.getMinecraft().player.sendChatMessage("/" + command);
            this.keyTimer[ArrayUtils.indexOf(Values.COMMANDS, command)] = 20;

        }

    }

    public void saveBindings() {

        int[] dummyBindings = AutoChat.BINDINGS.getIntList();
        int[] dummyModifiers = AutoChat.MODIFIERS.getIntList();

        while (dummyBindings.length < keys.length) {
            dummyBindings = ArrayUtils.add(dummyBindings, Keyboard.KEY_NONE);
        }

        while (dummyModifiers.length < keys.length) {
            dummyModifiers = ArrayUtils.add(dummyModifiers, KeyModifier.NONE.ordinal());
        }

        for (int i = 0; i < keys.length; ++i) {

            dummyBindings[i] = keys[i].getKeyCode();
            dummyModifiers[i] = keys[i].getKeyModifier().ordinal();

        }

        AutoChat.BINDINGS.set(dummyBindings);
        AutoChat.MODIFIERS.set(dummyModifiers);
        AutoChat.syncConfig();

    }

    public void reload() {

        if(isLoaded) {

            for (int i = 0; i < keys.length; i++) {
                Minecraft.getMinecraft().gameSettings.keyBindings = ArrayUtils.remove(Minecraft.getMinecraft().gameSettings.keyBindings, ArrayUtils.indexOf(Minecraft.getMinecraft().gameSettings.keyBindings, keys[i]));
            }

            keys = new KeyBinding[Values.COMMANDS.length];
            keyTimer = new byte[keys.length];
            for (int i = 0; i < keys.length; ++i) {

                while (BINDINGSTORAGE.length < Values.COMMANDS.length) {
                    BINDINGSTORAGE = ArrayUtils.add(BINDINGSTORAGE, Keyboard.KEY_NONE);
                }

                while (MODIFIERS.length < Values.COMMANDS.length) {
                    MODIFIERS = ArrayUtils.add(MODIFIERS, KeyModifier.NONE.ordinal());
                }

                keys[i] = new KeyBinding(I18n.format(description, Values.COMMANDS[i]), BINDINGSTORAGE[i], "key.ac.category");
                keys[i].setKeyModifierAndCode(KeyModifier.values()[MODIFIERS[i]], BINDINGSTORAGE[i]);

                ClientRegistry.registerKeyBinding(keys[i]);

            }

        }

    }

}