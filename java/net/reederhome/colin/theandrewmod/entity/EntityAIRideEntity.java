package net.reederhome.colin.theandrewmod.entity;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.util.AxisAlignedBB;

public class EntityAIRideEntity extends EntityAIBase {

	EntityLivingBase thingThatRides;
	Class thingToRide;
	public EntityAIRideEntity(EntityLivingBase thing, Class mount) {
		super();
		thingThatRides=thing;
		thingToRide = mount;
	}
	List lot;
	@Override
	public boolean shouldExecute() {
		int range = 5;
		lot = thingThatRides.worldObj.getEntitiesWithinAABB(thingToRide, AxisAlignedBB.getBoundingBox(thingThatRides.posX-range, thingThatRides.posY-range, thingThatRides.posZ-range, thingThatRides.posX+range, thingThatRides.posY+range, thingThatRides.posZ+range));
		return (lot.size()>0&&Math.random()>0.8&&thingThatRides.ridingEntity==null);
	}
	public void startExecuting() {
		thingThatRides.mountEntity((Entity) lot.get(new Random().nextInt(lot.size())));
	}

}
