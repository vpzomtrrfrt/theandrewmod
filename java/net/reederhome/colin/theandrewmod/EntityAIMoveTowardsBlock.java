package net.reederhome.colin.theandrewmod;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.world.World;

public class EntityAIMoveTowardsBlock extends EntityAIBase {

	Block block;
	EntityLiving ent;
	int range;
	double speed;
	
	public EntityAIMoveTowardsBlock(EntityLiving ent, Block block, int range, double speed) {
		this.ent=ent;
		this.block=block;
		this.range=range;
		this.speed=speed;
	}
	public int[] getBlocks(World world, int x, int y, int z, int r) {
		for(int sx=x-r;sx<x+r;sx++) {
			for(int sy=y-r;sy<y+r;sy++) {
				for(int sz=z-r;sz<z+r;sz++) {
					if(world.getBlock(sx, sy, sz).equals(this.block)) {
						return new int[]{sx,sy,sz};
					}
				}
			}
		}
		return null;
	}
	
	@Override
	public boolean shouldExecute() {
		return getBlocks(ent.worldObj, (int)ent.posX, (int)ent.posY, (int)ent.posZ, range)!=null;
	}
	
	public void startExecuting() {
		int[] b = getBlocks(ent.worldObj, (int)ent.posX, (int)ent.posY, (int)ent.posZ, range);
		if(b!=null) {
			ent.getNavigator().tryMoveToXYZ(b[0], b[1], b[2], speed);
		}
	}

}
