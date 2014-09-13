package net.reederhome.colin.theandrewmod.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.AchievementsAndrew;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

public class ItemGlassBottleArmor extends ItemArmor {

	String[] names = {"Helmet","Chestplate","Leggings","Boots"};
	public ItemGlassBottleArmor(int p_i45325_2_,
			int p_i45325_3_) {
		super(TheAndrewMod.glassBottleArmorMaterial, p_i45325_2_, p_i45325_3_);
		setCreativeTab(TheAndrewMod.tabAndrew);
		setTextureName(TheAndrewMod.MODID+":glassBottle"+names[p_i45325_3_]);
	}
	
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String type) {
		return TheAndrewMod.MODID+":textures/models/armor/glassBottle_"+(slot==2?2:1)+".png";
	}
	
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		player.addStat(AchievementsAndrew.glassBottleArmor, 1);
	}

}
