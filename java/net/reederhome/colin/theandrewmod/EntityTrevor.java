package net.reederhome.colin.theandrewmod;

import java.util.Random;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityTrevor extends EntityCreature {
	public EntityTrevor(World par1World) {
		super(par1World);
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityThomas.class, 0.4d, true));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityJack.class, 0.4d, true));
		this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.4d, 30));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityJack.class, 2, false));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityThomas.class, 2, false));
		ItemStack heldItem = new ItemStack(Items.wooden_sword);
		EnchantmentHelper.addRandomEnchantment(new Random(), heldItem, 42);
		this.setCurrentItemOrArmor(0, heldItem);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0);
		this.setHealth(40);
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
