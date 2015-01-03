package net.reederhome.colin.theandrewmod.client;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

public class RenderKevin extends RenderBiped {

	private ResourceLocation tr;
	public RenderKevin(float par2) {
		super(new ModelKevin(), par2);
		tr = new ResourceLocation(TheAndrewMod.MODID, "textures/models/kevin.png");
	}
	public ResourceLocation getEntityTexture(Entity e) {
		return tr;
	}
}