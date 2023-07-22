package dev.carl.eggwars;

import dev.carl.eggwars.resource.ResourceSpawner;
import dev.carl.eggwars.util.UtilColor;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;

import java.util.Set;
@RequiredArgsConstructor
public class Tick {
    private final Set<ResourceSpawner> resourceSpawners;

    public void secondTick() {
        Bukkit.getScheduler().runTaskTimer(Main.INSTANCE, () -> {
            long now = System.currentTimeMillis();
            for (ResourceSpawner resourceSpawner : resourceSpawners) {
                ArmorStand am = (ArmorStand) resourceSpawner.getArmorStand().getPassengers().get(0);
                am.setCustomName(UtilColor.toColor("&eРеспавн через &c" + (resourceSpawner.getSpawnTime() - (now - resourceSpawner.getLastSpawn()) / 1000) + "&e Секунд"));

                if (now - resourceSpawner.getLastSpawn() < resourceSpawner.getSpawnTime() * 1000) {
                    continue;
                }

                resourceSpawner.setLastSpawn(now);
                Location spawnLoc = resourceSpawner.getSpawnLoc();
                spawnLoc.getWorld().dropItem(spawnLoc, resourceSpawner.getDropItem());
            }
        }, 0, 10);
    }

    public void onTick() {
        Bukkit.getScheduler().runTaskTimer(Main.INSTANCE, () -> {
            for (ResourceSpawner resourceSpawner : resourceSpawners) {
                ArmorStand am = resourceSpawner.getArmorStand();
                am.setHeadPose(new EulerAngle(0, am.getHeadPose().getY() + Math.toRadians(10), 0));
            }
        }, 0, 1);
    }
}
