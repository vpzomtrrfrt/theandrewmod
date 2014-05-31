package net.reederhome.colin.theandrewmod.client;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.reederhome.colin.theandrewmod.EntityThomas;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

public class RenderThomas extends RenderBiped {

	private ResourceLocation tr;
	private ResourceLocation enderthomas;
	public RenderThomas(ModelBiped par1ModelBiped, float par2) {
		super(par1ModelBiped, par2);
		tr = new ResourceLocation(TheAndrewMod.MODID, "textures/models/thomas.png");
		enderthomas = new ResourceLocation(TheAndrewMod.MODID, "textures/models/enderthomas.png");
		/*System.out.println(tr);
		System.err.println("THOMAS IS HERE");*/
	}
	public ResourceLocation getEntityTexture(Entity e) {
		if(e instanceof EntityThomas) {
			if(((EntityThomas) e).isEnderThomas()) {
				return enderthomas;
			}
		}
		return tr;
	}
}
