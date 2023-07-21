package dev.carl.eggwars.events;

import dev.carl.eggwars.player.EWPlayer;
import dev.carl.eggwars.player.Team;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
public class Join implements Listener {
    private final Map<UUID, EWPlayer> playerMap;
    private final List<Team> teams;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        EWPlayer ewPlayer = new EWPlayer(
                player,
                Bukkit.createInventory(null, 27, "Предметы")
        );

        Team team = teams.get(0);
        team.players().add(player.getUniqueId());
        ewPlayer.setTeam(team);

        playerMap.put(player.getUniqueId(), ewPlayer);
    }
}
