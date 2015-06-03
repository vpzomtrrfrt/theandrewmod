package net.reederhome.colin.theandrewmod;

import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import morph.api.Ability;
import morph.api.Api;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemMinecart;
import net.minecraft.item.ItemSoup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.util.WeightedRandomFishable;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBeach;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.common.FishingHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.minecart.MinecartCollisionEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.reederhome.colin.theandrewmod.block.BlockDyedCake;
import net.reederhome.colin.theandrewmod.block.BlocksAndrew;
import net.reederhome.colin.theandrewmod.client.ClientProxy;
import net.reederhome.colin.theandrewmod.command.BeCactusCommand;
import net.reederhome.colin.theandrewmod.command.BeCommandBlockCommand;
import net.reederhome.colin.theandrewmod.command.FirmMatressCommand;
import net.reederhome.colin.theandrewmod.command.RadiationCommand;
import net.reederhome.colin.theandrewmod.command.SevenEightNineCommand;
import net.reederhome.colin.theandrewmod.command.SnorlaxCommand;
import net.reederhome.colin.theandrewmod.command.YesCommand;
import net.reederhome.colin.theandrewmod.enchantment.CactusEnchantment;
import net.reederhome.colin.theandrewmod.enchantment.ClusterProtectionEnchantment;
import net.reederhome.colin.theandrewmod.entity.EntityCharlie;
import net.reederhome.colin.theandrewmod.entity.EntityCurveball;
import net.reederhome.colin.theandrewmod.entity.EntityHPCreeper;
import net.reederhome.colin.theandrewmod.entity.EntityItemButterDust;
import net.reederhome.colin.theandrewmod.entity.EntityJack;
import net.reederhome.colin.theandrewmod.entity.EntityKevin;
import net.reederhome.colin.theandrewmod.entity.EntityLuckEgg;
import net.reederhome.colin.theandrewmod.entity.EntityProjectileHorse;
import net.reederhome.colin.theandrewmod.entity.EntityThomas;
import net.reederhome.colin.theandrewmod.entity.EntityThrownCactus;
import net.reederhome.colin.theandrewmod.entity.EntityTrevor;
import net.reederhome.colin.theandrewmod.entity.EntityZombieCow;
import net.reederhome.colin.theandrewmod.item.ItemGlassBottleArmor;
import net.reederhome.colin.theandrewmod.item.ItemWallumDust;
import net.reederhome.colin.theandrewmod.item.ItemsAndrew;
import net.reederhome.colin.theandrewmod.support.morph.AbilityExplodeOnDeath;
import net.reederhome.colin.theandrewmod.support.morph.AbilityThrowPotions;
import net.reederhome.colin.theandrewmod.tileentity.TileEntityJumpPad;
import net.reederhome.colin.theandrewmod.tileentity.TileEntitySideSlab;
import net.reederhome.colin.theandrewmod.tileentity.TileEntitySidedChest;
import net.reederhome.colin.theandrewmod.tileentity.TileEntityTNTChest;
import net.reederhome.colin.theandrewmod.tileentity.TileEntityWallumagicalChest;
import net.reederhome.colin.theandrewmod.world.BiomeGenDessert;
import net.reederhome.colin.theandrewmod.world.WorldGeneratorAndrew;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = TheAndrewMod.MODID, version = TheAndrewMod.VERSION, name = TheAndrewMod.NAME)
public class TheAndrewMod implements IFuelHandler {

