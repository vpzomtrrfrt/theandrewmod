package net.reederhome.colin.theandrewmod;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class CactusEnchantment extends Enchantment {

	protected CactusEnchantment(int id) {
		super(id, 9, EnumEnchantmentType.all);
		this.setName("theandrewmod.cactus");
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
