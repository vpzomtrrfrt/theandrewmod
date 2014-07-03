package net.reederhome.colin.theandrewmod;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockRainbowMachine extends Block {

	IIcon sideIcon;
	public BlockRainbowMachine() {
		super(Material.cloth);
	}
	
	public IIcon getIcon(int side, int meta) {
		return side==meta?this.blockIcon:(side==Facing.oppositeSide[meta]?Blocks.dispenser.getIcon(0, 2):sideIcon);
	}
	
	public void registerBlockIcons(IIconRegister ir) {
		blockIcon=ir.registerIcon(TheAndrewMod.MODID+":rainbowMachineFront");
		sideIcon =ir.registerIcon(TheAndrewMod.MODID+":rainbowMachineSide");
	}
	
	public int getRenderType()
    {
        return 16;
    }

	
	public void onBlockPlacedBy(World world, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
		world.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, Facing.oppositeSide[BlockPistonBase.determineOrientation(world, p_149689_2_, p_149689_3_, p_149689_4_, p_149689_5_)], 3);
	}
	
	public void onBlockAdded(World world, int x, int y, int z) {
		updateTick(world, x, y, z, world.rand);
	}
	
	public void updateTick(World world, int x, int y, int z, Random r) {
		int m = world.getBlockMetadata(x, y, z);
		int bx = x+Facing.offsetsXForSide[m];
		int by = y+Facing.offsetsYForSide[m];
		int bz = z+Facing.offsetsZForSide[m];
		Block b = world.getBlock(bx, by, bz);
		if(b.equals(Blocks.wool)||b.equals(Blocks.stained_hardened_clay)||b.equals(Blocks.stained_glass)||b.equals(Blocks.stained_glass_pane)||b.equals(Blocks.carpet)||b.equals(TheAndrewMod.dyedCactus)) {
			int nm = world.getBlockMetadata(bx, by, bz)-1;
			if(nm<0) {
				nm=15;
			}
			world.setBlockMetadataWithNotify(bx, by, bz, nm, 3);
		}
		world.scheduleBlockUpdate(x, y, z, this, 2);
	}

}
