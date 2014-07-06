package net.reederhome.colin.theandrewmod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGlassBottleArmor extends ItemArmor {

	public ItemGlassBottleArmor(int p_i45325_2_,
			int p_i45325_3_) {
		super(TheAndrewMod.glassBottleArmorMaterial, p_i45325_2_, p_i45325_3_);
		setCreativeTab(TheAndrewMod.tabAndrew);
	}
	
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String type) {
		return TheAndrewMod.MODID+":textures/models/armor/glassBottle_"+(slot==2?2:1)+".png";
	}
	
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		player.addStat(AchievementsAndrew.glassBottleArmor, 1);
	}

}
