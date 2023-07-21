package dev.carl.eggwars.events;

import dev.carl.eggwars.player.EWPlayer;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
@AllArgsConstructor
public class MenuOpen implements Listener {
    private final Set<UUID> shopMenu;
    private final Set<UUID> upgradeMenu;
    private final Map<UUID, EWPlayer> playerMap;

    @EventHandler
    public void onMenuOpen(PlayerInteractAtEntityEvent event) {
        Entity entity = event.getRightClicked();
        UUID entityUniqueId = entity.getUniqueId();
        Player player = event.getPlayer();
        if (event.getHand() != EquipmentSlot.HAND) {
            return;
        }

        UUID playerUniqueId = player.getUniqueId();
        EWPlayer ewPlayer = playerMap.get(playerUniqueId);
        if (ewPlayer == null) {
            return;
        }

        if (shopMenu.contains(entityUniqueId)) {
            player.openInventory(ewPlayer.getShopMenu());
            return;
        }

        if (upgradeMenu.contains(entityUniqueId)) {
            player.openInventory(ewPlayer.getTeam().upgradeMenu().getUpgradeMenu());
        }
    }

}
