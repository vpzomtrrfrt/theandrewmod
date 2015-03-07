package net.reederhome.colin.theandrewmod.item;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.FoodStats;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

public class ItemWallumEating extends Item {

	public ItemWallumEating() {
		super();
		setUnlocalizedName("wallumEating");
		setMaxDamage(100);
		setMaxStackSize(1);
		setTextureName(TheAndrewMod.MODID+":wallumEating");
		setFull3D();
		setCreativeTab(TheAndrewMod.tabAndrew);
	}
	
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.eat;
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if(par1ItemStack.getItemDamage()<=this.getMaxDamage()-1 && par3EntityPlayer.getFoodStats().getFoodLevel()<20) {
			par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		}
		return par1ItemStack;
	}
	
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 24;
    }
	
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		FoodStats fs = par3EntityPlayer.getFoodStats();
		int max = 20-fs.getFoodLevel();
		if(par1ItemStack.getItemDamage()>this.getMaxDamage()-max) {
			max = getMaxDamage()-par1ItemStack.getItemDamage();
		}
		fs.addStats(max, 0.6f);
		par1ItemStack.damageItem(max, par3EntityPlayer);
		return par1ItemStack;
	}
}
