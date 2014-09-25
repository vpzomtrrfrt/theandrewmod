package net.reederhome.colin.theandrewmod.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

public class ItemButterCluster extends Item {

	public ItemButterCluster() {
		super();
		setUnlocalizedName("butterCluster");
		setTextureName(TheAndrewMod.MODID+":butterCluster");
		setMaxStackSize(1);
		setCreativeTab(TheAndrewMod.tabAndrew);
	}
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer p) {
		p.setItemInUse(stack, getMaxItemUseDuration(stack));
		return stack;
	}
	
	public int getMaxItemUseDuration(ItemStack s) {
		return 32;
	}
	public EnumAction getItemUseAction(ItemStack s) {
		return EnumAction.drink;
	}
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer p) {
		p.addPotionEffect(new PotionEffect(TheAndrewMod.clusterPotion.id, 2000));
		return new ItemStack(ItemsAndrew.butterShellEmpty);
	}
}