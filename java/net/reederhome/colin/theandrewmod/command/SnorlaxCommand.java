package net.reederhome.colin.theandrewmod.command;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.entity.EntityThomas;

public class SnorlaxCommand implements ICommand {

	private List<String> aliases;
	
	public SnorlaxCommand() {
		aliases = new ArrayList<String>();
		aliases.add("snorlax");
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
		return var1.canCommandSenderUseCommand(3, getCommandName());
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
		return getCommandName();
	}

	@Override
	public boolean isUsernameIndex(String[] var1, int var2) {
		return false;
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		World world = var1.getEntityWorld();
		if(!world.isRemote) {
			ChunkCoordinates coords = var1.getPlayerCoordinates();
			EntityThomas thomas = new EntityThomas(world);
			thomas.setLocationAndAngles(coords.posX+Math.random()*8-4,coords.posY+1,coords.posZ+Math.random()*8-4, 180, 0);
			world.spawnEntityInWorld(thomas);
		}
	}

}
