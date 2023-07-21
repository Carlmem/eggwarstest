package org.bukkit.craftbukkit.v1_19_R3.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.ResultContainer;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.SmithingInventory;

public class CraftInventorySmithingNew extends CraftResultInventory implements SmithingInventory {

    private final Location location;

    public CraftInventorySmithingNew(Location location, Container inventory, ResultContainer resultInventory) {
        super(inventory, resultInventory);
        this.location = location;
    }

    @Override
    public ResultContainer getResultInventory() {
        return (ResultContainer) super.getResultInventory();
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public ItemStack getResult() {
        return getItem(3);
    }

    @Override
    public void setResult(ItemStack item) {
        setItem(3, item);
    }

    @Override
    public Recipe getRecipe() {
        net.minecraft.world.item.crafting.Recipe recipe = this.getResultInventory().getRecipeUsed();
        return (recipe == null) ? null : recipe.toBukkitRecipe();
    }
}
