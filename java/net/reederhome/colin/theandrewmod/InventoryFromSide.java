package net.reederhome.colin.theandrewmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;

public class InventoryFromSide implements IInventory {

	ISidedInventory inv;
	int side;
	
	public InventoryFromSide(ISidedInventory ti, int s) {
		inv = ti;
		side = s;
	}
	
	@Override
	public void closeInventory() {
		inv.closeInventory();
	}

	@Override
	public ItemStack decrStackSize(int arg0, int arg1) {
		return inv.decrStackSize(inv.getAccessibleSlotsFromSide(side)[arg0], arg1);
	}

	@Override
	public String getInventoryName() {
		return inv.getInventoryName();
	}

	@Override
	public int getInventoryStackLimit() {
		return inv.getInventoryStackLimit();
	}

	@Override
	public int getSizeInventory() {
		return inv.getAccessibleSlotsFromSide(side).length;
	}

	@Override
	public ItemStack getStackInSlot(int arg0) {
		return inv.getStackInSlot(inv.getAccessibleSlotsFromSide(side)[arg0]);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int arg0) {
		return inv.getStackInSlotOnClosing(inv.getAccessibleSlotsFromSide(side)[arg0]);
	}

	@Override
	public boolean hasCustomInventoryName() {
		return inv.hasCustomInventoryName();
	}

	@Override
	public boolean isItemValidForSlot(int arg0, ItemStack arg1) {
		return inv.isItemValidForSlot(inv.getAccessibleSlotsFromSide(side)[arg0], arg1);
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer arg0) {
		return inv.isUseableByPlayer(arg0);
	}

	@Override
	public void markDirty() {
		inv.markDirty();
	}

	@Override
	public void openInventory() {
		inv.openInventory();
	}

	@Override
	public void setInventorySlotContents(int arg0, ItemStack arg1) {
		inv.setInventorySlotContents(inv.getAccessibleSlotsFromSide(side)[arg0], arg1);
	}

}
