package org.bukkit.craftbukkit.v1_19_R3.entity;

import org.bukkit.craftbukkit.v1_19_R3.CraftServer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Marker;

public class CraftMarker extends CraftEntity implements Marker {

    public CraftMarker(CraftServer server, net.minecraft.world.entity.Marker entity) {
        super(server, entity);
    }

    @Override
    public net.minecraft.world.entity.Marker getHandle() {
        return (net.minecraft.world.entity.Marker) super.getHandle();
    }

    @Override
    public EntityType getType() {
        return EntityType.MARKER;
    }

    @Override
    public String toString() {
        return "CraftMarker";
    }
}
