package com.arc.adventurepp.bosses;

import net.minecraft.world.entity.Entity;

public abstract class BossPart<T extends Entity> {
    private final Entity parent;
    private T entity;

    public BossPart(Entity parent, T entity) {
        this.parent = parent;
        this.entity = entity;
    }

    public T getEntity() {
        return entity;
    }

    public Entity getParent() {
        return parent;
    }
}