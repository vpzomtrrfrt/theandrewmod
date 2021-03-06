package net.reederhome.colin.theandrewmod.item;

import net.minecraft.block.BlockBed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.TheAndrewMod;
import net.reederhome.colin.theandrewmod.block.BlocksAndrew;

public class ItemDecoyBed extends Item {

	public ItemDecoyBed() {
		super();
		setCreativeTab(TheAndrewMod.tabAndrew);
		setUnlocalizedName("decoyBed");
		setTextureName("bed");
	}
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (par3World.isRemote)
        {
            return true;
        }
        else if (par7 != 1)
        {
            return false;
        }
        else
        {
            ++par5;
            BlockBed var11 = (BlockBed)BlocksAndrew.decoyBed;
            int var12 = MathHelper.floor_double((double)(par2EntityPlayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
            byte var13 = 0;
            byte var14 = 0;

            if (var12 == 0)
            {
                var14 = 1;
            }

            if (var12 == 1)
            {
                var13 = -1;
            }

            if (var12 == 2)
            {
                var14 = -1;
            }

            if (var12 == 3)
            {
                var13 = 1;
            }

            if (par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack) && par2EntityPlayer.canPlayerEdit(par4 + var13, par5, par6 + var14, par7, par1ItemStack))
            {
                if (par3World.isAirBlock(par4, par5, par6) && par3World.isAirBlock(par4 + var13, par5, par6 + var14) && World.doesBlockHaveSolidTopSurface(par3World, par4, par5 - 1, par6) && World.doesBlockHaveSolidTopSurface(par3World, par4 + var13, par5 - 1, par6 + var14))
                {
                    par3World.setBlock(par4, par5, par6, var11, var12, 3);

                    if (par3World.getBlock(par4, par5, par6) == var11)
                    {
                        par3World.setBlock(par4 + var13, par5, par6 + var14, var11, var12 + 8, 3);
                    }

                    --par1ItemStack.stackSize;
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
    }
}
