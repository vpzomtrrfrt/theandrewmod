package net.reederhome.colin.theandrewmod.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.InventoryWallumStorage;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

public class ItemWallumStorage extends Item {

	final boolean isAxe;
	public ItemWallumStorage(boolean axe) {
		super();
		isAxe = axe;
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
		setCreativeTab(TheAndrewMod.tabAndrew);
	}
	
	public float func_150893_a(ItemStack stack, Block block) {
		return (Items.iron_axe.func_150893_a(stack,block)>1&&isAxe)?4:1;
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if(!par2World.isRemote) {
			par3EntityPlayer.displayGUIChest(new InventoryWallumStorage(par1ItemStack,par3EntityPlayer));
		}
		return par1ItemStack;
	}
}