package net.reederhome.colin.theandrewmod.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemDye;
import net.reederhome.colin.theandrewmod.item.ItemBlockDyedCactus;

public class BlocksAndrew {

	public static Block decoyBed = new BlockDecoyBed().setBlockTextureName("bed");
	public static Block sideSlab = new BlockSideSlab().setBlockTextureName("stone_slab_top");
	public static Block jumpPad = new BlockJumpPad();
	public static Block dyedCactus = new BlockDyedCactus();
	public static Block invasivePlant = new BlockInvasivePlant();
	public static Block cactusOre = new BlockCactusOre();
	public static Block redstoneCake = new BlockRedstoneCake();
	public static Block blockCactusGun = new BlockCactusGun();
	public static Block rainbowMachine = new BlockRainbowMachine();
	public static Block tntChest = new BlockTNTChest();
	
	public static void registerBlocks() {
		GameRegistry.registerBlock(decoyBed, "decoyBed");
		GameRegistry.registerBlock(sideSlab, "sideSlab");
		GameRegistry.registerBlock(jumpPad, "jumpPad");
		GameRegistry.registerBlock(dyedCactus, ItemBlockDyedCactus.class, "dyedCactus");
		GameRegistry.registerBlock(invasivePlant, "invasivePlant");
		GameRegistry.registerBlock(cactusOre, "cactusOre");
		GameRegistry.registerBlock(redstoneCake, "redstoneCake");
		GameRegistry.registerBlock(blockCactusGun, "blockCactusGun");
		GameRegistry.registerBlock(rainbowMachine, "rainbowMachine");
		for(int i = 0; i < 16; i++) {
			GameRegistry.registerBlock(new BlockDyedCake(i), "dyed_cake_"+ItemDye.field_150921_b[i]);
		}
		GameRegistry.registerBlock(tntChest, "tntChest");
	}
}
