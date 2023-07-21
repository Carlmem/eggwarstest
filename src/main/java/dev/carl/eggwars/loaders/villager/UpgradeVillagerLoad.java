package dev.carl.eggwars.loaders.villager;

import com.google.gson.JsonObject;
import dev.carl.eggwars.loaders.AbstractLoad;
import lombok.Getter;

import java.util.*;

@Getter
public class UpgradeVillagerLoad extends AbstractLoad implements Villagers {
    private final Set<UUID> upgradeVillagers = new HashSet<>();

    public UpgradeVillagerLoad() {
        super("C:/Users/Carl/IdeaProjects/eggwars/src/main/resources/js/upgradeVillager.json");
    }

    @Override
    public void objectsLoad(JsonObject person) {
        villagerLoad(person, upgradeVillagers);
    }
}
