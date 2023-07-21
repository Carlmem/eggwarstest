package dev.carl.eggwars.loaders;

import dev.carl.eggwars.Main;
import dev.carl.eggwars.events.DropPickUp;
import dev.carl.eggwars.events.Join;
import dev.carl.eggwars.events.MenuClick;
import dev.carl.eggwars.events.MenuOpen;
import dev.carl.eggwars.player.EWPlayer;
import dev.carl.eggwars.player.Team;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
@AllArgsConstructor
public class EventLoader {
    private final Set<UUID> shopMenu;
    private final Set<UUID> upgradeMenu;
    private final Map<UUID, EWPlayer> playerMap;
    private final List<Team> teams;

    public void load() {
        register(
                new DropPickUp(),
                new Join(playerMap, teams),
                new MenuClick(playerMap),
                new MenuOpen(shopMenu, upgradeMenu, playerMap)
        );
    }

    private void register(Listener... l) {
        PluginManager pm = Bukkit.getServer().getPluginManager();
        for (Listener listener : l) {
            pm.registerEvents(listener, Main.INSTANCE);
        }
    }
}
