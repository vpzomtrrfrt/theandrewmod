package net.reederhome.colin.theandrewmod.command;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.reederhome.colin.theandrewmod.block.BlocksAndrew;

public class BeCactusCommand implements ICommand {

	ArrayList<String> aliases;
	
	public BeCactusCommand() {
		aliases = new ArrayList<String>();
		aliases.add("beacactus");
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
		if(var1 instanceof EntityLivingBase) {
			EntityLivingBase e = (EntityLivingBase) var1;
			ItemStack oldItems = e.getEquipmentInSlot(4);
			if(oldItems!=null) {
				e.dropItem(oldItems.getItem(), oldItems.stackSize);
			}
			if(var2.length>0) {
				try {
					int meta = Integer.parseInt(var2[0]);
					e.setCurrentItemOrArmor(4, new ItemStack(BlocksAndrew.dyedCactus, 1, meta));
					return;
				} catch(NumberFormatException ex){}
			}
			e.setCurrentItemOrArmor(4, new ItemStack(Blocks.cactus));
		}
		else {
			var1.addChatMessage(new ChatComponentText("You are too awesome to be a cactus."));
		}
	}

}
