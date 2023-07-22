package dev.carl.eggwars.loaders.villager;

import com.google.gson.JsonObject;
import dev.carl.eggwars.loaders.AbstractLoad;
import lombok.Getter;

import java.util.*;

@Getter
public class ShopVillagerLoad extends AbstractLoad implements Villagers {
    private final Set<UUID> shopVillagers = new HashSet<>();
    public ShopVillagerLoad() {
        super("C:/Users/Carl/IdeaProjects/eggwars/src/main/resources/js/shopVillager.json");
    }

    @Override
    public void objectsLoad(JsonObject jso) {
        villagerLoad(jso, shopVillagers);
    }
}
