package dev.carl.eggwars.events;

import dev.carl.eggwars.menu.UpgradeMenu;
import dev.carl.eggwars.player.EWPlayer;
import dev.carl.eggwars.player.Team;
import dev.carl.eggwars.util.UtilColor;
import lombok.AllArgsConstructor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;
import java.util.List;

@AllArgsConstructor
public class MenuClick implements Listener {
    private final Map<UUID, EWPlayer> playerMap;
    private static final int SHARPNESS = 0;

    @EventHandler
    public void onClickMenu(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        EWPlayer ewPlayer = playerMap.get(player.getUniqueId());
        if (ewPlayer == null) {
            return;
        }

        Inventory inventory = event.getInventory();
        ItemStack itemStack = event.getCurrentItem();
        int slot = event.getSlot();
        if (itemStack == null) {
            return;
        }

        if (inventory.equals(ewPlayer.getTeam().upgradeMenu().getUpgradeMenu())) {
            event.setCancelled(true);
            upgradeInventory(slot, itemStack, ewPlayer);
            return;
        }

        if (inventory.equals(ewPlayer.getShopMenu())) {

        }

    }

    private void upgradeInventory(int slot, ItemStack itemStack, EWPlayer ewPlayer) {
        Player player = ewPlayer.getPlayer();
        if (slot == SHARPNESS) {
            onSharpnessUpgrade(ewPlayer, itemStack, player);
            return;
        }


    }

    private void onSharpnessUpgrade(EWPlayer ewPlayer, ItemStack itemStack, Player player) {
        UpgradeMenu upgradeMenu = ewPlayer.getTeam().upgradeMenu();
        int playerLevel = player.getLevel();
        if (playerLevel < upgradeMenu.getSharpnessPrice()) {
            return;
        }

        int price = upgradeMenu.getSharpnessPrice();
        player.setLevel(playerLevel - price);
        int newLevel = upgradeMenu.getSharpnessLevel() + 1;
        int newPrice = price * 2;

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(List.of(UtilColor.toColor("&7Уровень: &c" + newLevel), UtilColor.toColor("&7Цена: &c" + newPrice * 2 + " &7Ресурсов")));
        itemStack.setItemMeta(itemMeta);

        upgradeMenu.setSharpnessLevel(newLevel);
        upgradeMenu.setSharpnessPrice(newPrice);
        sharpnessUpdate(ewPlayer.getTeam(), newLevel);
    }

    private void sharpnessUpdate(Team team, int newLevel) {
        for (EWPlayer alivePlayer: team.getAlivePlayers()) {
            Inventory playerInventory = alivePlayer.getPlayer().getInventory();

            for (ItemStack item : playerInventory.getContents()) {
                if (item == null) {
                    continue;
                }

                if (item.getType() != Material.DIAMOND_SWORD) {
                    continue;
                }

                ItemMeta meta = item.getItemMeta();
                meta.addEnchant(Enchantment.DAMAGE_ALL, newLevel, true);
                item.setItemMeta(meta);
            }
        }
    }

}
