package net.reederhome.colin.theandrewmod;

import java.awt.Color;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSideSlab extends ModelBase {

	ModelRenderer box;
	public ModelSideSlab() {
		box = new ModelRenderer(this, 0, 0);
		box.addBox(0, 0, 0, 1, 1, 1, Color.gray.getRGB());
	}
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		box.render(f5);
	}
}
