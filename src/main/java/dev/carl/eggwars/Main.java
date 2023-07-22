package dev.carl.eggwars;

import dev.carl.eggwars.command.DelEntity;
import dev.carl.eggwars.loaders.EventLoader;
import dev.carl.eggwars.loaders.ResourceSpawnerLoad;
import dev.carl.eggwars.loaders.TeamLoad;
import dev.carl.eggwars.loaders.villager.ShopVillagerLoad;
import dev.carl.eggwars.loaders.villager.UpgradeVillagerLoad;
import dev.carl.eggwars.player.EWPlayer;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


public class Main extends JavaPlugin {
    private final Map<UUID, EWPlayer> playerMap = new ConcurrentHashMap<>();
    public static Main INSTANCE;
    @Override
    public void onEnable() {
        INSTANCE = this;
        getLogger().info("EggWars plugin aktiviert!");
        ResourceSpawnerLoad resourceSpawnerLoad = new ResourceSpawnerLoad();
        resourceSpawnerLoad.load();
        Tick tick = new Tick(resourceSpawnerLoad.getResourceSpawners());
        tick.secondTick();
        tick.onTick();

        ShopVillagerLoad shopVillagerLoad =new ShopVillagerLoad();
        UpgradeVillagerLoad upgradeVillagerLoad = new UpgradeVillagerLoad();
        TeamLoad teamLoad = new TeamLoad(playerMap);
        teamLoad.load();
        shopVillagerLoad.load();
        upgradeVillagerLoad.load();

        new EventLoader(upgradeVillagerLoad.getUpgradeVillagers(), shopVillagerLoad.getShopVillagers(), playerMap, teamLoad.getGameTeams()).load();
        getCommand("delentity").setExecutor(new DelEntity());
    }

    @Override
    public void onDisable() {

    }
}