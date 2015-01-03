package net.reederhome.colin.theandrewmod.entity;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.block.BlocksAndrew;

public class EntityKevin extends EntityCreature implements IRangedAttackMob {

	public EntityKevin(World w) {
		super(w);
		this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityCreature.class, 0.4d, true));
		this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 0.5d, 30));
		this.tasks.addTask(2, new EntityAIArrowAttack(this, 0.4, 0, 60));
		this.tasks.addTask(3, new EntityAITempt(this, 0.5, Item.getItemFromBlock(Blocks.anvil), false));
		this.tasks.addTask(3, new EntityAITempt(this, 0.5, Item.getItemFromBlock(BlocksAndrew.pakistan), false));
		this.tasks.addTask(4, new EntityAIWander(this, 0.3d));
		
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityZombie.class, 2, false));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityThomas.class, 2, false));
		
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0);
		this.setHealth(40);
		
		ItemStack p = new ItemStack(Items.iron_leggings);
		p.addEnchantment(Enchantment.fireProtection, 2);
		setCurrentItemOrArmor(2, p);
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

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase var1, float arg1) {
		for(int e = 0; e < 4; e++) {
			EntityEgg egg = new EntityEgg(worldObj, this);
			egg.setThrowableHeading(var1.posX-this.posX+Math.random()/4, var1.posY-this.posY+arg1, var1.posZ-this.posZ+Math.random()/4, 1.6f, 1.4f);
			worldObj.spawnEntityInWorld(egg);
		}
	}
	
}