package net.reederhome.colin.theandrewmod.tileentity;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityJumpPad extends TileEntity {

	public String owner = "";

	@Override
	public void updateEntity() {
		//System.out.println("updateEntity");
		AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord+1, yCoord+10, zCoord+1);
		//System.out.println(xCoord+", "+yCoord+", "+zCoord);
		List l = this.worldObj.getEntitiesWithinAABB(EntityPlayerMP.class, aabb);
		Iterator i = l.iterator();
		while(i.hasNext()) {
			EntityPlayerMP e = (EntityPlayerMP) i.next();
			//System.out.println(e.getCommandSenderName());
			if(!(e.getCommandSenderName().equals(owner)&&e.isSneaking())) {
				if(worldObj.getBlockMetadata(xCoord, yCoord, zCoord)==5) {e.addPotionEffect(new PotionEffect(Potion.resistance.id, 5, 2));}
				e.addPotionEffect(new PotionEffect(8, 5, 40));
			}
		}
		List l2 = this.worldObj.getEntitiesWithinAABB(EntityLiving.class, aabb);
		Iterator i2 = l2.iterator();
		while(i2.hasNext()) {
			EntityLiving e = (EntityLiving) i2.next();
			//System.out.println(e.getCommandSenderName());
			if(worldObj.getBlockMetadata(xCoord, yCoord, zCoord)==5) {e.addPotionEffect(new PotionEffect(Potion.resistance.id, 5, 2));}
			e.addPotionEffect(new PotionEffect(8, 5, 40));
		}
	}
	
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setString("Owner", owner);
	}
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		owner=nbt.getString("Owner");
	}
}
