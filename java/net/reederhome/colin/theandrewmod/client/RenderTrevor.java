package net.reederhome.colin.theandrewmod.client;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

public class RenderTrevor extends RenderBiped {

	private ResourceLocation tr;
	public RenderTrevor(ModelBiped par1ModelBiped, float par2) {
		super(par1ModelBiped, par2);
		tr = new ResourceLocation(TheAndrewMod.MODID, "textures/models/trevor.png");
	}
	public ResourceLocation getEntityTexture(Entity e) {
		return tr;
	}
}
