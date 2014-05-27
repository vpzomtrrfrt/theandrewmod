package net.reederhome.colin.theandrewmod.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.renderer.entity.RenderChicken;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.reederhome.colin.theandrewmod.EntityThomas;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

public class RenderCharlie extends RenderChicken {

	ResourceLocation tr;
	public RenderCharlie() {
		super(new ModelChicken(), 0.5f);
		tr = new ResourceLocation(TheAndrewMod.MODID, "textures/models/charlie.png");
	}
	public ResourceLocation getEntityTexture(Entity e) {
		return tr;
	}
}
