package net.reederhome.colin.theandrewmod.client;

import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

public class RenderHPCreeper extends RenderCreeper {

	private ResourceLocation tr;
	public RenderHPCreeper() {
		super();
		tr = new ResourceLocation(TheAndrewMod.MODID, "textures/models/hpcreeper.png");
	}
	public ResourceLocation getEntityTexture(Entity e) {
		return tr;
	}
}