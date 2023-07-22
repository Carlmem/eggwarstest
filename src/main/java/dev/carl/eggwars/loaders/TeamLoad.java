package dev.carl.eggwars.loaders;

import dev.carl.eggwars.item.ItemBuilder;
import dev.carl.eggwars.menu.UpgradeMenu;
import dev.carl.eggwars.player.EWPlayer;
import dev.carl.eggwars.player.Team;
import dev.carl.eggwars.util.UtilColor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class TeamLoad {
    private final Map<UUID, EWPlayer> playerMap;
    private final List<Team> gameTeams = new ArrayList<>();

    public void load() {
        World world = Bukkit.getWorld("world");
        teamCreate("Розовые", 6, new Location(world, -188, 51, 72), new UpgradeMenu(upgradeInventoryMenuCreate()));
    }

    private void teamCreate(String name, int d, Location spawnLoc, UpgradeMenu upgradeMenu) {
        gameTeams.add(new Team(
                UUID.randomUUID(),
                name,
                d,
                new ArrayList<>(),
                playerMap,
                spawnLoc,
                upgradeMenu
        ));
    }

    private Inventory upgradeInventoryMenuCreate() {
        Inventory inv = Bukkit.createInventory(null, 18, UtilColor.toColor("&6Меню Улучшений"));
        inv.addItem(sharpnessUpgrade());
        return inv;
    }

    private ItemStack sharpnessUpgrade() {
        ItemBuilder itemBuilder = new ItemBuilder();
        itemBuilder.setMaterial(Material.DIAMOND);
        itemBuilder.setAmount(1);
        itemBuilder.setName("&cОстрота");
        itemBuilder.setLore(UtilColor.toColor("&7Уровень: &c0"), UtilColor.toColor("&7Цена: &c" + 4 + " &7Ресурсов"));
        return itemBuilder.builder();
    }
}
