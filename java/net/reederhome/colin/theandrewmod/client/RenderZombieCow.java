package net.reederhome.colin.theandrewmod.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.entity.RenderCow;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

public class RenderZombieCow extends RenderCow {

	private ResourceLocation tr;
	public RenderZombieCow(ModelBase p_i1253_1_, float p_i1253_2_) {
		super(p_i1253_1_, p_i1253_2_);
		tr = new ResourceLocation(TheAndrewMod.MODID, "textures/models/zombieCow.png");
	}
	
	public RenderZombieCow() {
		this(new ModelCow(), 0.5f);
	}
	
	public ResourceLocation getEntityTexture(Entity e) {
		return tr;
	}
}
