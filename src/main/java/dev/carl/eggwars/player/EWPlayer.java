package dev.carl.eggwars.player;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

@Getter
@Setter
@RequiredArgsConstructor
public class EWPlayer {
    private final Player player;
    private final Inventory shopMenu;
    private final Status status = Status.ALIVE;
    private Team team;
    public enum Status {
        ALIVE,
        DEATH
    }
}
