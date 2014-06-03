package net.reederhome.colin.theandrewmod;

import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;

@Mod(modid = TheAndrewMod.MODID, version = TheAndrewMod.VERSION, name = "The Andrew Mod")
public class TheAndrewMod {

	public static final String MODID = "theandrewmod";
	public static final String VERSION = "1.5.0";
	static ItemArmor.ArmorMaterial glassBottleArmorMaterial = EnumHelper.addArmorMaterial("glassBottle", 8, new int[]{1, 3, 3, 1}, 16);
	static DamageSource deathBy789 = new DamageSource("theandrewmod.deathBy789");
	static DamageSource deathByPotatoLiver = new DamageSource("theandrewmod.deathByPotatoLiver");
	static DamageSource deathByCancer = new DamageSource("theandrewmod.deathByCancer");
	static DamageSource deathByGlass = new DamageSource("theandrewmod.deathByGlass");
	static DamageSource deathByCharlie = new DamageSource("theandrewmod.deathByCharlie");
	static Enchantment cactusEnchantment;
	static Block decoyBed = new DecoyBed().setBlockTextureName("bed");
	static Block sideSlab = new BlockSideSlab().setBlockTextureName("stone_slab_top");
	static Block jumpPad = new BlockJumpPad();
	static Block dyedCactus = new BlockDyedCactus();
	static Block invasivePlant = new BlockInvasivePlant();
	static Block cactusOre = new BlockCactusOre();
	static Potion cancerPotion;
	static Item potatoLiver;
	static Item plasticUtensils;
	static Item butterDust;
	static Item cactusGun;
	static Item blastingDevice;
	static Item glassBottleHelmet = new ItemGlassBottleArmor(0,0).setUnlocalizedName("glassBottleHelmet");
	static Item glassBottleChestplate = new ItemGlassBottleArmor(0,1).setUnlocalizedName("glassBottleChestplate");
	static Item glassBottleLeggings = new ItemGlassBottleArmor(0,2).setUnlocalizedName("glassBottleLeggings");
	static Item glassBottleBoots = new ItemGlassBottleArmor(0,3).setUnlocalizedName("glassBottleBoots");
	static int teidThomas;
	static int teidJack;
	static BiomeGenBase biomeDessert = new BiomeGenDessert(24);
	
	public static int avid = 24;
	
