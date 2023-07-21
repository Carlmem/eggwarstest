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
    default void villagerLoad(JsonObject person, Set<UUID> villagers) {
        Location location = new Location(
                Bukkit.getWorld(person.get("world").getAsString()),
                person.get("x").getAsDouble(),
                person.get("y").getAsDouble(),
                person.get("z").getAsDouble()
        );
        location.setPitch(person.get("pitch").getAsFloat());

        Villager villager = (Villager) location.getWorld().spawnEntity(location, EntityType.VILLAGER);
        villager.setAI(false);
        villager.setInvulnerable(true);
        villager.setCustomName(UtilColor.toColor(person.get("name").getAsString()));
        villager.setCustomNameVisible(true);

        villagers.add(villager.getUniqueId());
    }
}
