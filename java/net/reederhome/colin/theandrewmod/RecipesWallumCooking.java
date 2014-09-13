package net.reederhome.colin.theandrewmod;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.item.ItemsAndrew;

public class RecipesWallumCooking implements IRecipe {

	@Override
	public ItemStack getCraftingResult(InventoryCrafting arg0) {
		float numWallums = 0;
		int fuel = 0;
		String name = null;
		for(int x = 0; x < arg0.getSizeInventory(); x++) {
			ItemStack sis = arg0.getStackInSlot(x);
			if(sis!=null) {
				if(sis.getItem().equals(ItemsAndrew.itemWallumCooking)) {
					numWallums++;
					fuel+=sis.getMaxDamage()-sis.getItemDamage();
					if(sis.hasDisplayName()) name = sis.getDisplayName();
				}
				else if(sis.getItem().equals(ItemsAndrew.itemWallum)) {
					numWallums+=0.8;
					if(sis.hasDisplayName()) name = sis.getDisplayName();
				}
				else if(sis.getItem().equals(Items.stone_sword)) {
					numWallums+=0.09;
				}
				else if(sis.getItem().equals(Items.iron_ingot)) {
					numWallums+=0.11;
				}
				else {
					if(TileEntityFurnace.isItemFuel(sis)) {
						fuel+=TileEntityFurnace.getItemBurnTime(sis);
					}
					else {
						return null;
					}
				}
			}
		}
		if(numWallums==1) {
			ItemStack tr = new ItemStack(ItemsAndrew.itemWallumCooking, 1, ItemsAndrew.itemWallumCooking.getMaxDamage()-fuel);
			if(name!=null) tr.setStackDisplayName(name);
			return tr;
		}
		return null;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return null;
	}

	@Override
	public int getRecipeSize() {
		return 2;
	}

	@Override
	public boolean matches(InventoryCrafting arg0, World arg1) {
		return (getCraftingResult(arg0)!=null);
	}

}
