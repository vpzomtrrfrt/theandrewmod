package net.reederhome.colin.theandrewmod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
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
		this.setCreativeTab(TheAndrewMod.tabAndrew);
		this.setUnlocalizedName("butterDust");
	}
	public void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		player.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 600, 0));
		player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 600, 95));
		player.addPotionEffect(new PotionEffect(Potion.digSlowdown.getId(), 600, 1));
	}
	public boolean hasCustomEntity(ItemStack stack) {
		return true;
	}
	public Entity createEntity(World world, Entity location, ItemStack itemstack) {
		EntityItem tr = new EntityItemButterDust(world);
		tr.setEntityItemStack(itemstack);
		tr.setLocationAndAngles(location.posX, location.posY, location.posZ, location.rotationPitch, location.rotationYaw);
		tr.motionX=location.motionX;
		tr.motionY=location.motionY;
		tr.motionZ=location.motionZ;
		tr.delayBeforeCanPickup=((EntityItem)location).delayBeforeCanPickup;
		return tr;
	}
}