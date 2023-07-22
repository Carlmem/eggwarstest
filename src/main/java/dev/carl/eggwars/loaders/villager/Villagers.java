package dev.carl.eggwars.loaders.villager;

import com.google.gson.JsonObject;
import dev.carl.eggwars.util.UtilColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;

import java.util.Set;
import java.util.UUID;

public interface Villagers {
    default void villagerLoad(JsonObject jso, Set<UUID> villagers) {
        Location location = new Location(
                Bukkit.getWorld(jso.get("world").getAsString()),
                jso.get("x").getAsDouble(),
                jso.get("y").getAsDouble(),
                jso.get("z").getAsDouble(),
                90,
                jso.get("pitch").getAsFloat()
        );

        Villager villager = (Villager) location.getWorld().spawnEntity(location, EntityType.VILLAGER);
        villager.setAI(false);
        villager.setInvulnerable(true);
        villager.setCustomName(UtilColor.toColor(jso.get("name").getAsString()));
        villager.setCustomNameVisible(true);

        villagers.add(villager.getUniqueId());
    }
}
