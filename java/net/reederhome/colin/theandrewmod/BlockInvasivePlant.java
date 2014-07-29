package net.reederhome.colin.theandrewmod;

import java.util.Random;

import net.minecraft.block.BlockBush;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockInvasivePlant extends BlockBush {

	public boolean canBlockStay(World w, int x, int y, int z) {
		return !w.isAirBlock(x, y-1, z);
	}
	public BlockInvasivePlant() {
		super();
		setBlockName("invasivePlant");
		setCreativeTab(TheAndrewMod.tabAndrew);
		setBlockTextureName(TheAndrewMod.MODID+":invasivePlant");
	}
	public void updateTick(World world, int x, int y, int z, Random r) {
		int xm = x+r.nextInt(10)-5;
		int ym = 255;
		int zm = z+r.nextInt(10)-5;
		EntityFallingBlock sand = new EntityFallingBlock(world,xm,ym,zm,this);
		sand.field_145812_b=1;
		sand.field_145813_c=false;
		world.spawnEntityInWorld(sand);
	}
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase ent, ItemStack stack) {
		super.onBlockPlacedBy(world, x, y, z, ent, stack);
		if(ent instanceof EntityPlayer) {
			((EntityPlayer) ent).addStat(AchievementsAndrew.invasivePlant, 1);
		}
	}
}
