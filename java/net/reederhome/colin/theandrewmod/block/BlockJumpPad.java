package net.reederhome.colin.theandrewmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.AchievementsAndrew;
import net.reederhome.colin.theandrewmod.TheAndrewMod;
import net.reederhome.colin.theandrewmod.item.ItemsAndrew;
import net.reederhome.colin.theandrewmod.tileentity.TileEntityJumpPad;

public class BlockJumpPad extends Block implements ITileEntityProvider {

	IIcon safeIcon;
	
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
	
	public void registerBlockIcons(IIconRegister ir) {
		blockIcon = ir.registerIcon(getTextureName());
		safeIcon  = ir.registerIcon(getTextureName()+"Safe");
	}
	
	public IIcon getIcon(int side, int meta) {
		return meta==5?safeIcon:blockIcon;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer p, int s1, float f1, float f2, float f3) {
		ItemStack hi = p.getHeldItem();
		if(hi!=null) {
			if(hi.getItem().equals(ItemsAndrew.itemWallum)) {
				System.out.println("wallum");
				if(world.getBlockMetadata(x, y, z)==0) {
					if(((TileEntityJumpPad)world.getTileEntity(x, y, z)).owner.equals(p.getCommandSenderName())) {
						hi.damageItem(1, p);
						world.setBlockMetadataWithNotify(x, y, z, 5, 3);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
		TileEntityJumpPad tejp = (TileEntityJumpPad) w.getTileEntity(x, y, z);
		tejp.owner=p_149689_5_.getCommandSenderName();
		if(p_149689_5_ instanceof EntityPlayer) {
			((EntityPlayer) p_149689_5_).addStat(AchievementsAndrew.jumpPad, 1);
		}
	}
	
}
