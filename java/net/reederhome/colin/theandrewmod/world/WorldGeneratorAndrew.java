package net.reederhome.colin.theandrewmod.world;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.util.ForgeDirection;
import net.reederhome.colin.theandrewmod.TheAndrewMod;
import net.reederhome.colin.theandrewmod.block.BlocksAndrew;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGeneratorAndrew implements IWorldGenerator {

	@Override
	public void generate(Random r, int arg1, int arg2, World arg3,
			IChunkProvider arg4, IChunkProvider arg5) {
		if(arg3.provider.dimensionId==0) {
			int i = arg1*16;
			int j = arg2*16;
			
			//Ore
			for(int k = 0; k < 16; k+=2) {
				int fbx = i+r.nextInt(16);
				int fby = r.nextInt(64);
				int fbz = j+r.nextInt(16);
				(new CactusGenMineable()).generate(arg3, r, fbx, fby, fbz);
			}
			
			//Random Underground Cauldrons!
			for(int k = 0; k < 16; k++) {
				if(Math.random()<0.0001) {
					int x = i+k;
					int y;
					int z = j+r.nextInt(16);
					for(y=1;y<256;y++) {
						if(arg3.isAirBlock(x, y, z)&&arg3.isSideSolid(x, y-1, z, ForgeDirection.UP)) {
							if(!arg3.canBlockSeeTheSky(x, y, z)) {
								if(TheAndrewMod.logRUC) {
									System.out.println("RUC at "+x+" "+y+" "+z);
								}
								arg3.setBlock(x, y, z, Math.random()<0.2?BlocksAndrew.lootCauldron:Blocks.cauldron);
								arg3.setBlockMetadataWithNotify(x, y, z, 1, 3);
							}
							break;
						}
					}
				}
			}
		}
	}

	
}
