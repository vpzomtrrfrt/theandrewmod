package net.reederhome.colin.theandrewmod;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import net.reederhome.colin.theandrewmod.item.ItemsAndrew;

public class RecipesWallumGrinding implements IRecipe {

	@Override
	public ItemStack getCraftingResult(InventoryCrafting arg0) {
		ItemStack wallum = null;
		int add = 0;
		for(int i = 0; i < arg0.getSizeInventory(); i++) {
			ItemStack s = arg0.getStackInSlot(i);
			if(s==null) continue;
			if(s.getItem().equals(ItemsAndrew.wallumGrinding)) {
				if(wallum!=null) {return null;}
				wallum = s;
			}
			else if(OreDictionary.itemMatches(new ItemStack(Items.flint),s,false)) {
				add+=700;
			}
			else {
				return null;
			}
		}
		if(wallum==null) return null;
		if(add<=0) return null;
		ItemStack tr = wallum.copy();
		if(tr.getItemDamage()-add<0) {
			tr.setItemDamage(0);
		}
		else {
			tr.setItemDamage(tr.getItemDamage()-add);
		}
		return tr;
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
		return getCraftingResult(arg0)!=null;
	}

	
}