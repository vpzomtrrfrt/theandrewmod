package net.reederhome.colin.theandrewmod.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.item.ItemSnowball;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.entity.EntityCurveball;

public class ItemCurveball extends ItemSnowball {
	
	public ItemCurveball() {
		super();
		setUnlocalizedName("curveball");
		setTextureName("snowball");
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
        if (!par3EntityPlayer.capabilities.isCreativeMode)
        {
            --par1ItemStack.stackSize;
        }
	    par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
	    if (!par2World.isRemote) {
            par2World.spawnEntityInWorld(new EntityCurveball(par2World, par3EntityPlayer));
        }
        return par1ItemStack;
    }
}