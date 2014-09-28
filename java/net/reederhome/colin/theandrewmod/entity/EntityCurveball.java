package net.reederhome.colin.theandrewmod.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

public class EntityCurveball extends EntitySnowball {

	double startX = 0;
	double startZ = 0;
	public EntityCurveball(World p_i1773_1_) {
		super(p_i1773_1_);
	}
	
	public EntityCurveball(World par2World, EntityPlayer par3EntityPlayer) {
		super(par2World, par3EntityPlayer);
	}
	
	public void onImpact(MovingObjectPosition pos) {
		super.onImpact(pos);
		if(pos.entityHit!=null) {
			if(pos.entityHit instanceof EntityLivingBase) {
				EntityLivingBase ent = (EntityLivingBase) pos.entityHit;
				ent.addPotionEffect(new PotionEffect(TheAndrewMod.spinningPotion.id, 100));
			}
		}
	}

	public void onUpdate() {
		super.onUpdate();
		if(startX==0&&startZ==0) {
			startX=motionX/2;
			startZ=motionZ/2;
		}
		double t = Math.atan2(motionZ, motionX)+5;
		double vel = Math.sqrt(motionX*motionX+motionZ*motionZ);
		setVelocity(Math.cos(t)*vel+startX, motionY, Math.sin(t)*vel+startZ);
	}

}