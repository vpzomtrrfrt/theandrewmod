package net.reederhome.colin.theandrewmod;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class AchievementsAndrew {

	static Achievement dyeCactus;
	static Achievement invasivePlant;
	static Achievement shearThem;
	static Achievement butterDust;
	static Achievement jumpPad;
	static Achievement cactusGun;
	static Achievement glassBottleArmor;
	static Achievement potatoLiver;
	static Achievement plasticVictim;
	static AchievementPage page;
	public static void setup() {
		dyeCactus = new Achievement("achievement.theandrewmod.dyeCactus", "dyeCactus", 0, 0, new ItemStack(Items.dye, 1, 5), null).registerStat();
		invasivePlant = new Achievement("achievement.theandrewmod.invasivePlant", "invasivePlant", 2, 2, TheAndrewMod.invasivePlant, null).registerStat();
		shearThem = new Achievement("achievement.theandrewmod.shearThem", "shearThem", 2, -2, Items.shears, null).registerStat();
		butterDust = new Achievement("achievement.theandrewmod.butterDust", "butterDust", 4, 0, TheAndrewMod.butterDust, null).registerStat();
		jumpPad = new Achievement("achievement.theandrewmod.jumpPad", "jumpPad", 4, 2, TheAndrewMod.jumpPad, null).registerStat();
		cactusGun = new Achievement("achievement.theandrewmod.cactusGun", "cactusGun", -4, -1, TheAndrewMod.cactusGun, null).registerStat();
		glassBottleArmor = new Achievement("achievement.theandrewmod.glassBottleArmor", "glassBottleArmor", -4, 1, TheAndrewMod.glassBottleChestplate, null).registerStat();
		potatoLiver = new Achievement("achievement.theandrewmod.potatoLiver", "potatoLiver", 0, 5, TheAndrewMod.potatoLiver, null).registerStat();
		plasticVictim = new Achievement("achievement.theandrewmod.plasticVictim", "plasticVictim", 1, 5, TheAndrewMod.plasticUtensils, null);
		page = new AchievementPage(TheAndrewMod.NAME, dyeCactus, invasivePlant, shearThem, butterDust, jumpPad, cactusGun, glassBottleArmor, potatoLiver, plasticVictim);
		AchievementPage.registerAchievementPage(page);
	}
}
