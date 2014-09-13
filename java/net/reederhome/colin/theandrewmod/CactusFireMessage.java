package net.reederhome.colin.theandrewmod;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.reederhome.colin.theandrewmod.item.ItemPickaxeCactusGun;
import net.reederhome.colin.theandrewmod.item.ItemsAndrew;
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

		@Override
		public IMessage onMessage(CactusFireMessage arg0, MessageContext arg1) {
			try {
				EntityPlayer player = arg1.getServerHandler().playerEntity;
				if((player.getHeldItem()!=null&&(player.getHeldItem().getItem().equals(ItemsAndrew.cactusGun)||player.getHeldItem().getItem() instanceof ItemPickaxeCactusGun))||(player.getCurrentArmor(1)!=null&&player.getCurrentArmor(1).getItem().equals(ItemsAndrew.cactusGunPants))) {
					ItemsAndrew.cactusGun.onItemRightClick(null, player.worldObj, player);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
	}

}
