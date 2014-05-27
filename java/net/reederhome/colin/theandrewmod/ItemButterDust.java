package net.reederhome.colin.theandrewmod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemButterDust extends ItemFood {

	public ItemButterDust() {
		super(20, 0, false);
		this.setAlwaysEdible();
		this.setCreativeTab(CreativeTabs.tabFood);
		this.setUnlocalizedName("butterDust");
	}
	public void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		player.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 600, 0));
		player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 600, 95));
		player.addPotionEffect(new PotionEffect(Potion.digSlowdown.getId(), 600, 1));
	}
}