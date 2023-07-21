package dev.carl.eggwars.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public abstract class AbstractCommand implements CommandExecutor {

    public abstract void execute(CommandSender commandSender, String label, String[] args);

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        this.execute(sender, label, args);
        return true;
    }
}
