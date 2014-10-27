package net.reederhome.colin.theandrewmod.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityZombieCow extends EntityCow {

	
	public void onUpdate() {
		super.onUpdate();
		if(worldObj.canBlockSeeTheSky((int)posX, (int)posY, (int)posZ)&&getBrightness(1)>0.5&&!worldObj.isRemote&&worldObj.isDaytime()&&!isChild()) {
			this.setFire(6);
		}
	}
	
	public EntityZombieCow(World p_i1683_1_) {
		super(p_i1683_1_);
		this.tasks.taskEntries.clear();
		getNavigator().setBreakDoors(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, 2));
		this.tasks.addTask(1, new EntityAIBreakDoor(this));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1, false));
		this.tasks.addTask(3, new EntityAIMate(this, 1));
		this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityVillager.class, 1, true));
		this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
	}
	public boolean attackEntityAsMob(Entity e) {
		e.attackEntityFrom(DamageSource.causeMobDamage(this), 4);
		return true;
	}
	public EntityZombieCow createChild(EntityAgeable par1EntityAgeable)
    {
        return new EntityZombieCow(this.worldObj);
    }
	public void dropFewItems(boolean b, int i) {
		super.dropFewItems(b, i);
		int var4 = this.rand.nextInt(3);
        if (i > 0) {
            var4 += this.rand.nextInt(i + 1);
        }
        for (int var5 = 0; var5 < var4; ++var5) {
            this.dropItem(Items.rotten_flesh, 1);
        }
	}

}