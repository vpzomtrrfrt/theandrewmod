package net.reederhome.colin.theandrewmod.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityTrevor extends EntityCreature {
	public EntityTrevor(World par1World) {
		super(par1World);
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityThomas.class, 0.4d, true));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityJack.class, 0.4d, true));
		this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.5d, 30));
		this.tasks.addTask(3, new EntityAILookIdle(this));
		this.tasks.addTask(4, new EntityAIAvoidEntity(this, EntityTNTPrimed.class, 6, .25, .3));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityJack.class, 2, false));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityThomas.class, 2, false));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
		ItemStack heldItem = new ItemStack(Items.wooden_sword);
		this.setCurrentItemOrArmor(0, heldItem);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0);
		this.setHealth(40);
	}
	public void onUpdate() {
		super.onUpdate();
		Vec3 pos = RandomPositionGenerator.findRandomTarget(this, 4, 20);
		if(pos!=null) {
			int x = (int)pos.xCoord;
			int y = (int)pos.yCoord;
			int z = (int)pos.zCoord;
			if(worldObj.getBlock(x, y, z).equals(Blocks.gravel)) {
				worldObj.setBlockToAir(x, y, z);
				Blocks.gravel.dropBlockAsItem(worldObj, x, y, z, 0, 0);
			}
		}
		else {
			System.err.println("random position null");
		}
	}
	public boolean isAIEnabled() {
		return true;
	}
	public void doJump() {
		if(this.onGround) {jump();}
	}
	public boolean attackEntityAsMob(Entity e) {
		e.attackEntityFrom(DamageSource.generic, 4);
		this.swingItem();
		super.attackEntityAsMob(e);
		return true;
	}
	public ItemStack getHeldItem() {
		return this.getEquipmentInSlot(0);
	}
}
