package net.reederhome.colin.theandrewmod.client;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ResourceLocation;
import net.reederhome.colin.theandrewmod.CommonProxy;
import net.reederhome.colin.theandrewmod.TheAndrewMod;
import net.reederhome.colin.theandrewmod.entity.EntityCharlie;
import net.reederhome.colin.theandrewmod.entity.EntityHPCreeper;
import net.reederhome.colin.theandrewmod.entity.EntityJack;
import net.reederhome.colin.theandrewmod.entity.EntityKevin;
import net.reederhome.colin.theandrewmod.entity.EntityLuckEgg;
import net.reederhome.colin.theandrewmod.entity.EntityThomas;
import net.reederhome.colin.theandrewmod.entity.EntityThrownCactus;
import net.reederhome.colin.theandrewmod.entity.EntityTrevor;
import net.reederhome.colin.theandrewmod.entity.EntityZombieCow;
import net.reederhome.colin.theandrewmod.item.ItemsAndrew;
import net.reederhome.colin.theandrewmod.tileentity.TileEntitySideSlab;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;

public class ClientProxy extends CommonProxy {

	public static KeyBinding keyCactusFire = new KeyBinding("key.fireCactus", Keyboard.KEY_C, "key.category.andrew");
	public static KeyBinding keyNetworkBoot = new KeyBinding("key.networkBoot", Keyboard.KEY_N, "key.category.andrew");
	public void registerRenderers() {
		RenderBiped renderThomas = new RenderThomas(new ModelThomas(), 0.5f);
		RenderBiped renderJack = new RenderJack(new ModelBiped(), 0.5f);
		RenderingRegistry.registerEntityRenderingHandler(EntityThomas.class, renderThomas);
		RenderingRegistry.registerEntityRenderingHandler(EntityJack.class, renderJack);
		RenderingRegistry.registerEntityRenderingHandler(EntityTrevor.class, new RenderTrevor(new ModelBiped(), 0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityKevin.class, new RenderKevin(0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityThrownCactus.class, new RenderThrownCactus());
		RenderingRegistry.registerEntityRenderingHandler(EntityCharlie.class, new RenderCharlie());
		RenderingRegistry.registerEntityRenderingHandler(EntityHPCreeper.class, new RenderHPCreeper());
		RenderingRegistry.registerEntityRenderingHandler(EntityZombieCow.class, new RenderZombieCow());
		RenderingRegistry.registerEntityRenderingHandler(EntityLuckEgg.class, new RenderSnowball(ItemsAndrew.luckEgg));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySideSlab.class, new RenderSideSlab());
		VillagerRegistry.instance().registerVillagerSkin(TheAndrewMod.avid, new ResourceLocation(TheAndrewMod.MODID+":/textures/models/andrew.png"));
		ClientRegistry.registerKeyBinding(keyCactusFire);
		ClientRegistry.registerKeyBinding(keyNetworkBoot);
	}
}
