package net.reederhome.colin.theandrewmod.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.renderer.entity.RenderChicken;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemDye;
import net.minecraft.util.ResourceLocation;
import net.reederhome.colin.theandrewmod.EntityThomas;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

public class RenderCharlie extends RenderChicken {

	public RenderCharlie() {
		super(new ModelChicken(), 0.5f);
	}
	public ResourceLocation getEntityTexture(Entity e) {
		return new ResourceLocation(TheAndrewMod.MODID, "textures/models/charlie_"+ItemDye.field_150921_b[e.getDataWatcher().getWatchableObjectInt(16)]+".png");
	}
}
