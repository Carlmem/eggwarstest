/**
 * Automatically generated file, changes will be lost.
 */
package org.bukkit.craftbukkit.v1_19_R3.block.impl;

public final class CraftCake extends org.bukkit.craftbukkit.v1_19_R3.block.data.CraftBlockData implements org.bukkit.block.data.type.Cake {

    public CraftCake() {
        super();
    }

    public CraftCake(net.minecraft.world.level.block.state.BlockState state) {
        super(state);
    }

    // org.bukkit.craftbukkit.v1_19_R3.block.data.type.CraftCake

    private static final net.minecraft.world.level.block.state.properties.IntegerProperty BITES = getInteger(net.minecraft.world.level.block.CakeBlock.class, "bites");

    @Override
    public int getBites() {
        return get(CraftCake.BITES);
    }

    @Override
    public void setBites(int bites) {
        set(CraftCake.BITES, bites);
    }

    @Override
    public int getMaximumBites() {
        return getMax(CraftCake.BITES);
    }
}
