package org.bukkit.craftbukkit.v1_19_R3.entity;

import org.bukkit.craftbukkit.v1_19_R3.CraftServer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Squid;

public class CraftSquid extends CraftWaterMob implements Squid {

    public CraftSquid(CraftServer server, net.minecraft.world.entity.animal.Squid entity) {
        super(server, entity);
    }

    @Override
    public net.minecraft.world.entity.animal.Squid getHandle() {
        return (net.minecraft.world.entity.animal.Squid) entity;
    }

    @Override
    public String toString() {
        return "CraftSquid";
    }

    @Override
    public EntityType getType() {
        return EntityType.SQUID;
    }
}
