package net.reederhome.colin.theandrewmod.entity;

import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityMooshboom extends EntityMooshroom {

	public EntityMooshboom(World p_i1687_1_) {
		super(p_i1687_1_);
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
    {
		if (!super.attackEntityFrom(par1DamageSource, par2)) {
			return false;
		}
		setDead();
		worldObj.createExplosion(this, posX, posY, posZ, 3, false);
		return true;
    }
}