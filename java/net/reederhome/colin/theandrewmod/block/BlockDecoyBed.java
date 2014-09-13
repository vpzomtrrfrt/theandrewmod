package net.reederhome.colin.theandrewmod.block;

import net.minecraft.block.BlockBed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockDecoyBed extends BlockBed {

	public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		if(p_149727_1_.isRemote) {
			return false;
		}
		p_149727_1_.createExplosion(p_149727_5_, p_149727_2_, p_149727_3_, p_149727_4_, 10, true);
		return true;
	}
	public boolean canDropFromExplosion(Explosion e) {
		return false;
	}
}
