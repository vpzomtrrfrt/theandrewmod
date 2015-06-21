package net.reederhome.colin.theandrewmod.world;

import java.util.Random;

import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

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
	
	public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);

        if (par2Random.nextInt(30) == 0)
        {
            int var5 = par3 + par2Random.nextInt(16) + 8;
            int var6 = par4 + par2Random.nextInt(16) + 8;
            WorldGenDessertWells var7 = new WorldGenDessertWells();
            var7.generate(par1World, par2Random, var5, par1World.getHeightValue(var5, var6) + 1, var6);
            if(TheAndrewMod.logWells) {
            	System.out.println("WELL at "+var5+","+var6);
            }
        }
    }

}
