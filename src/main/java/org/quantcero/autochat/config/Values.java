package org.quantcero.autochat.config;

import net.minecraftforge.client.settings.KeyModifier;
import org.lwjgl.input.Keyboard;


public class Values {

    public static String[] COMMANDS;
    public static final String[] COMMANDS_DEFAULT = new String[]{"help"};
    public static final String COMMANDS_NAME = "acl";

    public static int[] BINDINGSTORAGE;
    public static final int[] BINDINGSTORAGE_DEFAULT = new int[]{Keyboard.KEY_NONE, Keyboard.KEY_NONE};
    public static final String BINDINGSTORAGE_NAME = "bstorage";

    public static int[] MODIFIERS;
    public static final int[] MODIFIERS_DEFAULT = new int[]{KeyModifier.NONE.ordinal(), KeyModifier.NONE.ordinal()};
    public static final String MODIFIERS_NAME = "modifiers";

}
