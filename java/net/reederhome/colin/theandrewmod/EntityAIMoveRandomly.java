package net.reederhome.colin.theandrewmod;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.Vec3;


public class EntityAIMoveRandomly extends EntityAIBase {

	Vec3 vec;
	EntityCreature te;
	public EntityAIMoveRandomly(EntityCreature te) {
		this.te=te;
	}
	
	@Override
	public boolean shouldExecute() {
		return te.getAttackTarget()==null;
	}

	public boolean continueExecuting() {
		return !this.te.getNavigator().noPath();
	}
	
	public void startExecuting() {
		vec = RandomPositionGenerator.findRandomTarget(te, 10, 1);
		if(this.te instanceof EntityThomas) {
			((EntityThomas) this.te).doJump();
		}
		this.te.getNavigator().tryMoveToXYZ(vec.xCoord, vec.yCoord, vec.zCoord, 0.4);
	}
}
