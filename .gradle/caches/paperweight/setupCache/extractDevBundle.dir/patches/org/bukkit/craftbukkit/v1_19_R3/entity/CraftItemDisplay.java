package org.bukkit.craftbukkit.v1_19_R3.entity;

import com.google.common.base.Preconditions;
import net.minecraft.world.item.ItemDisplayContext;
import org.bukkit.craftbukkit.v1_19_R3.CraftServer;
import org.bukkit.craftbukkit.v1_19_R3.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;

public class CraftItemDisplay extends CraftDisplay implements ItemDisplay {

    public CraftItemDisplay(CraftServer server, net.minecraft.world.entity.Display.ItemDisplay entity) {
        super(server, entity);
    }

    @Override
    public net.minecraft.world.entity.Display.ItemDisplay getHandle() {
        return (net.minecraft.world.entity.Display.ItemDisplay) super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftItemDisplay";
    }

    @Override
    public EntityType getType() {
        return EntityType.ITEM_DISPLAY;
    }

    @Override
    public ItemStack getItemStack() {
        return CraftItemStack.asBukkitCopy(this.getHandle().getItemStack());
    }

    @Override
    public void setItemStack(ItemStack item) {
        this.getHandle().setItemStack(CraftItemStack.asNMSCopy(item));
    }

    @Override
    public ItemDisplayTransform getItemDisplayTransform() {
        return ItemDisplayTransform.values()[this.getHandle().getItemTransform().ordinal()];
    }

    @Override
    public void setItemDisplayTransform(ItemDisplayTransform display) {
        Preconditions.checkArgument(display != null, "Display cannot be null");

        this.getHandle().setItemTransform(ItemDisplayContext.BY_ID.apply(display.ordinal()));
    }
}
