package net.reederhome.colin.theandrewmod.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

public class CactusGunArmor extends ItemArmor {

	String[] names = {"Cap","Jetpack","Pants","Boots"};
	public CactusGunArmor(int p_i45325_2_,
			int p_i45325_3_) {
		super(TheAndrewMod.cactusGunArmorMaterial, p_i45325_2_, p_i45325_3_);
		setCreativeTab(TheAndrewMod.tabAndrew);
		setTextureName(TheAndrewMod.MODID+":cactusGun"+names[p_i45325_3_]);
		setUnlocalizedName("cactusGun"+names[p_i45325_3_]);
	}
	
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String type) {
		return TheAndrewMod.MODID+":textures/models/armor/cactusGun_"+(slot==2?2:1)+".png";
	}

}
