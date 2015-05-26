package net.reederhome.colin.theandrewmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

public class BlockLiquidGunpowder extends BlockFluidClassic {

	IIcon flow;
	
	public BlockLiquidGunpowder() {
		super(BlocksAndrew.liquidGunpower, Material.water);
		setBlockTextureName(TheAndrewMod.MODID+":liquidGunpowder");
	}
	
	public void registerBlockIcons(IIconRegister ir) {
		blockIcon = ir.registerIcon(textureName+"_still");
		flow = ir.registerIcon(textureName+"_flow");
	}
	
	public IIcon getIcon(int side, int meta) {
		return meta==0?blockIcon:flow;
	}
	
	public void doExplode(World w, int x, int y, int z) {
		w.createExplosion(null, x, y, z, 2, true);
	}
	
	public boolean displaceIfPossible(World w, int x, int y, int z) {
		Block b = w.getBlock(x, y, z);
		boolean isLava = b.getMaterial().equals(Material.lava);
		if(b.equals(Blocks.torch) || b.equals(Blocks.fire) || isLava) {
			doExplode(w, x, y, z);
			if(!isLava) {
				return true;
			}
		}
		return super.displaceIfPossible(w, x, y, z);
	}
	
	public void onBlockDestroyedByExplosion(World w, int x, int y, int z, Explosion p_149723_5_) {
		doExplode(w, x, y, z);
	}
	
}