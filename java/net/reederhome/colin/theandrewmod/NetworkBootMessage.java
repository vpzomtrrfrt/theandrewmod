package net.reederhome.colin.theandrewmod;

import io.netty.buffer.ByteBuf;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.reederhome.colin.theandrewmod.item.NetworkArmor;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class NetworkBootMessage implements IMessage {

	public NetworkBootMessage(){}
	
	@Override
	public void fromBytes(ByteBuf arg0) {
		
	}

	@Override
	public void toBytes(ByteBuf arg0) {
		
	}
	
	public static class Handler implements IMessageHandler<NetworkBootMessage, IMessage> {

		@Override
		public IMessage onMessage(NetworkBootMessage arg0, MessageContext arg1) {
			try {
				EntityPlayer player = arg1.getServerHandler().playerEntity;
				if(player.getCurrentArmor(0)!=null&&player.getCurrentArmor(0).getItem() instanceof NetworkArmor) {
					double r = 10;
					List<EntityLivingBase> l = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(player.posX-r, player.posY-r, player.posZ-r, player.posX+r, player.posY+r, player.posZ+r));
					Iterator<EntityLivingBase> it = l.iterator();
					EntityLivingBase t = null;
					while(it.hasNext()) {
						EntityLivingBase e = it.next();
						if(e.getEquipmentInSlot(1)!=null&&(e.getEquipmentInSlot(1).getItem() instanceof NetworkArmor)) {
							t=e;
							break;
						}
					}
					if(t!=null) {
						player.displayGUIChest(new NetworkBootInventory(player,t));
					}
					else {
						System.out.println(t);
					}
				}
				else {
					System.out.println("not got the boots!");
					System.out.println(player.getCurrentArmor(0).getItem());
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
	}

}
