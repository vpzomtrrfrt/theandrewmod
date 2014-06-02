package net.reederhome.colin.theandrewmod;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockJumpPad extends Block implements ITileEntityProvider {

	public BlockJumpPad() {
		super(Material.cloth);
		setBlockName("jumpPad");
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityJumpPad();
	}
	
	public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
		TileEntityJumpPad tejp = (TileEntityJumpPad) w.getTileEntity(x, y, z);
		tejp.owner=p_149689_5_.getCommandSenderName();
	}
	
}