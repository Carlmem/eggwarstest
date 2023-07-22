package dev.carl.eggwars.resource;

import lombok.*;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;

@Setter
@Getter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class ResourceSpawner {
    private final ResourceType resourceType;
    private final long spawnTime;
    private final Location spawnLoc;
    private final ArmorStand armorStand;
    private final ItemStack dropItem;
    private int level = 1;
    private long lastSpawn;
}
