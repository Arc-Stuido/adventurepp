package com.arc.adventurepp.bosses;

import net.minecraft.world.entity.Entity;

import java.util.HashSet;

public abstract class AbstractBoss<T extends Entity> {
    private T mainEntity;
    HashSet<BossPart<?>> entities = HashSet.newHashSet(1);

    public void rotate(float angle){
        //TODO 完成BOSS旋转，所有部件也要一起旋转
    }
    public void tick(){
        if(!mainEntity.isAlive()){
            entities.forEach(bossPart -> bossPart.getEntity().kill());
        }
    }
}
