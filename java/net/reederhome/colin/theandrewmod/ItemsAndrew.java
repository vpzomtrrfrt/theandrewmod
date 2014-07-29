package net.reederhome.colin.theandrewmod;

import net.minecraft.item.Item;
import net.minecraft.item.ItemReed;
import net.minecraft.item.ItemSoup;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemsAndrew {
	static Item potatoLiver = new ItemPotatoLiver().setTextureName(TheAndrewMod.MODID+":potatoLiver");
	static Item plasticUtensils = new Item().setUnlocalizedName("plasticUtensils").setTextureName(TheAndrewMod.MODID+":plasticUtensils").setCreativeTab(TheAndrewMod.tabAndrew);
	static Item butterDust = new ItemButterDust().setTextureName(TheAndrewMod.MODID+":butterDust");
	static Item cactusGun=new ItemCactusGun().setTextureName(TheAndrewMod.MODID+":cactusGun");
	static Item blastingDevice = new ItemBlastingDevice().setTextureName(TheAndrewMod.MODID+":blastingDevice");
	static Item glassBottleHelmet = new ItemGlassBottleArmor(0,0).setUnlocalizedName("glassBottleHelmet");
	static Item glassBottleChestplate = new ItemGlassBottleArmor(0,1).setUnlocalizedName("glassBottleChestplate");
	static Item glassBottleLeggings = new ItemGlassBottleArmor(0,2).setUnlocalizedName("glassBottleLeggings");
	static Item glassBottleBoots = new ItemGlassBottleArmor(0,3).setUnlocalizedName("glassBottleBoots");
	static Item itemRedstoneCake = new ItemReed(BlocksAndrew.redstoneCake).setTextureName("cake").setUnlocalizedName("redstoneCake").setCreativeTab(TheAndrewMod.tabAndrew);
	static Item itemWallumCooking = new ItemWallumCooking();
	static Item itemWallum = new Item().setUnlocalizedName("wallum").setTextureName(TheAndrewMod.MODID+":wallum").setFull3D().setCreativeTab(TheAndrewMod.tabAndrew);
	static Item rainbowCoreBasic = new Item().setUnlocalizedName("rainbowCoreBasic").setTextureName(TheAndrewMod.MODID+":rainbowCoreBasic").setCreativeTab(TheAndrewMod.tabAndrew);
	static Item rainbowCoreAdvanced = new Item().setUnlocalizedName("rainbowCoreAdvanced").setTextureName(TheAndrewMod.MODID+":rainbowCoreAdvanced").setCreativeTab(TheAndrewMod.tabAndrew);
	static Item bulbasauce = new ItemSoup(4).setUnlocalizedName("bulbasauce").setTextureName(TheAndrewMod.MODID+":bulbasauce").setCreativeTab(TheAndrewMod.tabAndrew);
	
	public static void registerItems() {
		GameRegistry.registerItem(potatoLiver, "potatoLiver");
		GameRegistry.registerItem(plasticUtensils, "plasticUtensils");
		GameRegistry.registerItem(butterDust, "butterDust");
		GameRegistry.registerItem(cactusGun, "cactusGun");
		GameRegistry.registerItem(blastingDevice, "blastingDevice");
		GameRegistry.registerItem(glassBottleHelmet, "glassBottleHelmet");
		GameRegistry.registerItem(glassBottleChestplate, "glassBottleChestplate");
		GameRegistry.registerItem(glassBottleLeggings, "glassBottleLeggings");
		GameRegistry.registerItem(glassBottleBoots, "glassBottleBoots");
		GameRegistry.registerItem(itemRedstoneCake, "itemRedstoneCake");
		GameRegistry.registerItem(itemWallumCooking, "wallumCooking");
		GameRegistry.registerItem(itemWallum, "wallum");
		GameRegistry.registerItem(rainbowCoreBasic, "rainbowCoreBasic");
		GameRegistry.registerItem(rainbowCoreAdvanced, "rainbowCoreAdvanced");
		GameRegistry.registerItem(bulbasauce, "bulbasauce");
	}
}
