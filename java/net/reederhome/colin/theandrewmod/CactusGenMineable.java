package net.reederhome.colin.theandrewmod;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.reederhome.colin.theandrewmod.block.BlocksAndrew;

public class CactusGenMineable extends WorldGenMinable {

	public CactusGenMineable() {
		super(BlocksAndrew.cactusOre, 16);
	}

}
