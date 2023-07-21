package dev.carl.eggwars.menu;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.inventory.Inventory;

@Setter
@Getter
@RequiredArgsConstructor
public class UpgradeMenu {
    private int sharpnessLevel = 0;
    private int sharpnessPrice = 4;
    private int speedLevel = 0;
    private int speedPrice = 6;
    private int protectionLevel = 0;
    private int protectionPrice = 2;
    private final Inventory upgradeMenu;
}
