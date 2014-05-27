package net.reederhome.colin.theandrewmod;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;

public class BeCommandBlockCommand implements ICommand {

	List<String> aliases;
	public BeCommandBlockCommand() {
		aliases = new ArrayList<String>();
		aliases.add("beacommandblock");
	}
	
	@Override
	public int compareTo(Object arg0) {
		return 0;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender var1, String[] var2) {
		return var2.length == 1 ? CommandBase.getListOfStringsMatchingLastWord(var2, MinecraftServer.getServer().getAllUsernames()) : null;
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
		return getCommandName()+" [player]";
	}

	@Override
	public boolean isUsernameIndex(String[] var1, int var2) {
		return false;
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		EntityLivingBase pl;
		if(var2.length==0) {
			if(var1 instanceof EntityLivingBase) {
				pl = (EntityLivingBase) var1;
			}
			else {
				var1.addChatMessage(new ChatComponentText("You can't become a command block."));
				return;
			}
		}
		else {
			pl = CommandBase.getPlayer(var1, var2[0]);
			if(pl==null) {var1.addChatMessage(new ChatComponentText("Player "+var2[0]+" not online."));return;}
		}
		pl.setCurrentItemOrArmor(4, new ItemStack(Blocks.command_block));
	}

}
