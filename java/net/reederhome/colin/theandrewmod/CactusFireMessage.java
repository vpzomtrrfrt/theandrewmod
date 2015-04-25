package net.reederhome.colin.theandrewmod;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.reederhome.colin.theandrewmod.item.ItemPickaxeCactusGun;
import net.reederhome.colin.theandrewmod.item.ItemsAndrew;
import baubles.api.BaublesApi;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class CactusFireMessage implements IMessage {

	public CactusFireMessage(){}
	
	@Override
	public void fromBytes(ByteBuf arg0) {
		
	}

	@Override
	public void toBytes(ByteBuf arg0) {
		
	}
	
	public static class Handler implements IMessageHandler<CactusFireMessage, IMessage> {

		public boolean hasCactusGun(EntityPlayer player) {
			ItemStack held = player.getHeldItem();
			if(held!=null) {
				if(held.getItem().equals(ItemsAndrew.cactusGun)) {
					return true;
				}
				if(held.getItem() instanceof ItemPickaxeCactusGun) {
					return true;
				}
			}
			ItemStack pants = player.getCurrentArmor(1);
			if(pants != null && pants.getItem() instanceof ItemArmor) {
				if(((ItemArmor)pants.getItem()).getArmorMaterial().equals(TheAndrewMod.cactusGunArmorMaterial)) {
					return true;
				}
			}
			IInventory baubles = BaublesApi.getBaubles(player);
			if(baubles!=null) {
				ItemStack belt = baubles.getStackInSlot(3);
				if(belt!=null && belt.getItem().equals(ItemsAndrew.cactusGunBelt)) {
					return true;
				}
			}
			return false;
		}
		
		@Override
		public IMessage onMessage(CactusFireMessage arg0, MessageContext arg1) {
			try {
				EntityPlayer player = arg1.getServerHandler().playerEntity;
				if(hasCactusGun(player)) {
					ItemsAndrew.cactusGun.onItemRightClick(null, player.worldObj, player);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
	}

}
