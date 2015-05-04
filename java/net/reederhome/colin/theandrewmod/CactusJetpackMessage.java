package net.reederhome.colin.theandrewmod;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.reederhome.colin.theandrewmod.entity.EntityThrownCactus;
import net.reederhome.colin.theandrewmod.item.ItemsAndrew;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class CactusJetpackMessage implements IMessage {
	
	@Override
	public void fromBytes(ByteBuf arg0) {}

	@Override
	public void toBytes(ByteBuf arg0) {}
	
	public static class Handler implements IMessageHandler<CactusJetpackMessage,IMessage> {

		@Override
		public IMessage onMessage(CactusJetpackMessage arg0, MessageContext arg1) {
			EntityPlayer e = arg1.getServerHandler().playerEntity;
			if(e.getCurrentArmor(2)!=null && e.getCurrentArmor(2).getItem().equals(ItemsAndrew.cactusGunJetpack)) {
				e.addPotionEffect(new PotionEffect(Potion.resistance.id,1,4));
				EntityThrownCactus cac = new EntityThrownCactus(e.worldObj, true);
				cac.setPosition(e.posX, e.posY-0.1, e.posZ);
				cac.motionY=1;
				e.worldObj.spawnEntityInWorld(cac);
				e.worldObj.createExplosion(cac, e.posX, e.posY, e.posZ, 0.2f, false);
			}
			return null;
		}
		
	}

}