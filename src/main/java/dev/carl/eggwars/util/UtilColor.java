package dev.carl.eggwars.util;

import org.bukkit.ChatColor;

public class UtilColor {
    
    public static String toColor(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String toColor(String message, Object... args) {
        return args != null && args.length != 0 ? toColor(String.format(message, args)) : toColor(message);
    }
}
