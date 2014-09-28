package net.reederhome.colin.theandrewmod;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;

public class NetworkBootInventory implements IInventory {
	EntityLivingBase ent;
	EntityPlayer user;
	
	public NetworkBootInventory(EntityPlayer p, EntityLivingBase e) {
		ent=e;
		user=p;
	}

	@Override
	public void closeInventory() {}

	@Override
	public ItemStack decrStackSize(int arg0, int arg1) {
		return null;
	}

	@Override
	public String getInventoryName() {
		return "Network Boot: "+ent.getCommandSenderName();
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
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int arg0) {
		return null;
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
	public void markDirty() {}

	@Override
	public void openInventory() {
		if(ent instanceof EntityPlayerMP) {
			((EntityPlayerMP) ent).addChatMessage(new ChatComponentText(user.getCommandSenderName()+" is using a network boot."));
		}
	}

	@Override
	public void setInventorySlotContents(int arg0, ItemStack arg1) {
		if(!(ent instanceof EntityPlayer&&((EntityPlayer)ent).inventory.addItemStackToInventory(arg1))) {
			EntityItem it = ent.dropItem(Items.command_block_minecart, 1);
			it.setEntityItemStack(arg1);
		}
	}

	
}