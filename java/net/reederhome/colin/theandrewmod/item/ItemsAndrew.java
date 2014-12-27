package net.reederhome.colin.theandrewmod.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemReed;
import net.minecraft.item.ItemSoup;
import net.reederhome.colin.theandrewmod.TheAndrewMod;
import net.reederhome.colin.theandrewmod.block.BlocksAndrew;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemsAndrew {
	public static Item potatoLiver = new ItemPotatoLiver().setTextureName(TheAndrewMod.MODID+":potatoLiver");
	public static Item plasticUtensils = new Item().setUnlocalizedName("plasticUtensils").setTextureName(TheAndrewMod.MODID+":plasticUtensils").setCreativeTab(TheAndrewMod.tabAndrew);
	public static Item butterDust = new ItemButterDust().setTextureName(TheAndrewMod.MODID+":butterDust");
	public static Item cactusGun=new ItemCactusGun().setTextureName(TheAndrewMod.MODID+":cactusGun");
	public static Item blastingDevice = new ItemBlastingDevice().setTextureName(TheAndrewMod.MODID+":blastingDevice");
	public static Item glassBottleHelmet = new ItemGlassBottleArmor(0,0).setUnlocalizedName("glassBottleHelmet");
	public static Item glassBottleChestplate = new ItemGlassBottleArmor(0,1).setUnlocalizedName("glassBottleChestplate");
	public static Item glassBottleLeggings = new ItemGlassBottleArmor(0,2).setUnlocalizedName("glassBottleLeggings");
	public static Item glassBottleBoots = new ItemGlassBottleArmor(0,3).setUnlocalizedName("glassBottleBoots");
	public static Item itemRedstoneCake = new ItemReed(BlocksAndrew.redstoneCake).setTextureName("cake").setUnlocalizedName("redstoneCake").setCreativeTab(TheAndrewMod.tabAndrew);
	public static Item itemWallumCooking = new ItemWallumCooking();
	public static Item itemWallum = new Item().setUnlocalizedName("wallum").setTextureName(TheAndrewMod.MODID+":wallum").setFull3D().setCreativeTab(TheAndrewMod.tabAndrew).setMaxDamage(7);
	public static Item rainbowCoreBasic = new Item().setUnlocalizedName("rainbowCoreBasic").setTextureName(TheAndrewMod.MODID+":rainbowCoreBasic").setCreativeTab(TheAndrewMod.tabAndrew);
	public static Item rainbowCoreAdvanced = new Item().setUnlocalizedName("rainbowCoreAdvanced").setTextureName(TheAndrewMod.MODID+":rainbowCoreAdvanced").setCreativeTab(TheAndrewMod.tabAndrew);
	public static Item bulbasauce = new ItemSoup(4).setUnlocalizedName("bulbasauce").setTextureName(TheAndrewMod.MODID+":bulbasauce").setCreativeTab(TheAndrewMod.tabAndrew);
	public static Item decoyBed = new ItemDecoyBed();
	public static Item[] pickaxeCactusGun;
	public static Item cactusGunPants = new CactusGunArmor(0, 2);
	public static Item wallumGrinding = new ItemWallumGrinding();
	public static Item wallumDust = new ItemWallumDust(new String[]{"Iron", "Gold", "Diamond", "Coal", "Emerald", "Quartz", "Cactus"});
	public static Item butterCluster = new ItemButterCluster();
	public static Item butterShellEmpty = new Item().setUnlocalizedName("butterShell").setTextureName(TheAndrewMod.MODID+":butterShellEmpty").setCreativeTab(TheAndrewMod.tabAndrew);
	public static Item butterShellFull = new ItemButterShell();
	public static Item curveball = new ItemCurveball();
	public static Item networkBoots = new NetworkArmor(0, 3);
	public static Item wallumStorage = new ItemWallumStorage(false);
	public static Item wallumStoraxe = new ItemWallumStorage(true);
	public static Item luckEgg = new ItemLuckEgg();
	
	
	public static Item.ToolMaterial[] t = new Item.ToolMaterial[]{Item.ToolMaterial.EMERALD,Item.ToolMaterial.GOLD,Item.ToolMaterial.IRON,Item.ToolMaterial.STONE,Item.ToolMaterial.WOOD};
	
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
		GameRegistry.registerItem(decoyBed, "itemDecoyBed");
		GameRegistry.registerItem(cactusGunPants, "cactusGunPants");
		GameRegistry.registerItem(wallumGrinding, "wallumGrinding");
		GameRegistry.registerItem(wallumDust, "wallumDust");
		GameRegistry.registerItem(butterCluster, "butterCluster");
		GameRegistry.registerItem(butterShellEmpty, "butterShell");
		GameRegistry.registerItem(butterShellFull, "butterShellFull");
		GameRegistry.registerItem(curveball, "curveball");
		GameRegistry.registerItem(networkBoots, "networkBoots");
		GameRegistry.registerItem(wallumStorage, "wallumStorage");
		GameRegistry.registerItem(wallumStoraxe, "wallumStoraxe");
		GameRegistry.registerItem(luckEgg, "luckEgg");
		
		
		pickaxeCactusGun = new Item[t.length];
		for(int i=0;i<t.length;i++) {
			pickaxeCactusGun[i]=new ItemPickaxeCactusGun(t[i]);
			GameRegistry.registerItem(pickaxeCactusGun[i], "pickaxeCactusGun"+t[i]);
		}
		
	}
}
