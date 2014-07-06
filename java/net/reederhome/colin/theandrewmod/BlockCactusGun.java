package net.reederhome.colin.theandrewmod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockCactusGun extends Block {

	IIcon front, bottom;
	public BlockCactusGun() {
		super(Material.cactus);
		setBlockName("blockCactusGun");
	}
	
	public void onBlockPlacedBy(World world, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
		world.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, Facing.oppositeSide[BlockPistonBase.determineOrientation(world, p_149689_2_, p_149689_3_, p_149689_4_, p_149689_5_)], 3);
	}
	
	public IIcon getIcon(int side, int meta) {
		return side==meta%Facing.facings.length?front:(side<2?bottom:blockIcon);
	}
	
	public void registerBlockIcons(IIconRegister ir) {
		blockIcon=ir.registerIcon(TheAndrewMod.MODID+":blockCactusGunSide");
		front=ir.registerIcon(TheAndrewMod.MODID+":blockCactusGunFront");
		bottom=ir.registerIcon(TheAndrewMod.MODID+":blockCactusGunBottom");
	}
	
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		int meta = world.getBlockMetadata(x, y, z);
		if(world.isBlockIndirectlyGettingPowered(x, y, z)) {
			if(meta<Facing.facings.length) {
				world.setBlockMetadataWithNotify(x, y, z, meta+Facing.facings.length, 3);
				EntityThrownCactus cactus = new EntityThrownCactus(world);
				cactus.setVelocity(Facing.offsetsXForSide[meta], Facing.offsetsYForSide[meta], Facing.offsetsZForSide[meta]);
				cactus.setLocationAndAngles(Facing.offsetsXForSide[meta]+0.5+x, Facing.offsetsYForSide[meta]+0.5+y, Facing.offsetsZForSide[meta]+0.5+z, 0, 0);
				world.spawnEntityInWorld(cactus);
			}
		}
		else if(meta>=Facing.facings.length) {
			world.setBlockMetadataWithNotify(x, y, z, meta-Facing.facings.length, 3);
		}
	}

}
