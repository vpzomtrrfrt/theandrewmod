package net.reederhome.colin.theandrewmod.client;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPotion;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.reederhome.colin.theandrewmod.EntityThrownCactus;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderThrownCactus extends Render {

	@Override
	public void doRender(Entity var1, double var2, double var4, double var6,
			float var8, float var9) {
		IIcon var10 = Blocks.cactus.getBlockTextureFromSide(1);
		if(var1 instanceof EntityThrownCactus) {
			int clr = ((EntityThrownCactus)var1).getClr();
			System.out.println("Client Colorful!  "+clr);
			if(clr!=-1) {
				var10 = TheAndrewMod.dyedCactus.getIcon(1, clr);
			}
		}

        if (var10 != null)
        {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)var2, (float)var4, (float)var6);
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glScalef(0.5F, 0.5F, 0.5F);
            this.bindEntityTexture(var1);
            Tessellator var11 = Tessellator.instance;

            if (var10 == ItemPotion.func_94589_d("bottle_splash"))
            {
                int var12 = PotionHelper.func_77915_a(((EntityPotion)var1).getPotionDamage(), false);
                float var13 = (float)(var12 >> 16 & 255) / 255.0F;
                float var14 = (float)(var12 >> 8 & 255) / 255.0F;
                float var15 = (float)(var12 & 255) / 255.0F;
                GL11.glColor3f(var13, var14, var15);
                GL11.glPushMatrix();
                this.func_77026_a(var11, ItemPotion.func_94589_d("overlay"));
                GL11.glPopMatrix();
                GL11.glColor3f(1.0F, 1.0F, 1.0F);
            }

            this.func_77026_a(var11, var10);
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glPopMatrix();
        }
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return TextureMap.locationBlocksTexture;
	}

	private void func_77026_a(Tessellator par1Tessellator, IIcon par2Icon)
    {
        float var3 = par2Icon.getMinU();
        float var4 = par2Icon.getMaxU();
        float var5 = par2Icon.getMinV();
        float var6 = par2Icon.getMaxV();
        float var7 = 1.0F;
        float var8 = 0.5F;
        float var9 = 0.25F;
        GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        par1Tessellator.startDrawingQuads();
        par1Tessellator.setNormal(0.0F, 1.0F, 0.0F);
        par1Tessellator.addVertexWithUV((double)(0.0F - var8), (double)(0.0F - var9), 0.0D, (double)var3, (double)var6);
        par1Tessellator.addVertexWithUV((double)(var7 - var8), (double)(0.0F - var9), 0.0D, (double)var4, (double)var6);
        par1Tessellator.addVertexWithUV((double)(var7 - var8), (double)(var7 - var9), 0.0D, (double)var4, (double)var5);
        par1Tessellator.addVertexWithUV((double)(0.0F - var8), (double)(var7 - var9), 0.0D, (double)var3, (double)var5);
        par1Tessellator.draw();
    }
}
