package dev.carl.eggwars.player;

import dev.carl.eggwars.menu.UpgradeMenu;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public record Team(UUID uuid, String name, int d, List<UUID> players, Map<UUID, EWPlayer> playerMap, Location spawnLoc,
                   UpgradeMenu upgradeMenu) {
    public EWPlayer getAlivePlayer() {
        return getAlivePlayers().size() > 0 ? getAlivePlayers().get(0) : null;
    }

    public List<EWPlayer> getAlivePlayers() {
        List<EWPlayer> playerList = new ArrayList<>();
        for (UUID player : this.players) {
            EWPlayer rp = playerMap.get(player);
            if (rp.getStatus() != EWPlayer.Status.DEATH) {
                playerList.add(rp);
            }
        }
        return playerList;
    }
}
