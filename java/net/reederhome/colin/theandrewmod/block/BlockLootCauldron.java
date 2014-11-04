package net.reederhome.colin.theandrewmod.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.world.World;
import net.minecraftforge.common.FishingHooks;

public class BlockLootCauldron extends BlockCauldron {

	public void breakBlock(World world, int x, int y, int z, Block b, int thingy) {
		super.breakBlock(world, x, y, z, b, thingy);
		EntityItem it = new EntityItem(world);
		it.setEntityItemStack(FishingHooks.getRandomFishable(new Random(), (float)(Math.random()*0.2)));
		it.setLocationAndAngles(x+0.5, y+0.5, z+0.5, 0, 0);
		world.spawnEntityInWorld(it);
	}
}
