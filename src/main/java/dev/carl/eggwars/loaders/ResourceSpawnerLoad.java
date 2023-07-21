package dev.carl.eggwars.loaders;

import com.google.gson.JsonObject;
import dev.carl.eggwars.ResourceSpawner;
import dev.carl.eggwars.ResourceType;
import dev.carl.eggwars.item.ItemBuilder;
import lombok.Getter;
import net.minecraft.nbt.IntTag;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ResourceSpawnerLoad extends  AbstractLoad{
    private final Set<ResourceSpawner> resourceSpawners = new HashSet<>();

    public ResourceSpawnerLoad() {
        super("C:/Users/Carl/IdeaProjects/eggwars/src/main/resources/js/resourceSpawners.json");
    }

    @Override
    public void objectsLoad(JsonObject person) {
        Location loc = new Location(
                Bukkit.getWorld(person.get("world").getAsString()),
                person.get("x").getAsDouble(),
                person.get("y").getAsDouble(),
                person.get("z").getAsDouble()
        );

        ResourceSpawner resourceSpawner = new ResourceSpawner(
                ResourceType.valueOf(person.get("resourceType").getAsString()),
                person.get("spawnTime").getAsLong(),
                loc,
                armorStand(loc, new ItemStack(Material.valueOf(person.get("headItem").getAsString()))),
                ItemBuilder.setNbt(new ItemStack(
                        Material.valueOf(person.get("item").getAsString())),
                        "resource",
                        IntTag.valueOf(person.get("resource").getAsInt())
                )
        );

        resourceSpawners.add(resourceSpawner);
    }

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

    private ArmorStand armorStand(Location loc) {
        ArmorStand am = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
        am.setCustomNameVisible(true);
        am.setVisible(false);
        am.setMarker(false);
        return am;
    }
}
