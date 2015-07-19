package net.reederhome.colin.theandrewmod.item;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

public class ItemSandifier extends Item {

	public ItemSandifier() {
		super();
		setTextureName(TheAndrewMod.MODID+":sandifier");
		setUnlocalizedName("sandifier");
		setMaxStackSize(1);
		setCreativeTab(TheAndrewMod.tabAndrew);
	}
	
	public boolean onItemUse(ItemStack stack, EntityPlayer p, World w, int x, int y, int z, int s, float f1, float f2, float f3) {
		if(w.isRemote) return true;
		Block b = w.getBlock(x, y, z);
		int meta = w.getBlockMetadata(x, y, z);
		EntityFallingBlock ent = new EntityFallingBlock(w, x+0.5, y+0.5, z+0.5, b, meta);
		TileEntity te = w.getTileEntity(x, y, z);
		if(te!=null) {
			NBTTagCompound nbt = new NBTTagCompound();
			te.writeToNBT(nbt);
			NBTTagCompound tag = new NBTTagCompound();
			ent.writeToNBT(tag);
			tag.setTag("TileEntityData", nbt);
			ent.readFromNBT(tag);
		}
		w.spawnEntityInWorld(ent);
		return true;
	}
}