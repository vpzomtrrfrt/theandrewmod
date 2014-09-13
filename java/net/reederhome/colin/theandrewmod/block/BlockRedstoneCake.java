package net.reederhome.colin.theandrewmod.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCake;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockRedstoneCake extends BlockCake {

	public BlockRedstoneCake() {
		super();
		setBlockTextureName("cake");
		setHardness(0.5f);
		setTickRandomly(true);
		setBlockName("redstoneCake");
	}
	public boolean hasComparatorInputOverride() {
		return true;
	}
	public int getComparatorInputOverride(World p_149736_1_, int p_149736_2_, int p_149736_3_, int p_149736_4_, int p_149736_5_) {
		return p_149736_1_.getBlockMetadata(p_149736_2_, p_149736_3_, p_149736_4_);
	}
	public void updateTick(World world, int x, int y, int z, Random r) {
		super.updateTick(world, x, y, z, r);
		if(r.nextFloat()<0.00001) {
			EntityWitch witch = new EntityWitch(world);
			witch.setLocationAndAngles(x+r.nextInt(3)-1, y+1, z+r.nextInt(3)-1, 0, 0);
			world.spawnEntityInWorld(witch);
		}
	}
	public void updateStuff(World world, int x, int y, int z) {
		Block block = world.getBlock(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		world.setBlockToAir(x, y, z);
		world.setBlock(x, y, z, block);
		world.setBlockMetadataWithNotify(x, y, z, meta, 3);
	}
	
	public void onBlockClicked(World p_149699_1_, int p_149699_2_, int p_149699_3_, int p_149699_4_, EntityPlayer p_149699_5_) {
		super.onBlockClicked(p_149699_1_, p_149699_2_, p_149699_3_, p_149699_4_, p_149699_5_);
		this.updateStuff(p_149699_1_, p_149699_2_, p_149699_3_, p_149699_4_);
	}
	public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		boolean tr = super.onBlockActivated(p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_, p_149727_5_, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
		this.updateStuff(p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_);
		return tr;
	}
}
