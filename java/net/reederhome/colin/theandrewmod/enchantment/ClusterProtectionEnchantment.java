package net.reederhome.colin.theandrewmod.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

public class ClusterProtectionEnchantment extends Enchantment {

	public ClusterProtectionEnchantment(int id) {
		super(id,9,EnumEnchantmentType.armor_torso);
		setName(TheAndrewMod.MODID+".clusterProtection");
	}
}