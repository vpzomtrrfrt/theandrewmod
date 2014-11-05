package net.reederhome.colin.theandrewmod.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySidedChest extends TileEntity implements ISidedInventory {

	ItemStack[] items = new ItemStack[81];
	
	@Override
	public void closeInventory() {}

	@Override
	public ItemStack decrStackSize(int arg0, int arg1) {
		if(items[arg0]!=null) {
			if(items[arg0].stackSize>arg1) {
				return items[arg0].splitStack(arg1);
			}
			else {
				ItemStack tr = items[arg0];
				items[arg0] = null;
				return tr;
			}
		}
		return null;
	}

	@Override
	public String getInventoryName() {
		return "Sided Chest";
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public int getSizeInventory() {
		return 81;
	}

	@Override
	public ItemStack getStackInSlot(int arg0) {
		return items[arg0];
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int arg0) {
		return getStackInSlot(arg0);
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int arg0, ItemStack arg1) {
		return true;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer arg0) {
		return true;
	}

	@Override
	public void openInventory() {}

	@Override
	public void setInventorySlotContents(int arg0, ItemStack arg1) {
		items[arg0] = arg1;
	}

	@Override
	public boolean canExtractItem(int arg0, ItemStack arg1, int arg2) {
		return true;
	}

	@Override
	public boolean canInsertItem(int arg0, ItemStack arg1, int arg2) {
		return true;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int arg0) {
		int min = 0;
		if(arg0==2||arg0==3) {
			min=27;
		}
		else if(arg0==4||arg0==5) {
			min=54;
		}
		int[] tr = new int[27];
		for(int i = 0; i < 27; i++) {
			tr[i]=min+i;
		}
		return tr;
	}
	
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		NBTTagList l = new NBTTagList();
		for(int i = 0; i < items.length; i++) {
			if(items[i]!=null) {
				NBTTagCompound tag = new NBTTagCompound();
				items[i].writeToNBT(tag);
				tag.setInteger("Slot", i);
				l.appendTag(tag);
			}
		}
		nbt.setTag("Items", l);
	}
	
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagList l = (NBTTagList) nbt.getTag("Items");
		for(int i = 0; i < l.tagCount(); i++) {
			NBTTagCompound tag = l.getCompoundTagAt(i);
			items[tag.getInteger("Slot")] = ItemStack.loadItemStackFromNBT(tag);
		}
	}

}