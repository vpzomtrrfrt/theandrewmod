package net.reederhome.colin.theandrewmod.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.InventoryWallumStorage;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

public class ItemWallumStorage extends Item {

	public ItemWallumStorage(boolean axe) {
		super();
		setMaxStackSize(1);
		if(axe) {
			setUnlocalizedName("wallumStoraxe");
			setTextureName(TheAndrewMod.MODID+":wallumStoraxe");
			setHarvestLevel("axe", 3);
		}
		else {
			setUnlocalizedName("wallumStorage");
			setTextureName(TheAndrewMod.MODID+":wallumStorage");
		}
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if(!par2World.isRemote) {
			par3EntityPlayer.displayGUIChest(new InventoryWallumStorage(par1ItemStack));
		}
		return par1ItemStack;
	}
}