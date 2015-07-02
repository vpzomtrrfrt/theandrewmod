package net.reederhome.colin.theandrewmod.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.pathfinding.PathNavigate;

public interface ICreeper {

	EntityLivingBase getAttackTarget();

	int getCreeperState();

	PathNavigate getNavigator();

	void setCreeperState(int i);

	double getDistanceSqToEntity(Entity creeperAttackTarget);

	EntitySenses getEntitySenses();

}
