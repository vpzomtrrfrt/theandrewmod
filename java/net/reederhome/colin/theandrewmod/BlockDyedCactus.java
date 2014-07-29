package net.reederhome.colin.theandrewmod;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCactus;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockDyedCactus extends BlockCactus {

	private IIcon[][] icons;
	String[] sides = {"top","side"};
	String[] colors = ItemDye.field_150921_b;
	protected BlockDyedCactus() {
		super();
		this.setBlockName("dyed_cactus");
		setCreativeTab(TheAndrewMod.tabAndrew);
	}
	public IIcon getIcon(int side, int meta) {
		int sidei = side == 1 ? 0 : (side == 0 ? 2 : 1);
		IIcon tr = sidei==2?Blocks.cactus.getBlockTextureFromSide(0):this.icons[meta][sidei];
		return tr;
	}
	public void registerBlockIcons(IIconRegister ir) {
		this.icons = new IIcon[colors.length][sides.length];
		for(int t1 = 0; t1 < colors.length; t1++) {
			for(int t2 = 0; t2 < sides.length; t2++) {
				this.icons[t1][t2]=ir.registerIcon(TheAndrewMod.MODID+":dyed_cactus_"+sides[t2]+"_"+colors[t1]);
			}
		}
	}
	/*public void getSubBlocks(Item item, CreativeTabs tab, List l) {
		for(int i = 0; i < colors.length; i++) {
			l.add(new ItemStack(item, 1, i));
		}
	}*/

	public int damageDropped(int meta) {
		return meta;
	}
	
	public boolean canBlockStay(World p_149718_1_, int p_149718_2_, int p_149718_3_, int p_149718_4_)
    {
        if (p_149718_1_.getBlock(p_149718_2_ - 1, p_149718_3_, p_149718_4_).getMaterial().isSolid())
        {
            return false;
        }
        else if (p_149718_1_.getBlock(p_149718_2_ + 1, p_149718_3_, p_149718_4_).getMaterial().isSolid())
        {
            return false;
        }
        else if (p_149718_1_.getBlock(p_149718_2_, p_149718_3_, p_149718_4_ - 1).getMaterial().isSolid())
        {
            return false;
        }
        else if (p_149718_1_.getBlock(p_149718_2_, p_149718_3_, p_149718_4_ + 1).getMaterial().isSolid())
        {
            return false;
        }
        else
        {
            Block var5 = p_149718_1_.getBlock(p_149718_2_, p_149718_3_ - 1, p_149718_4_);
            return var5 == Blocks.cactus || var5 == this || var5 == Blocks.sand || var5 == BlocksAndrew.rainbowMachine;
        }
    }
	public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_)
    {
        if (p_149674_1_.isAirBlock(p_149674_2_, p_149674_3_ + 1, p_149674_4_))
        {
            int var6;

            for (var6 = 1; p_149674_1_.getBlock(p_149674_2_, p_149674_3_ - var6, p_149674_4_) == this; ++var6)
            {
                ;
            }

            if (var6 < 3)
            {
                int var7 = p_149674_1_.getBlockMetadata(p_149674_2_, p_149674_3_, p_149674_4_);

                if(Math.random()<0.1) {
                	p_149674_1_.setBlock(p_149674_2_, p_149674_3_ + 1, p_149674_4_, this);
                	p_149674_1_.setBlockMetadataWithNotify(p_149674_2_, p_149674_3_+1, p_149674_4_, var7, 4);
                	this.onNeighborBlockChange(p_149674_1_, p_149674_2_, p_149674_3_ + 1, p_149674_4_, this);
                }
            }
        }
    }
	public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_)
    {
        for (int var4 = 0; var4 < 16; ++var4)
        {
            p_149666_3_.add(new ItemStack(p_149666_1_, 1, var4));
        }
    }
}
