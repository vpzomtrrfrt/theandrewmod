package net.reederhome.colin.theandrewmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.TheAndrewMod;
import net.reederhome.colin.theandrewmod.tileentity.TileEntitySideSlab;

public class BlockSideSlab extends Block implements ITileEntityProvider {

	protected BlockSideSlab() {
		super(Material.rock);
		setCreativeTab(TheAndrewMod.tabAndrew);
		setBlockBounds(0, 0, 0, 1, 1, 0.5f);
		setBlockName("sideSlab");
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntitySideSlab();
	}
	
	public int getRenderType() {
		return -1;
	}
	
	public boolean isOpaqueCube() {
		return false;
	}
	
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		if(world.isRemote) return true;
		if(player.getHeldItem().getUnlocalizedName().equals(this.getUnlocalizedName())) {
			world.setBlock(x, y, z, BlocksAndrew.jumpPad);
			BlocksAndrew.jumpPad.onBlockPlacedBy(world, x, y, z, player, new ItemStack(BlocksAndrew.jumpPad));
			return true;
		}
		return false;
	}

}