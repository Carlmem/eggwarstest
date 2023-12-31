package org.bukkit.craftbukkit.v1_19_R3.entity;

import net.minecraft.world.entity.ambient.AmbientCreature;
import org.bukkit.craftbukkit.v1_19_R3.CraftServer;
import org.bukkit.entity.Ambient;
import org.bukkit.entity.EntityType;

public class CraftAmbient extends CraftMob implements Ambient {
    public CraftAmbient(CraftServer server, AmbientCreature entity) {
        super(server, entity);
    }

    @Override
    public AmbientCreature getHandle() {
        return (AmbientCreature) entity;
    }

    @Override
    public String toString() {
        return "CraftAmbient";
    }

    @Override
    public EntityType getType() {
        return EntityType.UNKNOWN;
    }
}
