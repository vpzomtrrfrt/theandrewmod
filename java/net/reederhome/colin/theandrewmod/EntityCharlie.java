package net.reederhome.colin.theandrewmod;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.util.AxisAlignedBB;
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
				thing.attackEntityFrom(TheAndrewMod.deathByCharlie, 20f);
			}
		}
	}
	public EntityCharlie createChild(EntityAgeable a) {
		return new EntityCharlie(worldObj);
	}
	public String getHurtSound() {
		return TheAndrewMod.MODID+":mob.charlie.scarp";
	}
}
