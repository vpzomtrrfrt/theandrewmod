package net.reederhome.colin.theandrewmod;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.item.ItemsAndrew;

public class RecipesButterShell implements IRecipe {

	@Override
	public ItemStack getCraftingResult(InventoryCrafting arg0) {
		NBTTagList l = new NBTTagList();
		boolean butterShell = false;
		boolean allButterShell = true;
		for(int i = 0; i < arg0.getSizeInventory(); i++) {
			ItemStack stack = arg0.getStackInSlot(i);
			if(stack==null) continue;
			if((stack.getItem()==ItemsAndrew.butterShellEmpty&&butterShell)||stack.getItem()!=ItemsAndrew.butterShellEmpty) {
				NBTTagCompound tag = new NBTTagCompound();
				ItemStack ta = stack.copy();
				ta.stackSize=1;
				ta.writeToNBT(tag);
				l.appendTag(tag);
			}
			if(stack.getItem()==ItemsAndrew.butterShellEmpty) {
				butterShell=true;
			}
			else {
				allButterShell=false;
			}
		}
		if(!allButterShell&&butterShell&&l.tagCount()>0) {
			ItemStack tr = new ItemStack(ItemsAndrew.butterShellFull);
			NBTTagCompound tag = new NBTTagCompound();
			tag.setTag("Items", l);
			tr.setTagCompound(tag);
			System.out.println(tr);
			return tr;
		}
		else {
			return null;
		}
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