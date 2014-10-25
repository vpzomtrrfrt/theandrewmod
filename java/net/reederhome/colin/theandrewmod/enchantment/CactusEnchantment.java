package net.reederhome.colin.theandrewmod.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

public class CactusEnchantment extends Enchantment {

	public CactusEnchantment(int id) {
		super(id, 9, EnumEnchantmentType.all);
		this.setName(TheAndrewMod.MODID+".cactus");
	}
	public int getMinEnchantability(int lvl) {
		return 10+(int)Math.ceil(lvl*3.2);
	}
	public int getMaxEnchantability(int lvl) {
		return lvl*5;
	}
	public int getMaxLevel() {
		return 10;
	}

}
