package net.reederhome.colin.theandrewmod.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

public class ItemMultiplier extends Item {
	public ItemMultiplier() {
		super();
		setTextureName(TheAndrewMod.MODID+":multiplier");
		setUnlocalizedName("multiplier");
		setCreativeTab(TheAndrewMod.tabAndrew);
		setMaxDamage(21);
		setMaxStackSize(1);
	}
	public void addInformation(ItemStack stack, EntityPlayer p, List info, boolean arg3) {
		info.add("Because multiplying makes it not prime");
	}
}