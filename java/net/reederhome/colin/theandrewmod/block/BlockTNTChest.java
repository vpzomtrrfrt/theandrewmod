package net.reederhome.colin.theandrewmod.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
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
		TileEntityTNTChest var7 = (TileEntityTNTChest) w.getTileEntity(x, y, z);
		if (var7 != null)
        {
            for (int var8 = 0; var8 < var7.getSizeInventory(); ++var8)
            {
                ItemStack var9 = var7.getStackInSlot(var8);

                if (var9 != null)
                {
                    float var10 = new Random().nextFloat() * 0.8F + 0.1F;
                    float var11 = new Random().nextFloat() * 0.8F + 0.1F;
                    EntityItem var14;

                    for (float var12 = new Random().nextFloat() * 0.8F + 0.1F; var9.stackSize > 0; w.spawnEntityInWorld(var14))
                    {
                        int var13 = new Random().nextInt(21) + 10;

                        if (var13 > var9.stackSize)
                        {
                            var13 = var9.stackSize;
                        }

                        var9.stackSize -= var13;
                        var14 = new EntityItem(w, (double)((float)x + var10), (double)((float)y + var11), (double)((float)z + var12), new ItemStack(var9.getItem(), var13, var9.getItemDamage()));
                        float var15 = 0.05F;
                        var14.motionX = (double)((float)new Random().nextGaussian() * var15);
                        var14.motionY = (double)((float)new Random().nextGaussian() * var15 + 0.2F);
                        var14.motionZ = (double)((float)new Random().nextGaussian() * var15);

                        if (var9.hasTagCompound())
                        {
                            var14.getEntityItem().setTagCompound((NBTTagCompound)var9.getTagCompound().copy());
                        }
                    }
                }
            }

            w.func_147453_f(x, y, z, b);
        }
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
