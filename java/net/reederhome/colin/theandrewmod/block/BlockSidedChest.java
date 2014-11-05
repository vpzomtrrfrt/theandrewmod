package net.reederhome.colin.theandrewmod.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.InventoryFromSide;
import net.reederhome.colin.theandrewmod.TheAndrewMod;
import net.reederhome.colin.theandrewmod.tileentity.TileEntitySidedChest;

public class BlockSidedChest extends BlockContainer {

	public BlockSidedChest() {
		super(Material.wood);
		setBlockName("sidedChest");
		setBlockTextureName(TheAndrewMod.MODID+":sidedChest");
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer p, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		if(!world.isRemote) {
			p.displayGUIChest(new InventoryFromSide((ISidedInventory) world.getTileEntity(x, y, z),p_149727_6_));
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1) {
		return new TileEntitySidedChest();
	}
	
}
