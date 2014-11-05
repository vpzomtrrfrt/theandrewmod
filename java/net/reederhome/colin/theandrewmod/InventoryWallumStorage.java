package net.reederhome.colin.theandrewmod;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryWallumStorage implements IInventory {

	ItemStack stack;
	
	public InventoryWallumStorage(ItemStack w) {
		stack=w;
	}
	
	public ItemStack[] getItems() {
		ItemStack[] tr = new ItemStack[9];
		if(stack.stackTagCompound!=null) {
			System.out.println(stack.stackTagCompound);
			NBTTagList l = (NBTTagList) stack.stackTagCompound.getTag("Items");
			if(l!=null) {
				for(int i = 0; i < l.tagCount(); i++) {
					NBTTagCompound tag = l.getCompoundTagAt(i);
					tr[tag.getInteger("Slot")]=ItemStack.loadItemStackFromNBT(tag);
				}
			}
		}
		return tr;
	}
	
	public void saveItems(ItemStack[] ts) {
		NBTTagList l = new NBTTagList();
		for(int i = 0; i < ts.length; i++) {
			if(ts[i]!=null) {
				NBTTagCompound tag = new NBTTagCompound();
				ts[i].writeToNBT(tag);
				tag.setInteger("Slot", i);
				l.appendTag(tag);
			}
		}
		NBTTagCompound nbt;
		if(stack.hasTagCompound()) {
			nbt=stack.stackTagCompound;
		}
		else {
			nbt=new NBTTagCompound();
		}
		nbt.setTag("Items", l);
		stack.setTagCompound(nbt);
	}
	
	@Override
	public void closeInventory() {}

	@Override
	public ItemStack decrStackSize(int arg0, int arg1) {
		ItemStack tr = null;
		ItemStack[] items = getItems();
		if(items[arg0]!=null) {
			if(items[arg0].stackSize>arg1) {
				tr = items[arg0].splitStack(arg1);
			}
			else {
				tr = items[arg0];
				items[arg0] = null;
			}
		}
		saveItems(items);
		return tr;
	}

	@Override
	public String getInventoryName() {
		return I18n.format(stack.getUnlocalizedName()+".name");
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public int getSizeInventory() {
		return 9;
	}

	@Override
	public ItemStack getStackInSlot(int arg0) {
		return getItems()[arg0];
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
		return false;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer arg0) {
		return true;
	}

	@Override
	public void markDirty() {}

	@Override
	public void openInventory() {}

	@Override
	public void setInventorySlotContents(int arg0, ItemStack arg1) {
		ItemStack[] items = getItems();
		items[arg0] = arg1;
		saveItems(items);
	}

}
