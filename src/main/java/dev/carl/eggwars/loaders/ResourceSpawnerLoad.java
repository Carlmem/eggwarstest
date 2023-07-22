package dev.carl.eggwars.loaders;

import com.google.gson.JsonObject;
import dev.carl.eggwars.resource.ResourceSpawner;
import dev.carl.eggwars.resource.ResourceType;
import dev.carl.eggwars.item.ItemBuilder;
import io.netty.util.internal.ConcurrentSet;
import lombok.Getter;
import net.minecraft.nbt.IntTag;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class ResourceSpawnerLoad extends AbstractLoad {
    private final Set<ResourceSpawner> resourceSpawners = ConcurrentHashMap.newKeySet();

    public ResourceSpawnerLoad() {
        super("C:/Users/Carl/IdeaProjects/eggwars/src/main/resources/js/resourceSpawners.json");
    }

    @Override
    public void objectsLoad(JsonObject jso) {
        Location loc = new Location(
                Bukkit.getWorld(jso.get("world").getAsString()),
                jso.get("x").getAsDouble(),
                jso.get("y").getAsDouble(),
                jso.get("z").getAsDouble()
        );

        ResourceSpawner resourceSpawner = new ResourceSpawner(
                ResourceType.valueOf(jso.get("resourceType").getAsString()),
                jso.get("spawnTime").getAsLong(),
                loc,
                armorStand(loc, new ItemStack(Material.valueOf(jso.get("headItem").getAsString()))),
                ItemBuilder.setNbt(
                        new ItemStack(Material.valueOf(jso.get("item").getAsString())),
                        "resource",
                        IntTag.valueOf(jso.get("resource").getAsInt())
                )
        );

        resourceSpawners.add(resourceSpawner);
    }

    /**
     * создает ArmorStand с предметом на голове
     *
     * @param loc  - локация, где будет заспавнен ArmorStand
     * @param item - предмет, который будет на голове у ArmorStand
     * @return - возращает ArmorStand
     */

    private ArmorStand armorStand(Location loc, ItemStack item) {
        ArmorStand am = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
        am.setCustomNameVisible(false);
        am.setArms(false);
        am.setVisible(false);
        am.getEquipment().setHelmet(item);
        am.setMarker(true);
        am.addPassenger(armorStand(loc));
        return am;
    }

    /**
     * создает ArmorStand
     *
     * @param loc - локация, где будет заспавнен ArmorStand
     * @return - возращает ArmorStand
     */

    private ArmorStand armorStand(Location loc) {
        ArmorStand am = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
        am.setCustomNameVisible(true);
        am.setVisible(false);
        am.setMarker(false);
        return am;
    }
}
