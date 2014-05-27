package net.reederhome.colin.theandrewmod;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public class FirmMatressCommand implements ICommand {

	private List<String> aliases;
	
	public FirmMatressCommand() {
		this.aliases = new ArrayList<String>();
		this.aliases.add("firmmatress");
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
		return "firmmatress";
	}

	@Override
	public boolean isUsernameIndex(String[] var1, int var2) {
		return false;
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		World world = var1.getEntityWorld();
		ChunkCoordinates coords = var1.getPlayerCoordinates();
		int decoy = new Random().nextInt(7)+coords.posX-4;
		for(int x = coords.posX-4; x < coords.posX+3; x++) {
			world.setBlock(x, coords.posY, coords.posZ, x==decoy?TheAndrewMod.decoyBed:Blocks.bed);
			world.setBlock(x, coords.posY, coords.posZ+1, x==decoy?TheAndrewMod.decoyBed:Blocks.bed, 8, 3);
		}
		System.out.println("Decoy: "+decoy);
	}

}