	@SidedProxy(serverSide="net.reederhome.colin.theandrewmod.CommonProxy", clientSide="net.reederhome.colin.theandrewmod.client.ClientProxy")
	static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		//LanguageRegistry.instance().addStringLocalization("death.attack.theAndrewMod.deathBy789", "7 8 %1$s");
		GameRegistry.registerBlock(decoyBed, "decoyBed");
		GameRegistry.registerBlock(sideSlab, "sideSlab");
		GameRegistry.registerBlock(jumpPad, "jumpPad");
		GameRegistry.registerBlock(dyedCactus, ItemBlockDyedCactus.class, "dyedCactus");
		GameRegistry.registerBlock(invasivePlant, "invasivePlant");
		teidThomas = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityThomas.class, "Thomas", teidThomas, Color.black.getRGB(), Color.cyan.getRGB());
		EntityRegistry.registerModEntity(EntityThomas.class, "Thomas", teidThomas, MODID, 128, 1, true);
		WeightedRandomChestContent wrcct = new WeightedRandomChestContent(new ItemStack(Items.spawn_egg, 1, teidThomas), 0, 10, 4);
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, wrcct);
		ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, wrcct);
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
		GameRegistry.registerTileEntity(TileEntitySideSlab.class, "sideSlab");
		GameRegistry.registerTileEntity(TileEntityJumpPad.class, "jumpPad");
		potatoLiver = new ItemPotatoLiver();
		potatoLiver.setTextureName(MODID+":potatoLiver");
		GameRegistry.registerItem(potatoLiver, "potatoLiver");
		plasticUtensils = new Item();
		plasticUtensils.setUnlocalizedName("plasticUtensils");
		plasticUtensils.setTextureName(MODID+":plasticUtensils");
		plasticUtensils.setCreativeTab(CreativeTabs.tabCombat);
		GameRegistry.registerItem(plasticUtensils, "plasticUtensils");
		butterDust = new ItemButterDust();
		butterDust.setTextureName(MODID+":butterDust");
		GameRegistry.registerItem(butterDust, "butterDust");
		cactusGun=new ItemCactusGun();
		cactusGun.setTextureName(MODID+":cactusGun");
		GameRegistry.registerItem(cactusGun, "cactusGun");
		blastingDevice = new ItemBlastingDevice();
		blastingDevice.setTextureName(MODID+":blastingDevice");
		GameRegistry.registerItem(blastingDevice, "blastingDevice");
		deathByPotatoLiver.setDamageIsAbsolute();
		deathByPotatoLiver.setDamageAllowedInCreativeMode();
		jumpPad.setBlockTextureName(MODID+":jumpPad");
		invasivePlant.setBlockTextureName(MODID+":invasivePlant");
		GameRegistry.registerBlock(cactusOre, "cactusOre");
		cactusOre.setBlockTextureName(MODID+":oreCactus");
		System.out.println("Hello?");
		int cpi = registerPotion();
		System.out.println("cpi="+cpi);
		cancerPotion = new CancerPotion(cpi, false, Color.black.getRGB());
		GameRegistry.addRecipe(new ItemStack(sideSlab), "s", "s", "s", 's', Blocks.stone_slab);
		GameRegistry.addShapelessRecipe(new ItemStack(potatoLiver), Items.bone, Items.potato, Items.wooden_sword);
		GameRegistry.addShapelessRecipe(new ItemStack(butterDust, 4), Items.gold_nugget, Items.milk_bucket, plasticUtensils, invasivePlant);
		GameRegistry.addRecipe(new ItemStack(blastingDevice), "ttt", "ttt", "stt", 't', Blocks.tnt, 's', Blocks.lever);
		GameRegistry.addRecipe(new ItemStack(cactusGun), "cbc", "coc", "c  ", 'c', Blocks.cactus, 'b', Items.bow, 'o', Blocks.obsidian);
		cactusEnchantment = new CactusEnchantment(24);
		GameRegistry.registerItem(glassBottleHelmet, "glassBottleHelmet");
		GameRegistry.registerItem(glassBottleChestplate, "glassBottleChestplate");
		GameRegistry.registerItem(glassBottleLeggings, "glassBottleLeggings");
		GameRegistry.registerItem(glassBottleBoots, "glassBottleBoots");
		glassBottleHelmet.setTextureName(MODID+":glassBottleHelmet");
		glassBottleChestplate.setTextureName(MODID+":glassBottleChestplate");
		glassBottleLeggings.setTextureName(MODID+":glassBottleLeggings");
		glassBottleBoots.setTextureName(MODID+":glassBottleBoots");
		GameRegistry.addRecipe(new ItemStack(glassBottleHelmet), "bbb", "b b", "   ", 'b', Items.glass_bottle);
		GameRegistry.addRecipe(new ItemStack(glassBottleChestplate), "b b", "bbb", "bbb", 'b', Items.glass_bottle);
		GameRegistry.addRecipe(new ItemStack(glassBottleLeggings), "bbb", "b b", "b b", 'b', Items.glass_bottle);
		GameRegistry.addRecipe(new ItemStack(glassBottleBoots), "   ", "b b", "b b", 'b', Items.glass_bottle);
		VillagerRegistry.instance().registerVillagerId(avid);
		VillagerRegistry.instance().registerVillageTradeHandler(avid, new TradeHandlerAndrew());
		BiomeDictionary.registerBiomeType(biomeDessert, BiomeDictionary.Type.WASTELAND);
		BiomeManager.addSpawnBiome(biomeDessert);
		GameRegistry.registerWorldGenerator(new WorldGeneratorAndrew(), 8);
		for(int d = 0; d < 16; d++) {
			GameRegistry.addSmelting(new ItemStack(dyedCactus, 1, d), new ItemStack(Items.dye, 1, d), 17);
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
	
	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent e) {
		//System.out.println("livingUpdate");
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
	}
	
	@SubscribeEvent
	public void onItemPickup(EntityItemPickupEvent event) {
		if(event.item.getEntityItem().getItem().equals(potatoLiver)&&!(event.entityPlayer!=null&&event.item.func_145798_i()==event.entityPlayer.getCommandSenderName())) {
			System.out.println("potatoLiver pickup");
			event.entityLiving.attackEntityFrom(TheAndrewMod.deathByPotatoLiver, 100f);
			event.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public void onEntityInteract(EntityInteractEvent event) {
		ItemStack stack = event.entityPlayer.getCurrentEquippedItem();
		if(!event.target.worldObj.isRemote) {
			if(stack!=null) {
				if(stack.getItem().equals(plasticUtensils)) {
					stack.stackSize--;
					event.target.attackEntityFrom(new EntityDamageSource("theandrewmod.deathByPotatoLiver.player", event.entityPlayer), 100f);
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
					System.out.println("good job - "+stack.getItemDamage());
					if(stack.getItem().getMaxDamage()-stack.getItemDamage()<event.ammount+2) {
						event.entityLiving.attackEntityFrom(deathByGlass, 100f);
						event.setCanceled(true);
					}
				}
				else if(stack!=null){
					System.out.println(stack.getItem().getClass());
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onEntityDeath(LivingDeathEvent event) {
		if(event.entity.worldObj.isRemote) return;
		if(event.source.damageType.contains("PotatoLiver")&&Math.random()<0.9) {
			event.entityLiving.dropItem(plasticUtensils, 1);
		}
		if(event.entity instanceof EntityThomas) {
			event.entity.worldObj.createExplosion(event.entity, event.entity.posX, event.entity.posY, event.entity.posZ, 5, event.entity.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
			event.entity.dropItem(new ItemStack(invasivePlant).getItem(), 1);
		}
	}
	
	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent event) {
		Block block = event.entity.worldObj.getBlock(event.x, event.y, event.z);
		ItemStack item = event.entityPlayer.getCurrentEquippedItem();
		if((block.equals(Blocks.cactus)||block.equals(dyedCactus))&&item!=null&&item.getItem().equals(Items.dye)) {
			item.stackSize--;
			event.entity.worldObj.setBlock(event.x, event.y, event.z, dyedCactus, item.getItemDamage(), 3);
			event.entity.worldObj.playSound(event.x, event.y, event.z, "mob.villager.death", 1f, 1f, true);
		}
	}
	
	@SubscribeEvent
	public void onPlayerCraft(ItemCraftedEvent ev) {
		System.out.println(ev.player.getCommandSenderName()+" crafted something");
		if(ev.crafting.getItem().equals(butterDust)) {
			//System.out.println("butter dust crafted");
			if(Math.random()<0.9) {
				if(!ev.player.inventory.addItemStackToInventory(new ItemStack(plasticUtensils, 1))) {
					ev.player.dropItem(plasticUtensils, 1);
				}
			}
		}
	}
}
