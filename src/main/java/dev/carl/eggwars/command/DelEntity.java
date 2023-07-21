package dev.carl.eggwars.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class DelEntity extends AbstractCommand {

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        if (args.length != 0) {
            return;
        }

        if (!(sender instanceof Player player)) {
            return;
        }

        player.getWorld().getEntities().forEach(Entity::remove);
    }
}
