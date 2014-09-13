package net.reederhome.colin.theandrewmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.AchievementsAndrew;
import net.reederhome.colin.theandrewmod.TheAndrewMod;
import net.reederhome.colin.theandrewmod.tileentity.TileEntityJumpPad;

public class BlockJumpPad extends Block implements ITileEntityProvider {

	public BlockJumpPad() {
		super(Material.cloth);
		setBlockName("jumpPad");
		setCreativeTab(TheAndrewMod.tabAndrew);
		setBlockTextureName(TheAndrewMod.MODID+":jumpPad");
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityJumpPad();
	}
	
	public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
		TileEntityJumpPad tejp = (TileEntityJumpPad) w.getTileEntity(x, y, z);
		tejp.owner=p_149689_5_.getCommandSenderName();
		if(p_149689_5_ instanceof EntityPlayer) {
			((EntityPlayer) p_149689_5_).addStat(AchievementsAndrew.jumpPad, 1);
		}
	}
	
}
