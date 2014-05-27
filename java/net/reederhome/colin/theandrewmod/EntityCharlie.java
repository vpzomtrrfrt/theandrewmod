package net.reederhome.colin.theandrewmod;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
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
				if(thing!=this) {
					thing.attackEntityFrom(TheAndrewMod.deathByCharlie, 20f);
				}
			}
		}
		else if(Math.random()<0.01) {
			if(!eatPlant(posX,posY,posZ)) if(!eatPlant(posX+1,posY,posZ)) if(!eatPlant(posX,posZ,posZ+1)) if(!eatPlant(posX-1, posY, posZ)) eatPlant(posX, posY, posZ-1);
		}
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
