package dev.carl.eggwars.events;

import dev.carl.eggwars.item.ItemBuilder;
import dev.carl.eggwars.loaders.ResourceSpawnerLoad;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class DropPickUp implements Listener {
    @EventHandler
    public void onPickUp(PlayerPickupItemEvent e) {
        Player player = e.getPlayer();
        Item item = e.getItem();
        ItemStack itemStack = item.getItemStack();
        int resource = ItemBuilder.getNbt(itemStack).getInt("resource");
        if (resource == 0) {
            return;
        }

        e.setCancelled(true);
        item.remove();
        player.setLevel(player.getLevel() + resource * itemStack.getAmount());
    }
}
