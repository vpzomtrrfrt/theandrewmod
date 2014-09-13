package net.reederhome.colin.theandrewmod.tileentity;

import net.minecraft.block.BlockTNT;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTNTChest extends TileEntity implements IInventory {

	public ItemStack[] contents = new ItemStack[36];

	@Override
	public void closeInventory() {}

	@Override
	public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.contents[par1] != null)
        {
            ItemStack var3;

            if (this.contents[par1].stackSize <= par2)
            {
                var3 = this.contents[par1];
                this.contents[par1] = null;
                return var3;
            }
            else
            {
                var3 = this.contents[par1].splitStack(par2);

                if (this.contents[par1].stackSize == 0)
                {
                    this.contents[par1] = null;
                }

                return var3;
            }
        }
		return null;
	}

	@Override
	public String getInventoryName() {
		return "TNT Chest";
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public int getSizeInventory() {
		return 27;
	}

	@Override
	public ItemStack getStackInSlot(int arg0) {
		return contents[arg0];
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int arg0) {
		return contents[arg0];
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
	public void openInventory() {worldObj.createExplosion(null, xCoord, yCoord, zCoord, 1, false);}

	@Override
	public void setInventorySlotContents(int arg0, ItemStack arg1) {
		contents[arg0]=arg1;
		if(arg1!=null&&arg1.getItem().equals(Item.getItemFromBlock(Blocks.redstone_torch))) {
			worldObj.setBlockToAir(xCoord, yCoord, zCoord);
			EntityTNTPrimed p = new EntityTNTPrimed(worldObj, xCoord, yCoord, zCoord, null);
			worldObj.spawnEntityInWorld(p);
		}
	}
	
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		NBTTagList l = new NBTTagList();
		for(int i=0;i<contents.length;i++) {
			if(contents[i]!=null) {
				NBTTagCompound tag = new NBTTagCompound();
				contents[i].writeToNBT(tag);
				tag.setInteger("Slot", i);
				l.appendTag(tag);
			}
		}
		nbt.setTag("Items", l);
	}
	
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagList l = (NBTTagList) nbt.getTag("Items");
		for(int i=0;i<l.tagCount();i++) {
			NBTTagCompound tag = l.getCompoundTagAt(i);
			contents[tag.getInteger("Slot")]=ItemStack.loadItemStackFromNBT(tag);
		}
	}
}
