package net.reederhome.colin.theandrewmod;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.StatBase;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.AchievementPage;
import net.reederhome.colin.theandrewmod.block.BlocksAndrew;
import net.reederhome.colin.theandrewmod.item.ItemsAndrew;

public class AchievementsAndrew {

	public static Achievement dyeCactus;
	public static Achievement invasivePlant;
	public static Achievement shearThem;
	public static Achievement butterDust;
	public static Achievement jumpPad;
	public static Achievement cactusGun;
	public static Achievement cactusHoe;
	public static Achievement cactusPants;
	public static Achievement glassBottleArmor;
	public static Achievement potatoLiver;
	public static Achievement plasticVictim;
	public static Achievement glassBottleArmorFill;
	public static Achievement plasticUser;
	public static Achievement wallum;
	public static Achievement wallumCooking;
	public static Achievement wallumGrinding;
	public static Achievement tntChest;
	public static AchievementPage page;
	public static void setup() {
		dyeCactus = new Achievement("achievement.theandrewmod.dyeCactus", "dyeCactus", 0, 0, new ItemStack(Items.dye, 1, 5), null).registerStat();
		invasivePlant = new Achievement("achievement.theandrewmod.invasivePlant", "invasivePlant", 2, 2, BlocksAndrew.invasivePlant, null).registerStat();
		shearThem = new Achievement("achievement.theandrewmod.shearThem", "shearThem", 2, -2, Items.shears, null).registerStat();
		butterDust = new Achievement("achievement.theandrewmod.butterDust", "butterDust", 4, 0, ItemsAndrew.butterDust, null).registerStat();
		jumpPad = new Achievement("achievement.theandrewmod.jumpPad", "jumpPad", 4, 2, BlocksAndrew.jumpPad, null).registerStat();
		cactusGun = new Achievement("achievement.theandrewmod.cactusGun", "cactusGun", -4, -1, ItemsAndrew.cactusGun, null).registerStat();
		cactusHoe = new Achievement("achievement.theandrewmod.cactusHoe", "cactusHoe", -5, -1, Items.golden_hoe, cactusGun).registerStat();
		cactusPants = new Achievement("achievement.theandrewmod.cactusPants", "cactusPants", -3, -1, ItemsAndrew.cactusGunPants, cactusGun).registerStat();
		glassBottleArmor = new Achievement("achievement.theandrewmod.glassBottleArmor", "glassBottleArmor", -4, 1, ItemsAndrew.glassBottleChestplate, null).registerStat();
		glassBottleArmorFill = new Achievement("achievement.theandrewmod.glassBottleArmorFill", "glassBottleArmorFill", -3, 2, ItemsAndrew.glassBottleHelmet, glassBottleArmor).registerStat();
		potatoLiver = new Achievement("achievement.theandrewmod.potatoLiver", "potatoLiver", 0, 5, ItemsAndrew.potatoLiver, null).registerStat();
		plasticVictim = new Achievement("achievement.theandrewmod.plasticVictim", "plasticVictim", 1, 5, ItemsAndrew.plasticUtensils, null).registerStat();
		plasticUser = new Achievement("achievement.theandrewmod.plasticUser", "plasticUser", 0, 6, ItemsAndrew.plasticUtensils, null).registerStat();
		wallum = new Achievement("achievement.theandrewmod.wallum", "wallum", -10, 5, ItemsAndrew.itemWallum, null).registerStat();
		wallumCooking = new Achievement("achievement.theandrewmod.wallumCooking", "wallumCooking", -10, 6, ItemsAndrew.itemWallumCooking, wallum).registerStat();
		wallumGrinding = new Achievement("achievement.theandrewmod.wallumGrinding", "wallumGrinding", -11, 5, ItemsAndrew.wallumGrinding, wallum).registerStat();
		tntChest = new Achievement("achievement.theandrewmod.tntChest", "tntChest", 6, 2, BlocksAndrew.tntChest, null).registerStat();
		page = new AchievementPage(TheAndrewMod.NAME, dyeCactus, invasivePlant, shearThem, butterDust, jumpPad, cactusGun, glassBottleArmor, glassBottleArmorFill, potatoLiver, plasticVictim, plasticUser, cactusHoe, wallum, wallumCooking, wallumGrinding, tntChest, cactusPants);
		AchievementPage.registerAchievementPage(page);
	}
}
