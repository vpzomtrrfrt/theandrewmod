package net.reederhome.colin.theandrewmod;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGeneratorAndrew implements IWorldGenerator {

	@Override
	public void generate(Random r, int arg1, int arg2, World arg3,
			IChunkProvider arg4, IChunkProvider arg5) {
		int i = arg1*16;
		int j = arg2*16;
		for(int k = 0; k < 16; k+=2) {
			int fbx = i+r.nextInt(16);
			int fby = r.nextInt(64);
			int fbz = j+r.nextInt(16);
			(new CactusGenMineable()).generate(arg3, r, fbx, fby, fbz);
		}
	}

	
}
