package net.reederhome.colin.theandrewmod.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

public class NetworkArmor extends ItemArmor {

	String[] names = {"Cap","Chestplate","Pants","Boots"};
	public NetworkArmor(int p_i45325_2_,
			int p_i45325_3_) {
		super(TheAndrewMod.networkArmorMaterial, p_i45325_2_, p_i45325_3_);
		setCreativeTab(TheAndrewMod.tabAndrew);
		setTextureName(TheAndrewMod.MODID+":network"+names[p_i45325_3_]);
		setUnlocalizedName("network"+names[p_i45325_3_]);
	}
	
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String type) {
		return TheAndrewMod.MODID+":textures/models/armor/network_"+(slot==2?2:1)+".png";
	}

}
