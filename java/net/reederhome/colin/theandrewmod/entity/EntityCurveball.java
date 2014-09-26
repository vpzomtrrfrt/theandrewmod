package net.reederhome.colin.theandrewmod.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

public class EntityCurveball extends EntitySnowball {

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
		double t = Math.atan2(motionZ, motionX)+5;
		setVelocity(Math.sin(t), motionY, Math.cos(t));
	}

}