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
	static AchievementPage page;
	public static void setup() {
		dyeCactus = new Achievement("achievement.theandrewmod.dyeCactus", "dyeCactus", 0, 0, new ItemStack(Items.dye, 1, 5), null).registerStat();
		invasivePlant = new Achievement("achievement.theandrewmod.invasivePlant", "invasivePlant", 2, 2, TheAndrewMod.invasivePlant, null).registerStat();
		shearThem = new Achievement("achievement.theandrewmod.shearThem", "shearThem", 2, -2, Items.shears, null).registerStat();
		butterDust = new Achievement("achievement.theandrewmod.butterDust", "butterDust", 4, 0, TheAndrewMod.butterDust, null).registerStat();
		jumpPad = new Achievement("achievement.theandrewmod.jumpPad", "jumpPad", 4, 2, TheAndrewMod.jumpPad, null);
		page = new AchievementPage(TheAndrewMod.NAME, dyeCactus, invasivePlant, shearThem, butterDust, jumpPad);
		AchievementPage.registerAchievementPage(page);
	}
}
