package net.reederhome.colin.theandrewmod.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.reederhome.colin.theandrewmod.ModelSideSlab;
import net.reederhome.colin.theandrewmod.tileentity.TileEntitySideSlab;

import org.lwjgl.opengl.GL11;

public class RenderSideSlab extends TileEntitySpecialRenderer {

	ModelBase model = new ModelSideSlab();
	
	@Override
	public void renderTileEntityAt(TileEntity var1, double var2, double var4,
			double var6, float var8) {
		if(var1 instanceof TileEntitySideSlab) {
			/*TileEntitySideSlab tess = (TileEntitySideSlab) var1;
			GL11.glPushMatrix();
			GL11.glBegin(GL11.GL_TRIANGLES);
			//bindTexture(new ResourceLocation(TheAndrewMod.MODID+":/textures/blocks/stone_slab_top"));
			GL11.glColor3d(0.5, 0.5, 0.5);
			GL11.glTranslated(var2, var4, var6);
			GL11.glScaled(1, 1, 0.5d);
			int dir = tess.getWorldObj().getBlockMetadata(tess.xCoord, tess.yCoord, tess.zCoord);
			//if(dir==0) {
				model.render(null, (float)var2, (float)var4, (float)var6, (float)var2+1, (float)var4+1, (float)var6+1);
			//}
			GL11.glPopMatrix();*/
		}
	}

}
