package net.reederhome.colin.theandrewmod.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.reederhome.colin.theandrewmod.block.BlocksAndrew;

public class ItemBlockDyedCactus extends ItemBlockWithMetadata {

	public ItemBlockDyedCactus(Block p_i45328_1_) {
		super(p_i45328_1_, BlocksAndrew.dyedCactus);
		setHasSubtypes(true);
		this.setMaxDamage(0);
	}
	public int getMetadata(int meta) {
		return meta;
	}
	public String getUnlocalizedName(ItemStack stack) {
		return "tile.dyedCactus."+ItemDye.field_150923_a[stack.getItemDamage()];
	}
	public IIcon getIconFromDamage(int meta) {
		return BlocksAndrew.dyedCactus.getIcon(1, meta);
	}

}
