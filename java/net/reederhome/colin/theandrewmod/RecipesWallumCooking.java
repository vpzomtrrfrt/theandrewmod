package net.reederhome.colin.theandrewmod;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;

public class RecipesWallumCooking implements IRecipe {

	@Override
	public ItemStack getCraftingResult(InventoryCrafting arg0) {
		float numWallums = 0;
		int fuel = 0;
		for(int x = 0; x < arg0.getSizeInventory(); x++) {
			ItemStack sis = arg0.getStackInSlot(x);
			if(sis!=null) {
				if(sis.getItem().equals(TheAndrewMod.itemWallumCooking)) {
					numWallums++;
					fuel+=sis.getMaxDamage()-sis.getItemDamage();
				}
				else if(sis.getItem().equals(TheAndrewMod.itemWallum)) {
					numWallums+=0.9;
				}
				else if(sis.getItem().equals(Items.stone_sword)) {
					numWallums+=0.1;
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
			return new ItemStack(TheAndrewMod.itemWallumCooking, 1, TheAndrewMod.itemWallumCooking.getMaxDamage()-fuel);
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
