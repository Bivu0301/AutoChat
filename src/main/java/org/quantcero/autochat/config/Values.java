package org.quantcero.autochat.config;

import net.minecraftforge.client.settings.KeyModifier;
import org.lwjgl.input.Keyboard;


public class Values {

    public static String[] COMMANDS;
    public static final String[] COMMANDS_DEFAULT = new String[]{"help", "gamemode 0", "gamemode 1"};
    public static final String COMMANDS_NAME = "acl";

    public static int[] BINDINGSTORAGE;
    public static final int[] BINDINGSTORAGE_DEFAULT = new int[]{Keyboard.KEY_NONE, Keyboard.KEY_NONE};
    public static final String BINDINGSTORAGE_NAME = "bstorage";

    public static int[] MODIFIERS;
    public static final int[] MODIFIERS_DEFAULT = new int[]{KeyModifier.NONE.ordinal(), KeyModifier.NONE.ordinal()};
    public static final String MODIFIERS_NAME = "modifiers";

    public static String AUTOCOMMAND;
    public static final String AUTOCOMMAND_DEFAULT = "help";
    public static final String AUTOCOMMAND_NAME = "acc";

    public static Integer AUTOCOMMANDTIMER;
    public static final Integer AUTOCOMMANDTIMER_DEFAULT = 5;
    public static final String AUTOCOMMANDTIMER_NAME = "acct";

    public static Boolean AUTOMATICISACTIVE;
    public static final Boolean AUTOMATICISACTIVE_DEFAULT = true;
    public static final String AUTOMATICISACTIVE_NAME = "acca";


}
