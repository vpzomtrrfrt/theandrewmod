package net.reederhome.colin.theandrewmod.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityProjectileHorse extends EntityHorse {

	
	public EntityProjectileHorse(World w) {
		super(w);
	}
	
	public void onImpact(MovingObjectPosition arg0) {
		if(!worldObj.isRemote) worldObj.createExplosion(this, posX, posY, posZ, 3, true);
		this.setDead();
	}
    
    public void setThrowableHeading(double par1, double par3, double par5)
    {
    	float par7 = 1;
    	float par8 = 1;
        float var9 = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
        par1 /= (double)var9;
        par3 /= (double)var9;
        par5 /= (double)var9;
        par1 += this.rand.nextGaussian() * 0.007499999832361937D * (double)par8;
        par3 += this.rand.nextGaussian() * 0.007499999832361937D * (double)par8;
        par5 += this.rand.nextGaussian() * 0.007499999832361937D * (double)par8;
        par1 *= (double)par7;
        par3 *= (double)par7;
        par5 *= (double)par7;
        this.motionX = par1;
        this.motionY = par3;
        this.motionZ = par5;
        float var10 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
        this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(par1, par5) * 180.0D / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(par3, (double)var10) * 180.0D / Math.PI);
    }

	
    public void onUpdate()
    {
        this.lastTickPosX = this.posX;
        this.lastTickPosY = this.posY;
        this.lastTickPosZ = this.posZ;
        super.onUpdate();

        Vec3 var1 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
        Vec3 var2 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        MovingObjectPosition var3 = this.worldObj.rayTraceBlocks(var1, var2);
        var1 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
        var2 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

        if (var3 != null)
        {
            var2 = Vec3.createVectorHelper(var3.hitVec.xCoord, var3.hitVec.yCoord, var3.hitVec.zCoord);
        }

        if (!this.worldObj.isRemote)
        {
            Entity var4 = null;
            List var5 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
            double var6 = 0.0D;

            for (int var9 = 0; var9 < var5.size(); ++var9)
            {
                Entity var10 = (Entity)var5.get(var9);

                if (var10.canBeCollidedWith())
                {
                    float var11 = 0.3F;
                    AxisAlignedBB var12 = var10.boundingBox.expand((double)var11, (double)var11, (double)var11);
                    MovingObjectPosition var13 = var12.calculateIntercept(var1, var2);

                    if (var13 != null)
                    {
                        double var14 = var1.distanceTo(var13.hitVec);

                        if (var14 < var6 || var6 == 0.0D)
                        {
                            var4 = var10;
                            var6 = var14;
                        }
                    }
                }
            }

            if (var4 != null)
            {
                var3 = new MovingObjectPosition(var4);
            }
        }

        if (var3 != null)
        {
            if (var3.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && this.worldObj.getBlock(var3.blockX, var3.blockY, var3.blockZ) == Blocks.portal)
            {
                this.setInPortal();
            }
            else
            {
                this.onImpact(var3);
            }
        }

        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;
        float var16 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

        for (this.rotationPitch = (float)(Math.atan2(this.motionY, (double)var16) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
        {
            ;
        }

        while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
        {
            this.prevRotationPitch += 360.0F;
        }

        while (this.rotationYaw - this.prevRotationYaw < -180.0F)
        {
            this.prevRotationYaw -= 360.0F;
        }

        while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
        {
            this.prevRotationYaw += 360.0F;
        }

        this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
        this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
        float var17 = 0.99F;
        float var18 = 0.02f;

        if (this.isInWater())
        {
            for (int var7 = 0; var7 < 4; ++var7)
            {
                float var19 = 0.25F;
                this.worldObj.spawnParticle("bubble", this.posX - this.motionX * (double)var19, this.posY - this.motionY * (double)var19, this.posZ - this.motionZ * (double)var19, this.motionX, this.motionY, this.motionZ);
            }

            var17 = 0.8F;
        }

        this.motionX *= (double)var17;
        this.motionY *= (double)var17;
        this.motionZ *= (double)var17;
        this.motionY -= (double)var18;
        this.setPosition(this.posX, this.posY, this.posZ);
    }
}