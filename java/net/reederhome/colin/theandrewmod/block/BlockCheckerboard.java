package net.reederhome.colin.theandrewmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

public class BlockCheckerboard extends Block {

	IIcon altIcon;
	public BlockCheckerboard(Material m) {
		super(m);
		setCreativeTab(TheAndrewMod.tabAndrew);
		setBlockTextureName(TheAndrewMod.MODID+":checkerboard");
		setBlockName("checkerboard");
	}
	
	public IIcon getIcon(int side, int meta) {
		return meta==0?blockIcon:altIcon;
	}
	
	public void onBlockAdded(World w, int x, int y, int z) {
		w.setBlockMetadataWithNotify(x, y, z, ((x+y+z)%2==0)?0:5, 3);
	}
	
	public void registerBlockIcons(IIconRegister ir) {
		super.registerBlockIcons(ir);
		altIcon=ir.registerIcon(getTextureName()+"Alt");
	}
}