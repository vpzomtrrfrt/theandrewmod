package net.reederhome.colin.theandrewmod.block;

import net.minecraft.block.BlockCactus;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

public class BlockCompressedCactus extends BlockCactus {

	public BlockCompressedCactus() {
		super();
		setCreativeTab(TheAndrewMod.tabAndrew);
		setBlockName("compressedCactus");
		setBlockTextureName(TheAndrewMod.MODID+":compressedCactus");
	}
	
	public void onEntityCollidedWithBlock(World p_149670_1_, int p_149670_2_, int p_149670_3_, int p_149670_4_, Entity p_149670_5_)
    {
        p_149670_5_.attackEntityFrom(DamageSource.cactus, 3.0F);
    }
	
}