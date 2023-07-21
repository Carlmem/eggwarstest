package dev.carl.eggwars.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class UtilChat {
    public static void ps(String Server, CommandSender player, String msg, Object[] objects) {
        player.sendMessage(String.format(ChatColor.translateAlternateColorCodes('&', "&8&l[" + Server + "&8&l] " + msg), objects));
    }
    public static void sendMessage(CommandSender p, String msg, Object... args) {
        UtilChat.ps("&a&lBattleRoyal", p, msg, args);
    }
}
