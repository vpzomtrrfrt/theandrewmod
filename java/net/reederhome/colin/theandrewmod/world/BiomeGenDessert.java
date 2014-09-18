package net.reederhome.colin.theandrewmod.world;

import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenDessert extends BiomeGenBase {

	public BiomeGenDessert(int par1) {
		super(par1);
		this.spawnableCreatureList.clear();
		this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityMooshroom.class, 2, 4, 8));
		this.topBlock=Blocks.cake;
		this.fillerBlock=Blocks.sand;
		this.theBiomeDecorator.treesPerChunk=-999;
		this.theBiomeDecorator.deadBushPerChunk=0;
		this.theBiomeDecorator.reedsPerChunk=1;
		this.theBiomeDecorator.cactiPerChunk=15;
		setBiomeName("Dessert");
	}
}
