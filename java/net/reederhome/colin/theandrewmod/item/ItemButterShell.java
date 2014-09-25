package net.reederhome.colin.theandrewmod.item;

import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

public class ItemButterShell extends Item {

	public ItemButterShell() {
		super();
		setMaxStackSize(0);
		setUnlocalizedName("butterShellFull");
		setTextureName(TheAndrewMod.MODID+":butterShellFull");
	}
	
	public void addInformation(ItemStack stack, EntityPlayer p, List info, boolean arg3) {
		if(stack.hasTagCompound()) {
			NBTTagList l = (NBTTagList) stack.getTagCompound().getTag("Items");
			for(int i = 0; i < l.tagCount(); i++) {
				info.add(ItemStack.loadItemStackFromNBT(l.getCompoundTagAt(i)).getUnlocalizedName());
			}
		}
	}
	
	public ItemStack onItemRightClick(ItemStack stack, World w, EntityPlayer p) {
		if(!w.isRemote) {
			NBTTagList l = (NBTTagList) stack.getTagCompound().getTag("Items");
			for(int i = 0; i < l.tagCount(); i++) {
				NBTTagCompound tag = l.getCompoundTagAt(i);
				EntityItem en = p.dropItem(Items.command_block_minecart, 1);
				en.setEntityItemStack(ItemStack.loadItemStackFromNBT(tag));
			}
		}
		return new ItemStack(ItemsAndrew.butterShellEmpty);
	}
}