	public static final String MODID = "theandrewmod";
	public static final String VERSION = "1.17.0";
	public static final String NAME = "The Andrew Mod";
	public static CreativeTabs tabAndrew = new CreativeTabs(CreativeTabs.getNextID(), "theandrewmod") {	
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return ItemsAndrew.potatoLiver;
		}
	};
	public static ItemArmor.ArmorMaterial glassBottleArmorMaterial = EnumHelper.addArmorMaterial("glassBottle", 8, new int[]{1, 3, 3, 1}, 16);
	public static ItemArmor.ArmorMaterial cactusGunArmorMaterial = EnumHelper.addArmorMaterial("cactusGun", 12, new int[]{1,1,2,1}, 20);
	public static ItemArmor.ArmorMaterial networkArmorMaterial = EnumHelper.addArmorMaterial("network", 12, new int[]{3,9,6,3}, 20);
	public static Item.ToolMaterial diamondBlockMaterial = EnumHelper.addToolMaterial("diamondBlock", 18, 9366, 48, 18, 60);
	public static DamageSource deathBy789 = new DamageSource(MODID+".deathBy789");
	public static DamageSource deathByPotatoLiver = new DamageSource(MODID+".deathByPotatoLiver");
	public static DamageSource deathByCancer = new DamageSource(MODID+".deathByCancer");
	public static DamageSource deathByCluster = new DamageSource(MODID+".deathByCluster");
	public static DamageSource deathByGlass = new DamageSource(MODID+".deathByGlass");
	public static DamageSource deathByCharlie = new DamageSource(MODID+".deathByCharlie");
	public static DamageSource deathByGrindingWallum = new DamageSource(MODID+".deathByGrindingWallum");
	public static DamageSource deathByCrafting = new DamageSource(MODID+".deathByCrafting");
	public static Enchantment cactusEnchantment;
	public static Enchantment clusterProtection;
	public static Potion cancerPotion;
	public static Potion clusterPotion;
	public static Potion spinningPotion;
	public static int teidThomas;
	public static int teidJack;
	public static BiomeGenBase biomeDessert;
	public static SimpleNetworkWrapper netWrap;
	public static boolean baublesDetected = false;
	
	public static int avid;
	public static boolean debugMode;
	
	static Configuration config;
	
	@SidedProxy(serverSide="net.reederhome.colin.theandrewmod.CommonProxy", clientSide="net.reederhome.colin.theandrewmod.client.ClientProxy")
	static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		debugMode = config.getBoolean("debugMode", "misc", false, "Some extra logging for debugging and showcases");
		avid = config.getInt("villagerId", "ids", 24, 0, 4096, "id for Andrew");
		biomeDessert = new BiomeGenDessert(config.getInt("dessertBiomeId", "ids", 24, 0, 4096, "id for Dessert Biome"));
		teidThomas = EntityRegistry.findGlobalUniqueEntityId();
		try {
			Class.forName("baubles.common.lib.PlayerHandler");
			baublesDetected = true;
		} catch(Exception e) {}
		EntityRegistry.registerGlobalEntityID(EntityThomas.class, "Thomas", teidThomas, Color.black.getRGB(), Color.cyan.getRGB());
		EntityRegistry.registerModEntity(EntityThomas.class, "Thomas", teidThomas, MODID, 128, 1, true);
		WeightedRandomChestContent wrcct = new WeightedRandomChestContent(new ItemStack(Items.spawn_egg, 1, teidThomas), 0, 10, 4);
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, wrcct);
		ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, wrcct);
		FishingHooks.addTreasure(new WeightedRandomFishable(new ItemStack(Items.spawn_egg, 1, teidThomas), 1));
		EntityRegistry.addSpawn(EntityThomas.class, 40, 1, 10, EnumCreatureType.monster, BiomeGenBase.hell);
		teidJack = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityJack.class, "Jack", teidJack, new Color(140,140,140).getRGB(), Color.red.getRGB());
		EntityRegistry.registerModEntity(EntityJack.class, "Jack", teidJack, MODID, 128, 1, true);
		EntityRegistry.addSpawn(EntityJack.class, 14, 1, 10, EnumCreatureType.creature, BiomeGenBase.plains);
		int teid = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityTrevor.class, "Trevor", teid);
		EntityRegistry.registerModEntity(EntityTrevor.class, "Trevor", teid, MODID, 128, 1, true);
		EntityRegistry.addSpawn(EntityTrevor.class, 14, 1, 4, EnumCreatureType.creature, BiomeGenBase.plains);
		teid = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityThrownCactus.class, "ThrownCactus", teid);
		EntityRegistry.registerModEntity(EntityThrownCactus.class, "ThrownCactus", teid, MODID, 128, 1, true);
		teid = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityCharlie.class, "Charlie", teid);
		EntityRegistry.registerModEntity(EntityCharlie.class, "Charlie", teid, MODID, 128, 1, true);
		teid = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityItemButterDust.class, "ItemButterDust", teid);
		EntityRegistry.registerModEntity(EntityItemButterDust.class, "ItemButterDust", teid, MODID, 128, 1, true);
		teid = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityHPCreeper.class, "HPCreeper", teid, 0, 0x707070);
		EntityRegistry.registerModEntity(EntityHPCreeper.class, "HPCreeper", teid, MODID, 128, 1, true);
		EntityRegistry.addSpawn(EntityHPCreeper.class, 10, 1, 4, EnumCreatureType.monster, BiomeGenBase.plains);
		teid = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityCurveball.class, "Curveball", teid);
		EntityRegistry.registerModEntity(EntityCurveball.class, "Curveball", teid, MODID, 128, 1, true);
		teid = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityZombieCow.class, "ZombieCow", teid, 4470310, 7969893);
		EntityRegistry.registerModEntity(EntityZombieCow.class, "ZombieCow", teid, MODID, 128, 1, true);
		EntityRegistry.addSpawn(EntityZombieCow.class, 8, 1, 4, EnumCreatureType.monster, BiomeGenBase.plains);
		DungeonHooks.addDungeonMob("ZombieCow", 20);
		teid = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityLuckEgg.class, "LuckEgg", teid);
		EntityRegistry.registerModEntity(EntityLuckEgg.class, "LuckEgg", teid, MODID, 128, 1, true);
		teid = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityKevin.class, "Kevin", teid);
		EntityRegistry.registerModEntity(EntityKevin.class, "Kevin", teid, MODID, 128, 1, true);
		EntityRegistry.addSpawn(EntityKevin.class, 14, 1, 2, EnumCreatureType.creature, BiomeGenBase.desert, BiomeGenBase.swampland, BiomeGenBase.jungle, biomeDessert);
		teid = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityProjectileHorse.class, "ProjectileHorse", teid);
		EntityRegistry.registerModEntity(EntityProjectileHorse.class, "ProjectileHorse", teid, MODID, 128, 1, true);
		GameRegistry.registerTileEntity(TileEntitySideSlab.class, "sideSlab");
		GameRegistry.registerTileEntity(TileEntityJumpPad.class, "jumpPad");
		GameRegistry.registerTileEntity(TileEntityTNTChest.class, "TNTChest");
		GameRegistry.registerTileEntity(TileEntityWallumagicalChest.class, "WallumagicalChest");
		GameRegistry.registerTileEntity(TileEntitySidedChest.class, "SidedChest");
		deathByPotatoLiver.setDamageIsAbsolute();
		deathByPotatoLiver.setDamageAllowedInCreativeMode();
		System.out.println("Hello?");
		int cpi = registerPotion();
		System.out.println("cpi="+cpi);
		cancerPotion = new PotionAndrew(cpi, false, Color.black.getRGB()).setPotionName("potion.cancerPotion");
		int lpi = registerPotion();
		clusterPotion = new PotionAndrew(lpi, false, new Color(0, 32, 32).getRGB()).setPotionName("potion.clusterPotion");
		int spi = registerPotion();
		spinningPotion = new PotionAndrew(spi, false, new Color(55, 200, 200).getRGB()).setPotionName("potion.spinningPotion"); 
		BlocksAndrew.registerBlocks();
		ItemsAndrew.registerItems();
		GameRegistry.addRecipe(new ItemStack(BlocksAndrew.sideSlab), "s", "s", "s", 's', Blocks.stone_slab);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemsAndrew.potatoLiver), Items.bone, Items.potato, Items.wooden_sword);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemsAndrew.butterDust, 4), Items.gold_nugget, Items.milk_bucket, ItemsAndrew.plasticUtensils, BlocksAndrew.invasivePlant);
		GameRegistry.addRecipe(new ItemStack(ItemsAndrew.blastingDevice), "ttt", "ttt", "stt", 't', Blocks.tnt, 's', Blocks.lever);
		GameRegistry.addRecipe(new ItemStack(ItemsAndrew.cactusGun), "cbc", "coc", "c  ", 'c', Blocks.cactus, 'b', Items.bow, 'o', Blocks.obsidian);
		cactusEnchantment = new CactusEnchantment(config.getInt("enchantmentCactus", "ids", 24, 0, 4096, "id for Cactus Enchantment"));
		clusterProtection = new ClusterProtectionEnchantment(config.getInt("enchantmentClusterProtection", "ids", 25, 0, 4096, "id for Cluster Protection Enchantment")); 
		GameRegistry.addRecipe(new ItemStack(ItemsAndrew.glassBottleHelmet), "bbb", "b b", "   ", 'b', Items.glass_bottle);
		GameRegistry.addRecipe(new ItemStack(ItemsAndrew.glassBottleChestplate), "b b", "bbb", "bbb", 'b', Items.glass_bottle);
		GameRegistry.addRecipe(new ItemStack(ItemsAndrew.glassBottleLeggings), "bbb", "b b", "b b", 'b', Items.glass_bottle);
		GameRegistry.addRecipe(new ItemStack(ItemsAndrew.glassBottleBoots), "   ", "b b", "b b", 'b', Items.glass_bottle);
		RecipeSorter.register("wallumCooking", RecipesWallumCooking.class, Category.SHAPELESS, "");
		GameRegistry.addRecipe(new RecipesWallumCooking());
		if(config.getBoolean("butterShell", "crafting", false, "It's broken right now...")) {
			RecipeSorter.register("butterShell", RecipesButterShell.class, Category.SHAPELESS, "");
			GameRegistry.addRecipe(new RecipesButterShell());
		}
		RecipeSorter.register("wallumGrinding", RecipesWallumGrinding.class, Category.SHAPELESS, "");
		RecipeSorter.register("wallumEating", RecipesWallumEating.class, Category.SHAPELESS, "");
		GameRegistry.addRecipe(new RecipesWallumGrinding());
		GameRegistry.addRecipe(new RecipesWallumEating());
		GameRegistry.addRecipe(new ShapedOreRecipe(ItemsAndrew.itemWallum, "w", "s", "i", 'i', "ingotIron", 's', "stickWood", 'w', "plankWood"));
		GameRegistry.addRecipe(new ItemStack(BlocksAndrew.blockCactusGun), "rcr", "gig", "rcr", 'r', Items.redstone, 'c', Blocks.cobblestone, 'g', ItemsAndrew.cactusGun, 'i', Items.iron_ingot);
		GameRegistry.addRecipe(new ItemStack(BlocksAndrew.rainbowMachine), "scs", "crc", "scs", 's', Items.redstone, 'c', Blocks.cobblestone, 'r', ItemsAndrew.rainbowCoreAdvanced);
		GameRegistry.addRecipe(new ItemStack(ItemsAndrew.cactusGunPants), "lgl", "c c", "c c", 'l', Items.leather, 'g', ItemsAndrew.cactusGun, 'c', Blocks.cactus);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemsAndrew.decoyBed), Items.bed, Items.gunpowder, Blocks.stone_button, Items.redstone);
		GameRegistry.addShapelessRecipe(new ItemStack(BlocksAndrew.tntChest), Blocks.tnt, Blocks.chest, Items.gunpowder, dye("green"));
		GameRegistry.addShapelessRecipe(new ItemStack(ItemsAndrew.itemRedstoneCake), Items.cake, Items.redstone, Blocks.stone_stairs, Blocks.stone_pressure_plate, Blocks.wooden_button);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemsAndrew.rainbowCoreBasic), dye("black"), dye("white"), dye("red"), dye("lime"), Items.redstone, dye("blue"), dye("yellow"), dye("cyan"), dye("magenta"));
		GameRegistry.addShapelessRecipe(new ItemStack(ItemsAndrew.rainbowCoreAdvanced), dye("brown"), dye("purple"), dye("silver"), dye("gray"), ItemsAndrew.rainbowCoreBasic, dye("pink"), dye("green"), dye("lightBlue"), dye("orange"));
		GameRegistry.addShapelessRecipe(new ItemStack(ItemsAndrew.bulbasauce), Items.wheat_seeds, Items.wheat_seeds, Items.wheat_seeds, Items.bowl);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemsAndrew.wallumGrinding, 1, 2100), Items.iron_ingot, ItemsAndrew.itemWallum, Blocks.redstone_block, Items.flint, Items.flint, Items.flint);
		VillagerRegistry.instance().registerVillagerId(avid);
		VillagerRegistry.instance().registerVillageTradeHandler(avid, new TradeHandlerAndrew());
		BiomeDictionary.registerBiomeType(biomeDessert, BiomeDictionary.Type.WASTELAND);
		BiomeManager.addSpawnBiome(biomeDessert);
		GameRegistry.registerWorldGenerator(new WorldGeneratorAndrew(), 8);
		for(int d = 0; d < 16; d++) {
			GameRegistry.addSmelting(new ItemStack(BlocksAndrew.dyedCactus, 1, d), new ItemStack(Items.dye, 1, d), 17);
		}
		AchievementsAndrew.setup();
		for(int m = 0; m < ItemsAndrew.t.length; m++) {
			GameRegistry.addRecipe(new ItemStack(ItemsAndrew.pickaxeCactusGun[m]), "mmm", "rsl", "rsc", 'm', ItemsAndrew.t[m].func_150995_f(), 'c', ItemsAndrew.cactusGun, 's', Items.stick, 'l', Items.slime_ball, 'r', Items.redstone);
			GameRegistry.addRecipe(new ItemStack(ItemsAndrew.pickaxeCactusGun[m]), "mmm", "lsr", "csr", 'm', ItemsAndrew.t[m].func_150995_f(), 'c', ItemsAndrew.cactusGun, 's', Items.stick, 'l', Items.slime_ball, 'r', Items.redstone);
		}
		GameRegistry.addRecipe(new ItemStack(BlocksAndrew.pakistan), " rc", " mr", "cr ", 'r', Items.redstone, 'm', Items.map, 'c', Blocks.cactus);
		GameRegistry.addRecipe(new ItemStack(BlocksAndrew.checkerboard, 8), "wbw", "brb", "wbw", 'w', new ItemStack(Blocks.wool, 1, 0), 'b', new ItemStack(Blocks.wool, 1, 15), 'r', Items.redstone);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemsAndrew.curveball), Items.snowball, Items.snowball);
		GameRegistry.addRecipe(new ItemStack(ItemsAndrew.networkBoots), "   ", "i l", "r p", 'i', Items.iron_ingot, 'l', Items.leather, 'r', Items.redstone, 'p', Items.repeater);
		GameRegistry.addShapelessRecipe(new ItemStack(BlocksAndrew.wallumagicalChest), Blocks.chest, ItemsAndrew.itemWallum, Blocks.chest);
		GameRegistry.addRecipe(new ItemStack(BlocksAndrew.sidedChest), "crc", 'c', Blocks.chest, 'r', Blocks.redstone_block);
		GameRegistry.addRecipe(new ItemStack(ItemsAndrew.luckEgg, 4), "rgr", "geg", "rgr", 'r', Items.redstone, 'g', Items.gold_nugget, 'e', Items.egg);
		if(config.getBoolean("enableStorageWallum", "crafting", true, "Allow the storage wallum to be crafted.")) {
			GameRegistry.addRecipe(new ItemStack(ItemsAndrew.wallumStorage), "c", "s", "w", 'c', Blocks.chest, 's', Items.stick, 'w', ItemsAndrew.itemWallum);
			GameRegistry.addRecipe(new ItemStack(ItemsAndrew.wallumStoraxe), "c", "s", "w", 'c', Blocks.chest, 's', Items.iron_axe, 'w', ItemsAndrew.itemWallum);
		}
		GameRegistry.addRecipe(new ItemStack(ItemsAndrew.fireDonut, 4), "wsw", "shs", "wsw", 'w', Items.wheat, 's', Items.sugar, 'h', ItemsAndrew.horseBomb);
		GameRegistry.addRecipe(new ItemStack(ItemsAndrew.cactusGunJetpack), "c c", "lll", "g g", 'c', Blocks.cactus, 'l', Items.leather, 'g', ItemsAndrew.cactusGun);
		if(baublesDetected) {
			GameRegistry.addRecipe(new ItemStack(ItemsAndrew.cactusGunBelt), " l ", "lsl", "ylg", 'l', Items.leather, 's', Items.string, 'y', Items.slime_ball, 'g', ItemsAndrew.cactusGun);
		}
		GameRegistry.addRecipe(new ShapedOreRecipe(ItemsAndrew.diamondBlockSword, " d ", " d ", " s ", 'd', "blockDiamond", 's', "stickWood"));
		GameRegistry.addSmelting(BlocksAndrew.cactusOre, new ItemStack(Blocks.cactus), 22);
		OreDictionary.registerOre("oreCactus", BlocksAndrew.cactusOre);
		netWrap = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
		netWrap.registerMessage(CactusFireMessage.Handler.class, CactusFireMessage.class, 0, Side.SERVER);
		netWrap.registerMessage(NetworkBootMessage.Handler.class, NetworkBootMessage.class, 1, Side.SERVER);
		netWrap.registerMessage(CactusJetpackMessage.Handler.class, CactusJetpackMessage.class, 2, Side.SERVER);
		EntityLuckEgg.initActions();
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(ItemsAndrew.luckEgg), 1, 16, 3));
		FishingHooks.addTreasure(new WeightedRandomFishable(new ItemStack(ItemsAndrew.luckEgg), 1));
		cactusGunArmorMaterial.customCraftingMaterial=Item.getItemFromBlock(Blocks.cactus);
		diamondBlockMaterial.setRepairItem(new ItemStack(Blocks.diamond_block));
		
		/**Morph Compat*/
		/***/
		Ability.registerAbility("throwPotions", AbilityThrowPotions.class);
		Ability.registerAbility("explodeOnDeath", AbilityExplodeOnDeath.class);
		Ability.mapAbilities(EntityZombieCow.class, Ability.createNewAbilityByType("sunburn", null), Ability.createNewAbilityByType("hostile", null));
		Ability.mapAbilities(EntityHPCreeper.class, new AbilityThrowPotions());
		Ability.mapAbilities(EntityThomas.class, Ability.createNewAbilityByType("fireImmunity", null), new AbilityExplodeOnDeath());
		/***/
		
		FluidContainerRegistry.registerFluidContainer(BlocksAndrew.liquidGunpower, new ItemStack(ItemsAndrew.liquidGunpowderBucket), new ItemStack(Items.bucket));
		
		config.save();
	}
	
	public ItemStack dye(String string) {
		for(int i = 0; i < 16; i++) {
			if(string.equalsIgnoreCase(ItemDye.field_150923_a[i])) {
				return new ItemStack(Items.dye, 1, i);
			}
		}
		return null;
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent ev) {
		Iterator<String> iter = ItemWallumDust.listWallumDust.keySet().iterator();
		while(iter.hasNext()) {
			String ore = iter.next();
			ArrayList<ItemStack> orei = OreDictionary.getOres("ore"+ore);
			for(int i = 0; i < orei.size(); i++) {
				ItemStack res = FurnaceRecipes.smelting().getSmeltingResult(orei.get(i));
				if(res!=null) {
					ItemStack from = ItemWallumDust.listWallumDust.get(ore);
					GameRegistry.addSmelting(from, res, FurnaceRecipes.smelting().func_151398_b(from));
					break;
				}
			}
		}
	}

	private int registerPotion() {
		Potion[] potionTypes = null;

		for (Field f : Potion.class.getDeclaredFields()) {
		f.setAccessible(true);
		try {
		if (f.getName().equals("potionTypes") || f.getName().equals("field_76425_a")) {
		Field modfield = Field.class.getDeclaredField("modifiers");
		modfield.setAccessible(true);
		modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);

		potionTypes = (Potion[])f.get(null);
		final Potion[] newPotionTypes = new Potion[256];
		System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
		f.set(null, newPotionTypes);
		for(int p = 1; p < newPotionTypes.length; p++) {
			if(newPotionTypes[p]==null) {
				return p;
			}
		}
		}
		}
		catch (Exception e) {
		System.err.println("Severe error, please report this to the mod author:");
		System.err.println(e);
		}
		}
		return 32;
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.registerRenderers();
		MinecraftForge.EVENT_BUS.register(this);
		FMLCommonHandler.instance().bus().register(this);
		GameRegistry.registerFuelHandler(this);
	}
	
	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		event.registerServerCommand(new SevenEightNineCommand());
		event.registerServerCommand(new FirmMatressCommand());
		event.registerServerCommand(new SnorlaxCommand());
		event.registerServerCommand(new BeCommandBlockCommand());
		event.registerServerCommand(new YesCommand());
		event.registerServerCommand(new RadiationCommand());
		event.registerServerCommand(new BeCactusCommand());
	}
	
	int lastJetpackFire = 0;
	
	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent e) {
		if(e.entityLiving.getEquipmentInSlot(4)!=null&&e.entityLiving.getEquipmentInSlot(4).getItem().getUnlocalizedName().equals(Blocks.command_block.getUnlocalizedName())) {
			e.entityLiving.addPotionEffect(new PotionEffect(14, 2, 1, false));
		}
		if(e.entityLiving.isPotionActive(TheAndrewMod.cancerPotion)) {
			PotionEffect pe = e.entityLiving.getActivePotionEffect(cancerPotion);
			if(new Random().nextInt(Math.max(1,(int)Math.floor(pe.getDuration()/12)))==0) {
				e.entityLiving.attackEntityFrom(deathByCancer, 1);
				if(pe.getDuration()==1) {
					e.entityLiving.attackEntityFrom(deathByCancer, 10);
				}
			}
		}
		if(e.entityLiving.isPotionActive(TheAndrewMod.clusterPotion)) {
			double r = 10;
			List<EntityLivingBase> stuff = e.entity.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(e.entity.posX-r, e.entity.posY-r, e.entity.posZ-r, e.entity.posX+r, e.entity.posY+r, e.entity.posZ+r));
			Iterator<EntityLivingBase> i = stuff.iterator();
			while(i.hasNext()) {
				EntityLivingBase c = i.next();
				if(e.entity.getDistanceToEntity(c)<r&&!e.entity.equals(c)) {
					if(e.entityLiving.getEquipmentInSlot(3)==null||EnchantmentHelper.getEnchantmentLevel(clusterProtection.effectId, e.entityLiving.getEquipmentInSlot(3))==0) {
						e.entityLiving.attackEntityFrom(TheAndrewMod.deathByCluster, (float)r-e.entity.getDistanceToEntity(c));
					}
					if(c.getEquipmentInSlot(3)==null||EnchantmentHelper.getEnchantmentLevel(clusterProtection.effectId, c.getEquipmentInSlot(3))==0) {
						c.attackEntityFrom(TheAndrewMod.deathByCluster, (float)r-c.getDistanceToEntity(e.entity));
					}
				}
			}
		}
		if(e.entityLiving.isPotionActive(spinningPotion)) {
			e.entity.setAngles(e.entity.rotationPitch+5, e.entity.rotationYaw+5);
			e.entityLiving.setRotationYawHead(e.entityLiving.rotationYawHead+5);
		}
		if(proxy instanceof ClientProxy && Minecraft.getMinecraft().thePlayer==e.entity) {
			if(((ClientProxy)proxy).keyCactusJetpack.getIsKeyPressed()) {
				if(lastJetpackFire+5<e.entity.ticksExisted) {
					lastJetpackFire = e.entity.ticksExisted;
					netWrap.sendToServer(new CactusJetpackMessage());
				}
			}
			else {
				lastJetpackFire=0;
			}
		}
	}
	
	@SubscribeEvent
	public void onItemPickup(EntityItemPickupEvent event) {
		if(event.item.getEntityItem().getItem().equals(ItemsAndrew.potatoLiver)&&!(event.entityPlayer!=null&&event.item.func_145798_i()==event.entityPlayer.getCommandSenderName())) {
			event.entityLiving.attackEntityFrom(TheAndrewMod.deathByPotatoLiver, 100f);
			event.setCanceled(true);
			event.item.setDead();
		}
	}
	
	@SubscribeEvent
	public void onMinecartCollide(MinecartCollisionEvent evt) {
		double ydif = evt.collider.posY-evt.minecart.posY;
		if(evt.collider instanceof EntityPlayer && ydif>-1) {
			EntityPlayer p = (EntityPlayer) evt.collider;
			if(p.inventory.hasItem(Item.getItemFromBlock(BlocksAndrew.sideSlab))) {
				for(int i = 0; i < p.inventory.getSizeInventory(); i++) {
					ItemStack it = p.inventory.getStackInSlot(i);
					if(it!=null&&it.getItem().equals(Item.getItemFromBlock(BlocksAndrew.sideSlab)) && !it.hasDisplayName()) {
						ItemStack ni = it.copy();
						ni.setStackDisplayName("Knee Injury");
						p.inventory.setInventorySlotContents(i, ni);
						break;
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onBlockDrops(HarvestDropsEvent event) {
		Iterator<ItemStack> it = event.drops.iterator();
		boolean dropsItself = false;
		while(it.hasNext()) {
			ItemStack stack = it.next();
			if(stack.getItem().equals(Item.getItemFromBlock(event.block))) {
				dropsItself = true;
			}
		}
		if(event.block==Blocks.leaves || event.block==Blocks.leaves2) {
			if(!dropsItself) {
				BiomeGenBase biome = event.world.getBiomeGenForCoords(event.x, event.z);
				if(biome instanceof BiomeGenDesert || biome instanceof BiomeGenBeach) {
					double r = Math.random();
					if(r<0.01) {
						event.drops.add(new ItemStack(ItemsAndrew.horseBomb));
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onEntityInteract(EntityInteractEvent event) {
		ItemStack stack = event.entityPlayer.getCurrentEquippedItem();
		if(!event.target.worldObj.isRemote) {
			if(stack!=null) {
				if(stack.getItem().equals(ItemsAndrew.plasticUtensils)) {
					stack.stackSize--;
					event.target.attackEntityFrom(new EntityDamageSource(deathByPotatoLiver.damageType+".player", event.entityPlayer), 100f);
					if(event.target instanceof EntityPlayer) {
						((EntityPlayer) event.target).addStat(AchievementsAndrew.plasticVictim, 1);
					}
					event.entityPlayer.addStat(AchievementsAndrew.plasticUser, 1);
				}
				else if((stack.getItem() instanceof ItemMinecart)&&(event.target instanceof EntityBat)&&event.target.riddenByEntity==null) {
					stack.stackSize--;
					EntityMinecart cart = EntityMinecart.createMinecart(event.target.worldObj, event.target.posX, event.target.posY, event.target.posZ, ((ItemMinecart)stack.getItem()).minecartType);
					event.entity.worldObj.spawnEntityInWorld(cart);
					cart.mountEntity(event.target);
				}
				else if(stack.getItem().equals(new ItemStack(Blocks.cactus).getItem())&&(event.target instanceof EntityCow)) {
					stack.stackSize--;
					if(Math.random()<0.005) {
						event.target.dropItem(Items.saddle, (int)(Math.abs(new Random().nextGaussian())*16));
					}
				}
				else if(stack.getItem().equals(Items.shears)&&(event.target instanceof EntitySheep)&&!((EntitySheep)event.target).getSheared()) {
					Calendar var5 = event.target.worldObj.getCurrentDate();
					if(var5.get(2)+1==2&&var5.get(5)==14&&Math.random()<0.5) {
						event.target.dropItem(Items.cookie, new Random().nextInt(4)+1);
					}
				}
				else if(stack.getItem().equals(ItemsAndrew.multiplier)&&(event.target instanceof EntityTNTPrimed)) {
					event.target.setDead();
					event.target.dropItem(Item.getItemFromBlock(Blocks.tnt), 1);
					stack.damageItem(1, event.entityLiving);
					event.entityPlayer.addStat(AchievementsAndrew.multiplier, 1);
				}
				else if(stack.getItem().equals(ItemsAndrew.pickerupper)) {
					event.target.mountEntity(event.entity);
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event) {
		if(event.entityLiving.worldObj.isRemote) return;
		if(event.entity instanceof EntityThomas&&event.source.isProjectile()) {
			event.entityLiving.attackEntityFrom(DamageSource.cactus, 1.0F);
		}
		if(event.entity instanceof EntityLivingBase) {
			EntityLivingBase ent = (EntityLivingBase) event.entity;
			int lvl = 0;
			for(int i = 1; i < 5; i++) {
				lvl+=EnchantmentHelper.getEnchantmentLevel(cactusEnchantment.effectId, ent.getEquipmentInSlot(i));
			}
			ent.dropItem(new ItemStack(Blocks.cactus).getItem(), lvl);
		}
		if(event.source instanceof EntityDamageSource) {
			Entity srcE = event.source.getSourceOfDamage();
			if(srcE instanceof EntityLivingBase) {
				ItemStack stack = ((EntityLivingBase) srcE).getHeldItem();
				if(stack!=null) {
					int lvl = EnchantmentHelper.getEnchantmentLevel(cactusEnchantment.effectId, stack);
					event.entity.dropItem(new ItemStack(Blocks.cactus).getItem(), lvl);
				}
			}
		}
		if(event.source!=deathByGlass) {
			for(int i = 1; i < 5; i++) {
				ItemStack stack = event.entityLiving.getEquipmentInSlot(i);
				if(stack!=null&&stack.getItem().getClass().equals(ItemGlassBottleArmor.class)) {
					if(stack.getItem().getMaxDamage()-stack.getItemDamage()<event.ammount+2) {
						event.entityLiving.attackEntityFrom(deathByGlass, 100f);
						event.setCanceled(true);
						stack.stackSize=0;
					}
				}
			}
		}
		if(event.source.equals(DamageSource.drown)) {
			if(event.entityLiving instanceof EntityPlayer) {
				boolean hasGBA=false;
				ItemStack stack = event.entityLiving.getEquipmentInSlot(4);
				if(stack!=null&&stack.getItem() instanceof ItemGlassBottleArmor) {
					hasGBA=true;
				}
				if(hasGBA) {
					EntityPlayer player = (EntityPlayer)event.entityLiving;
					if(player.inventory.hasItem(Items.glass_bottle)) {
						player.inventory.consumeInventoryItem(Items.glass_bottle);
						if(!player.inventory.addItemStackToInventory(new ItemStack(Items.potionitem))) {
							player.dropItem(Items.potionitem, 1);
						}
						event.setCanceled(true);
						player.addStat(AchievementsAndrew.glassBottleArmorFill, 1);
					}
				}
			}
		}
		if(event.source instanceof EntityDamageSource) {
			if(((EntityDamageSource)event.source).getSourceOfDamage().riddenByEntity==event.entity) {
				event.entity.mountEntity(null);
			}
		}
	}
	
	@SubscribeEvent
	public void onEntityDeath(LivingDeathEvent event) {
		if(event.entity.worldObj.isRemote) return;
		if(event.source.damageType.contains("PotatoLiver")&&Math.random()<0.9) {
			event.entityLiving.dropItem(ItemsAndrew.plasticUtensils, 1);
		}
		if(event.entity instanceof EntityThomas) {
			event.entity.worldObj.createExplosion(event.entity, event.entity.posX, event.entity.posY, event.entity.posZ, 5, event.entity.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
			event.entity.dropItem(new ItemStack(BlocksAndrew.invasivePlant).getItem(), 1);
		}
		if(event.entity instanceof EntityPlayer) {
			EntityLivingBase e = Api.getMorphEntity(event.entity.getCommandSenderName(), event.entity.worldObj.isRemote);
			if(e!=null && Ability.hasAbility(e.getClass(), "explodeOnDeath")) {
				event.entity.worldObj.createExplosion(event.entity, event.entity.posX, event.entity.posY, event.entity.posZ, 5, false);
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent event) {
		Block block = event.entity.worldObj.getBlock(event.x, event.y, event.z);
		ItemStack item = event.entityPlayer.getCurrentEquippedItem();
		World w = event.entity.worldObj;
		if((block.equals(Blocks.cactus)||block.equals(BlocksAndrew.dyedCactus))&&item!=null&&item.getItem().equals(Items.dye)) {
			item.stackSize--;
			w.setBlock(event.x, event.y, event.z, BlocksAndrew.dyedCactus, item.getItemDamage(), 3);
			w.playSound(event.x, event.y, event.z, "mob.villager.death", 1f, 1f, true);
			event.entityPlayer.addStat(AchievementsAndrew.dyeCactus, 1);
		}
		else if((block.equals(Blocks.cake)||block instanceof BlockDyedCake)&&item!=null&&item.getItem().equals(Items.dye)) {
			item.stackSize--;
			int meta = w.getBlockMetadata(event.x, event.y, event.z);
			w.setBlock(event.x, event.y, event.z, BlockDyedCake.listDyedCake[item.getItemDamage()]);
			w.setBlockMetadataWithNotify(event.x, event.y, event.z, meta, 3);
			w.playSound(event.x, event.y, event.z, "mob.villager.death", 1f, 1f, true);
		}
		else if(block.equals(Blocks.ice)&&item!=null&&item.getItem().equals(Items.bucket)) {
			event.entityPlayer.inventory.setInventorySlotContents(event.entityPlayer.inventory.currentItem, new ItemStack(ItemsAndrew.iceBucket));
			event.world.setBlockToAir(event.x, event.y, event.z);
		}
		else if(item!=null && item.getItem().equals(ItemsAndrew.itemWallum) && !w.isRemote) {
			boolean failed = false;
			int[] coal = {
					 0, 0,  0,
					 0, 1,  0,
					 0, 2,  0,
					-1, 2,  0,
					 1, 2,  0,
					 0, 2, -1,
					 0, 2,  1
			};
			for(int i = 0; i < coal.length; i+=3) {
				if(!w.getBlock(event.x+coal[i], event.y+coal[i+1], event.z+coal[i+2]).equals(Blocks.coal_block)) {
					failed = true;
					break;
				}
			}
			if(!failed) {
				for(int i = 0; i < coal.length; i+=3) {
					w.setBlockToAir(event.x+coal[i], event.y+coal[i+1], event.z+coal[i+2]);
				}
				EntityItem e = new EntityItem(w, event.x, event.y, event.z, new ItemStack(Items.diamond, 1));
				w.spawnEntityInWorld(e);
				item.damageItem(1, event.entityLiving);
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerCraft(ItemCraftedEvent ev) {
		System.out.println(ev.player.getCommandSenderName()+" crafted something");
		if(ev.crafting.getItem().equals(ItemsAndrew.butterDust)) {
			ev.player.addStat(AchievementsAndrew.butterDust, 1);
			if(Math.random()<0.9) {
				if(!ev.player.inventory.addItemStackToInventory(new ItemStack(ItemsAndrew.plasticUtensils, 1))) {
					ev.player.dropItem(ItemsAndrew.plasticUtensils, 1);
				}
			}
		}
		else if(ev.crafting.getItem().equals(ItemsAndrew.potatoLiver)) {
			ev.player.addStat(AchievementsAndrew.potatoLiver, 1);
		}
		else if(ev.crafting.getItem().equals(ItemsAndrew.itemWallum)) {
			ev.player.addStat(AchievementsAndrew.wallum, 1);
		}
		else if(ev.crafting.getItem().equals(ItemsAndrew.itemWallumCooking)) {
			ev.player.addStat(AchievementsAndrew.wallumCooking, 1);
		}
		else if(ev.crafting.getItem().equals(ItemsAndrew.wallumGrinding)) {
			ev.player.addStat(AchievementsAndrew.wallumGrinding, 1);
		}
		else if(ev.crafting.getItem().equals(Item.getItemFromBlock(BlocksAndrew.tntChest))) {
			ev.player.addStat(AchievementsAndrew.tntChest, 1);
		}
		else if(ev.crafting.getItem().equals(ItemsAndrew.cactusGunPants)) {
			ev.player.addStat(AchievementsAndrew.cactusPants, 1);
		}
		else if(ev.crafting.getItem().equals(ItemsAndrew.cactusGunJetpack)) {
			ev.player.addStat(AchievementsAndrew.cactusJetpack, 1);
		}
		else if(ev.crafting.getItem().equals(ItemsAndrew.curveball)) {
			if(ev.player.experienceLevel>1) {
				ev.player.addExperienceLevel(-1);
			}
			else {
				ev.player.attackEntityFrom(deathByCrafting, 100f);
			}
		}
		else if(ev.crafting.getItem().equals(ItemsAndrew.wallumEating)) {
			IInventory inv = ev.craftMatrix;
			for(int i = 0; i < inv.getSizeInventory(); i++) {
				ItemStack st = inv.getStackInSlot(i);
				if(st != null) {
					if(st.getItem() instanceof ItemSoup) {
						if(!ev.player.inventory.addItemStackToInventory(new ItemStack(Items.bowl))) {
							ev.player.dropItem(Items.bowl, 1);
						}
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onKeyInput(KeyInputEvent ev) {
		if(((ClientProxy)proxy).keyCactusFire.isPressed()) {
			netWrap.sendToServer(new CactusFireMessage());
			ItemsAndrew.cactusGun.onItemRightClick(null, Minecraft.getMinecraft().theWorld, Minecraft.getMinecraft().thePlayer);
		}
		if(((ClientProxy)proxy).keyNetworkBoot.isPressed()) {
			netWrap.sendToServer(new NetworkBootMessage());
		}
	}
	
	@SubscribeEvent
	public void onBucketFill(FillBucketEvent e) {
		if(e.world.getBlock(e.target.blockX, e.target.blockY, e.target.blockZ).equals(BlocksAndrew.blockLiquidGunpowder) && e.world.getBlockMetadata(e.target.blockX, e.target.blockY, e.target.blockZ)==0) {
			e.world.setBlockToAir(e.target.blockX, e.target.blockY, e.target.blockZ);
			e.result = new ItemStack(ItemsAndrew.liquidGunpowderBucket);
			e.setResult(Result.ALLOW);
		}
	}

	@Override
	public int getBurnTime(ItemStack fuel) {
		if(fuel.getItem().equals(ItemsAndrew.liquidGunpowderBucket)) {
			return 5000;
		}
		return 0;
	}
}
