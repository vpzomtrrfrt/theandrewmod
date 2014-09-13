package net.reederhome.colin.theandrewmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.TheAndrewMod;
import net.reederhome.colin.theandrewmod.tileentity.TileEntityTNTChest;

public class BlockTNTChest extends BlockTNT implements ITileEntityProvider {

	public BlockTNTChest() {
		super();
		setBlockTextureName("tnt");
		setBlockName("tntChest");
		setCreativeTab(TheAndrewMod.tabAndrew);
	}
	
	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1) {
		return new TileEntityTNTChest();
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer p, int s1, float f1, float f2, float f3) {
		if(super.onBlockActivated(world, x, y, z, p, s1, f1, f2, f3)) return true;
		p.displayGUIChest((IInventory) world.getTileEntity(x, y, z));
		return true;
	}
	
	@Override
	public void breakBlock(World w, int x, int y, int z, Block b, int i) {
		super.breakBlock(w, x, y, z, b, i);
		w.removeTileEntity(x, y, z);
	}
	
	public boolean onBlockEventReceived(World p_149696_1_, int p_149696_2_, int p_149696_3_, int p_149696_4_, int p_149696_5_, int p_149696_6_)
    {
        super.onBlockEventReceived(p_149696_1_, p_149696_2_, p_149696_3_, p_149696_4_, p_149696_5_, p_149696_6_);
        TileEntity var7 = p_149696_1_.getTileEntity(p_149696_2_, p_149696_3_, p_149696_4_);
        return var7 != null ? var7.receiveClientEvent(p_149696_5_, p_149696_6_) : false;
    }

}
