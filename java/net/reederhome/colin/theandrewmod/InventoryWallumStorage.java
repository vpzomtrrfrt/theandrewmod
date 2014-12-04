package net.reederhome.colin.theandrewmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.reederhome.colin.theandrewmod.item.ItemWallumStorage;

public class InventoryWallumStorage implements IInventory {

	ItemStack stack;
	EntityPlayer user;
	
	public InventoryWallumStorage(ItemStack w, EntityPlayer p) {
		stack=w;
		user=p;
	}
	
	public ItemStack[] getItems() {
		//System.out.println("retrieval on "+FMLCommonHandler.instance().getEffectiveSide());
		ItemStack[] tr = new ItemStack[9];
		if(stack.hasTagCompound()) {
			//System.out.println("there is a tag");
			//System.out.println(stack.getTagCompound());
			if(stack.getTagCompound().hasKey("Items")) {
				NBTTagList l = (NBTTagList) stack.getTagCompound().getTag("Items");
				if(l!=null) {
					for(int i = 0; i < l.tagCount(); i++) {
						NBTTagCompound tag = l.getCompoundTagAt(i);
						tr[tag.getInteger("Slot")]=ItemStack.loadItemStackFromNBT(tag);
					}
				}
			}
		}
		return tr;
	}
	
	public void saveItems(ItemStack[] ts) {
		//System.out.println("saving on "+FMLCommonHandler.instance().getEffectiveSide());
		NBTTagList l = new NBTTagList();
		for(int i = 0; i < ts.length; i++) {
			if(ts[i]!=null) {
				if(ts[i]!=stack) {
					NBTTagCompound tag = new NBTTagCompound();
					ts[i].writeToNBT(tag);
					tag.setInteger("Slot", i);
					l.appendTag(tag);
				}
				else {
					user.attackEntityFrom(TheAndrewMod.deathByCrafting, 100);
				}
			}
		}
		NBTTagCompound nbt;
		if(stack.hasTagCompound()) {
			//System.out.println("saving to existing tag");
			nbt=stack.getTagCompound();
		}
		else {
			nbt=new NBTTagCompound();
		}
		nbt.setTag("Items", l);
		stack.setTagCompound(nbt);
		//System.out.println(stack.getTagCompound());
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
		return stack.getUnlocalizedName()+".name";
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
		if(arg0.equals(user)&&user.getHeldItem()!=null) {
			if(user.getHeldItem().equals(stack)) {
				return true;
			}
			else {
				if(user.getHeldItem().getItem() instanceof ItemWallumStorage) {
					stack=user.getHeldItem();
					return isUseableByPlayer(arg0);
				}
			}
		}
		return false;
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
