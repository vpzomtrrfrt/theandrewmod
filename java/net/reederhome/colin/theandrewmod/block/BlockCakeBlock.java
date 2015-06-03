package net.reederhome.colin.theandrewmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

public class BlockCakeBlock extends Block {

	public BlockCakeBlock() {
		super(Material.cake);
		setBlockTextureName(TheAndrewMod.MODID+":cakeblock_side");
	}
	
	public void setBlockBoundsBasedOnState(IBlockAccess w, int x, int y, int z) {
		float var6 = 0.0625F;
        float var7 = 1 / 16.0F;
        this.setBlockBounds(var7, 0.0F, var6, 1.0F - var6, 1.0F, 1.0F - var6);
	}
	
	public IIcon getIcon(int side, int meta) {
		if(side>1) {
			return blockIcon;
		}
		else {
			return Blocks.cake.getIcon(side, meta);
		}
	}
	
	public boolean isOpaqueCube()
    {
        return false;
    }

}