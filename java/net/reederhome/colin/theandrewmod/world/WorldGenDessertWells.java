package net.reederhome.colin.theandrewmod.world;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.reederhome.colin.theandrewmod.block.BlocksAndrew;

public class WorldGenDessertWells extends WorldGenerator {

	@Override
	public boolean generate(World w, Random r, int x, int y,
			int z) {
		while(w.isAirBlock(x, y, z) && y > 2) {
			y--;
		}
		int var6, var7;
		for(var6 = -2; var6 <= 2; var6++) {
			for(var7 = -2; var7 <= 2; var7++) {
				if(w.isAirBlock(x+var6, y-1, z+var7)) {
					return false;
				}
			}
		}
		for(var6 = -1; var6 <= 0; var6++) {
			for(var7 = -2; var7 <= 2; var7++) {
				for(int var8 = -2; var8 <= 2; var8++) {
					w.setBlock(x+var7, y+var6, z+var8, BlocksAndrew.cakeBlock, 0, 2);
				}
			}
		}
		for(var6 = -2; var6 <= 2; var6++) {
			for(var7 = -2; var7 <= 2; var7++) {
				if(var6 == -2 || var6 == 2 || var7 == -2 || var7 == 2) {
					w.setBlock(x+var6, y+1, z+var7, BlocksAndrew.cakeBlock, 0, 2);
				}
			}
		}
		w.setBlock(x+2, y+1, z, Blocks.cake, 0, 2);
		w.setBlock(x-2, y+1, z, Blocks.cake, 0, 2);
		w.setBlock(x, y+1, z+2, Blocks.cake, 0, 2);
		w.setBlock(x, y+1, z-2, Blocks.cake, 0, 2);
		for(var6 = -1; var6 <= 1; var6++) {
			for(var7 = -1; var7 <= 1; var7++) {
				w.setBlock(x+var6, y+4, z+var7, (var6 == 0 && var7 == 0)?BlocksAndrew.cakeBlock:Blocks.cake, 0, 2);
			}
		}
		for(var6 = 1; var6 <= 3; var6++) {
			w.setBlock(x-1, y+var6, z-1, BlocksAndrew.cakeBlock, 0, 2);
			w.setBlock(x-1, y+var6, z+1, BlocksAndrew.cakeBlock, 0, 2);
			w.setBlock(x+1, y+var6, z-1, BlocksAndrew.cakeBlock, 0, 2);
			w.setBlock(x+1, y+var6, z+1, BlocksAndrew.cakeBlock, 0, 2);
		}
		return true;
	}

	
}