package net.reederhome.colin.theandrewmod.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityKevin extends EntityCreature {

	public EntityKevin(World w) {
		super(w);
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityThomas.class, 0.4d, true));
		this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 0.5d, 30));
		this.tasks.addTask(2, new EntityAIWander(this, 0.3d));
		this.tasks.addTask(3, new EntityAILookIdle(this));
		
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityThomas.class, 2, false));
		
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0);
		this.setHealth(40);
	}
	
	public boolean isAIEnabled() {
		return true;
	}
	
	public boolean attackEntityAsMob(Entity e) {
		e.attackEntityFrom(DamageSource.causeMobDamage(this), 4);
		this.swingItem();
		super.attackEntityAsMob(e);
		return true;
	}
	
}