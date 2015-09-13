package net.reederhome.colin.theandrewmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

public class BlockMinesweeper extends Block {

	private Block mine = Blocks.tnt;
	private int[] coords = {-1, -1, 0, -1, 1, -1, 1, 0, 1, 1, 0, 1, -1, 1, -1, 0};
	private IIcon[] icons = new IIcon[11];
	
	public BlockMinesweeper() {
		super(Material.rock);
		setCreativeTab(TheAndrewMod.tabAndrew);
		setHardness(1);
		setBlockName("minesweeper");
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		int found = 0;
		if(world.getBlock(x, y-1, z).equals(mine)) {
			found = 9;
		}
		else {
			for(int i = 0; i < coords.length; i+=2) {
				if(world.getBlock(x+coords[i], y-1, z+coords[i+1]).equals(mine)) {
					found++;
				}
			}
			if(found==0) found = 10;
		}
		world.setBlockMetadataWithNotify(x, y, z, found, 3);
		return true;
	}
	
	public void registerBlockIcons(IIconRegister ir) {
		for(int i = 0; i < icons.length; i++) {
			icons[i] = ir.registerIcon(TheAndrewMod.MODID+":minesweeper"+i);
		}
	}
	
	public boolean canProvidePower()
    {
        return true;
    }

    public int isProvidingWeakPower(IBlockAccess w, int x, int y, int z, int s)
    {
        return (w.getBlockMetadata(x, y, z)==9)?15:0;
    }
    
    public IIcon getIcon(int side, int meta) {
    	return icons[meta];
    }
}