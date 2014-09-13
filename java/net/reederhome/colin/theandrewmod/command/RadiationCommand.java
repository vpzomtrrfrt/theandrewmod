package net.reederhome.colin.theandrewmod.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

public class RadiationCommand implements ICommand {

	List<String> aliases;
	public RadiationCommand() {
		aliases = new ArrayList<String>();
		aliases.add("radiation");
	}
	
	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender var1, String[] var2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender var1) {
		return true;
	}

	@Override
	public List getCommandAliases() {
		return aliases;
	}

	@Override
	public String getCommandName() {
		return aliases.get(0);
	}

	@Override
	public String getCommandUsage(ICommandSender var1) {
		return getCommandName();
	}

	@Override
	public boolean isUsernameIndex(String[] var1, int var2) {
		return false;
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		ChunkCoordinates c = var1.getPlayerCoordinates();
		List<EntityLivingBase> l = var1.getEntityWorld().getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(c.posX-10, c.posY-10, c.posZ-10, c.posX+10, c.posY+10, c.posZ+10));
		Iterator<EntityLivingBase> i = l.iterator();
		while(i.hasNext()) {
			EntityLivingBase t = i.next();
			t.addPotionEffect(new PotionEffect(TheAndrewMod.cancerPotion.id, 3600));
			if(t instanceof ICommandSender) {
				((ICommandSender) t).addChatMessage(new ChatComponentText("[Atomic Bomb] BOOM!"));
			}
		}
		var1.getEntityWorld().createExplosion((var1 instanceof Entity)?((Entity)var1):null, c.posX, c.posY, c.posZ, 2, true);
	}

}
