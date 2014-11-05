package net.reederhome.colin.theandrewmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.TheAndrewMod;
import net.reederhome.colin.theandrewmod.item.ItemsAndrew;
import net.reederhome.colin.theandrewmod.tileentity.TileEntityWallumagicalChest;

public class BlockWallumagicalChest extends BlockChest {

	public BlockWallumagicalChest() {
		super(0);
		setCreativeTab(TheAndrewMod.tabAndrew);
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer p, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		ItemStack stack = p.getHeldItem();
		if(stack!=null&&stack.getItem().equals(ItemsAndrew.itemWallum)) {
			TileEntity te = world.getTileEntity(x, y, z);
			if(te!=null) {
				((TileEntityWallumagicalChest)te).swapInventory();
				return true;
			}
		}
		return super.onBlockActivated(world, x, y, z, p, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
	}
	
	public TileEntity createNewTileEntity(World w, int i) {
		return new TileEntityWallumagicalChest();
	}
	
	public void breakBlock(World world, int x, int y, int z, Block p_149749_5_, int p_149749_6_) {
		TileEntityWallumagicalChest te = (TileEntityWallumagicalChest) world.getTileEntity(x, y, z);
		if(te!=null) {
			super.breakBlock(world, x, y, z, p_149749_5_, p_149749_6_);
			te.swapInventory();
			super.breakBlock(world, x, y, z, p_149749_5_, p_149749_6_);
		}
	}

}