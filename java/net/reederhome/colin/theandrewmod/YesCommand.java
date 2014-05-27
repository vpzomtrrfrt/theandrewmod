package net.reederhome.colin.theandrewmod;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;

public class YesCommand implements ICommand {

	private List<String> aliases = new ArrayList<String>();

	public YesCommand() {
		aliases.add("yes");
	}
	
	@Override
	public int compareTo(Object arg0) {
		return 0;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender var1, String[] var2) {
		return null;
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender var1) {
		return true;
	}

	@Override
	public List getCommandAliases() {
		return this.aliases;
	}

	@Override
	public String getCommandName() {
		return this.aliases.get(0);
	}

	@Override
	public String getCommandUsage(ICommandSender var1) {
		return this.aliases.get(0);
	}

	@Override
	public boolean isUsernameIndex(String[] var1, int var2) {
		return false;
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		if(var1.getEntityWorld().isRemote) return;
		ChunkCoordinates coords = var1.getPlayerCoordinates();
		int xp = coords.posX;
		int yp = coords.posY;
		int zp = coords.posZ;
		for(int y = 0; y < 8; y++) {
			var1.addChatMessage(new ChatComponentText("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy"));
			var1.getEntityWorld().spawnParticle("angryVillager", xp+new Random().nextInt(2)-1, yp+new Random().nextInt(2)-1, zp+new Random().nextInt(2)-1, Math.random()-0.5, Math.random()-0.5, Math.random()-0.5);
			if(var1 instanceof Entity) {var1.getEntityWorld().playSoundAtEntity((Entity)var1, "mob.villager.yes", 1f, 1f);} else {System.out.println("Not an entity");}
		}
	}

}
