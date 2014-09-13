package net.reederhome.colin.theandrewmod.block;

import java.util.Random;

import net.minecraft.block.BlockOre;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

public class BlockCactusOre extends BlockOre {

	public BlockCactusOre() {
		super();
		setBlockName("cactusOre");
		setCreativeTab(TheAndrewMod.tabAndrew);
		setBlockTextureName(TheAndrewMod.MODID+":oreCactus");
	}
	public Item getItemDropped(int par1, Random r, int par2) {
		return new ItemStack(Blocks.cactus).getItem();
	}
}
