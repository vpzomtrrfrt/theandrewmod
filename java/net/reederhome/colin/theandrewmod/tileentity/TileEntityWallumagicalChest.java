package net.reederhome.colin.theandrewmod.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityChest;

public class TileEntityWallumagicalChest extends TileEntityChest {

	ItemStack[] altInv = new ItemStack[36];
	
	public void swapInventory() {
		ItemStack[] tmp = new ItemStack[36];
		for(int i = 0; i < getSizeInventory(); i++) {
			tmp[i]=getStackInSlot(i);
		}
		for(int i = 0; i < getSizeInventory(); i++) {
			setInventorySlotContents(i, altInv[i]);
		}
		altInv = tmp;
	}
	
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		NBTTagList l = new NBTTagList();
		for(int i = 0; i < altInv.length; i++) {
			if(altInv[i]!=null) {
				NBTTagCompound tag = new NBTTagCompound();
				altInv[i].writeToNBT(tag);
				tag.setInteger("Slot", i);
				l.appendTag(tag);
			}
		}
		nbt.setTag("AltInv", l);
	}
	
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagList l = (NBTTagList)nbt.getTag("AltInv");
		for(int i = 0; i < l.tagCount(); i++) {
			NBTTagCompound tag = l.getCompoundTagAt(i);
			altInv[tag.getInteger("Slot")]=ItemStack.loadItemStackFromNBT(tag);
		}
	}
}