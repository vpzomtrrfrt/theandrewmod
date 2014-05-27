package net.reederhome.colin.theandrewmod.client;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.reederhome.colin.theandrewmod.CommonProxy;
import net.reederhome.colin.theandrewmod.EntityCharlie;
import net.reederhome.colin.theandrewmod.EntityJack;
import net.reederhome.colin.theandrewmod.EntityThomas;
import net.reederhome.colin.theandrewmod.EntityThrownCactus;
import net.reederhome.colin.theandrewmod.EntityTrevor;
import net.reederhome.colin.theandrewmod.RenderJack;
import net.reederhome.colin.theandrewmod.RenderSideSlab;
import net.reederhome.colin.theandrewmod.RenderThomas;
import net.reederhome.colin.theandrewmod.RenderTrevor;
import net.reederhome.colin.theandrewmod.TileEntitySideSlab;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	public void registerRenderers() {
		RenderBiped renderThomas = new RenderThomas(new ModelThomas(), 0.5f);
		RenderBiped renderJack = new RenderJack(new ModelBiped(), 0.5f);
		RenderingRegistry.registerEntityRenderingHandler(EntityThomas.class, renderThomas);
		RenderingRegistry.registerEntityRenderingHandler(EntityJack.class, renderJack);
		RenderingRegistry.registerEntityRenderingHandler(EntityTrevor.class, new RenderTrevor(new ModelBiped(), 0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityThrownCactus.class, new RenderThrownCactus());
		RenderingRegistry.registerEntityRenderingHandler(EntityCharlie.class, new RenderCharlie());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySideSlab.class, new RenderSideSlab());
	}
}
