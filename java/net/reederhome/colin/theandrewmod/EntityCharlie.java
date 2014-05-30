package net.reederhome.colin.theandrewmod;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityCharlie extends EntityChicken {

	public EntityCharlie(World p_i1682_1_) {
		super(p_i1682_1_);
	}
	public void onUpdate() {
		super.onUpdate();
		if(Math.random()<0.001) {
			List<EntityLivingBase> l = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(posX-0.5, posY-0.5, posZ-0.5, posX+1, posY+1, posZ+1));
			if(l.size()>0) {
				EntityLivingBase thing = l.get(new Random().nextInt(l.size()));
				if(thing!=this&&(Math.random()<0.01||!(thing instanceof EntityCharlie))) {
					thing.attackEntityFrom(TheAndrewMod.deathByCharlie, 20f);
					this.setRotation((float)Math.atan2(posZ-thing.posZ, posX-thing.posX), rotationPitch);
				}
			}
		}
		else if(Math.random()<0.1) {
			boolean dontEat=false;
			if(!eatPlant(posX,posY,posZ)) if(!eatPlant(posX+1,posY,posZ)) if(!eatPlant(posX,posZ,posZ+1)) if(!eatPlant(posX-1, posY, posZ)) if(!eatPlant(posX, posY, posZ-1)) dontEat=true;
			if(!dontEat) {this.setRotation(this.rotationYaw, 90);}
		}
	}
	public boolean interact(EntityPlayer p) {
		ItemStack stack = p.getHeldItem();
		if(stack!=null&&stack.getItem().equals(new ItemStack(TheAndrewMod.invasivePlant).getItem())) {
			Entity thing = this;
			while(thing.riddenByEntity!=null) {
				thing=thing.riddenByEntity;
			}
			p.mountEntity(thing);
		}
		return false;
	}
	public boolean eatPlant(double posX, double posY, double posZ) {
		if(worldObj.getBlock((int)posX, (int)posY, (int)posZ).equals(TheAndrewMod.invasivePlant)) {
			worldObj.setBlockToAir((int)posX, (int)posY, (int)posZ);
			this.heal(1);
			return true;
		}
		return false;
	}
	public EntityCharlie createChild(EntityAgeable a) {
		return new EntityCharlie(worldObj);
	}
	public String getHurtSound() {
		return TheAndrewMod.MODID+":mob.charlie.scarp";
	}
	public void onDeath(DamageSource source) {
		System.out.println(source.damageType);
	}
}
