package net.reederhome.colin.theandrewmod;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.item.ItemsAndrew;

public class RecipesWallumEating implements IRecipe {

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		int ta = 0;
		float numWallums = 0;
		ItemStack tw = null;
		for(int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack slot = inv.getStackInSlot(i);
			if(slot!=null) {
				if(slot.getItem().equals(Items.iron_ingot)) {
					numWallums+=0.11;
				}
				else if(slot.getItem().equals(ItemsAndrew.itemWallum)) {
					numWallums+=0.89;
					tw=slot;
				}
				else if(slot.getItem().equals(ItemsAndrew.wallumEating)) {
					numWallums++;
					tw=slot;
				}
				else if(slot.getItem() instanceof ItemFood) {
					ItemFood f = (ItemFood) slot.getItem();
					ta+=f.func_150905_g(slot);
				}
			}
		}
		if(numWallums==1&&ta>0) {
			ItemStack tr;
			if(tw.getItem().equals(ItemsAndrew.wallumEating)) {
				tr = tw.copy();
			}
			else {
				tr = new ItemStack(ItemsAndrew.wallumEating, 1, ItemsAndrew.wallumEating.getMaxDamage());
			}
			tr.setItemDamage(Math.max(tr.getItemDamage()-ta,0));
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
		return getCraftingResult(arg0)!=null;
	}

}