package net.reederhome.colin.theandrewmod.support.baubles;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.reederhome.colin.theandrewmod.TheAndrewMod;
import baubles.api.BaubleType;
import baubles.api.IBauble;

public class ItemCactusGunBelt extends Item implements IBauble {

	public ItemCactusGunBelt() {
		super();
		setTextureName(TheAndrewMod.MODID+":cactusGunBelt");
		setCreativeTab(TheAndrewMod.tabAndrew);
		setUnlocalizedName("cactusGunBelt");
	}
	
	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		return BaubleType.BELT;
	}

	@Override
	public void onWornTick(ItemStack itemstack, EntityLivingBase player) {}

	@Override
	public void onEquipped(ItemStack itemstack, EntityLivingBase player) {}

	@Override
	public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {}

	@Override
	public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
		return true;
	}

	@Override
	public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
		return true;
	}

}