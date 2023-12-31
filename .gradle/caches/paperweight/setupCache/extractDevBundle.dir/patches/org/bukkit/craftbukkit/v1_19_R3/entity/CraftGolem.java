package org.bukkit.craftbukkit.v1_19_R3.entity;

import net.minecraft.world.entity.animal.AbstractGolem;
import org.bukkit.craftbukkit.v1_19_R3.CraftServer;
import org.bukkit.entity.Golem;

public class CraftGolem extends CraftCreature implements Golem {
    public CraftGolem(CraftServer server, AbstractGolem entity) {
        super(server, entity);
    }

    @Override
    public AbstractGolem getHandle() {
        return (AbstractGolem) entity;
    }

    @Override
    public String toString() {
        return "CraftGolem";
    }
}
