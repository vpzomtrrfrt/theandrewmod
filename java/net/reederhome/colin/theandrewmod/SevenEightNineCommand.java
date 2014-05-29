package net.reederhome.colin.theandrewmod;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class SevenEightNineCommand implements ICommand {

	private List<String> aliases;
	
	public SevenEightNineCommand() {
		this.aliases = new ArrayList();
		this.aliases.add("789");
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
		return "789 [player]";
	}

	@Override
	public boolean isUsernameIndex(String[] var1, int var2) {
		return false;
	}

	static Entity forEnemy;
	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		World world = var1.getEntityWorld();
		double posX, posY, posZ;
		if(var2.length>0) {
			EntityPlayer ep = CommandBase.getPlayer(var1,var2[0]);
			if(ep==null) {var1.addChatMessage(new ChatComponentText("Player "+var2[0]+" doesn't exist!"));return;}
			posX=ep.posX;
			posY=ep.posY;
			posZ=ep.posZ;
		}
		else if(var1 instanceof EntityPlayer) {
			EntityPlayer ep = (EntityPlayer) var1;
			posX=ep.posX;
			posY=ep.posY;
			posZ=ep.posZ;
		}
		else {
			ChunkCoordinates coords = var1.getPlayerCoordinates();
			posX=coords.posX;
			posY=coords.posY;
			posZ=coords.posZ;
		}
		EntityItem seven = new EntityItem(world, posX, posY+3, posZ, new ItemStack(Items.diamond,7));
		world.spawnEntityInWorld(seven);
		EntityItem eight = new EntityItem(world, posX, posY+5, posZ, new ItemStack(Items.emerald,8));
		world.spawnEntityInWorld(eight);
		forEnemy=null;
		if(var1 instanceof Entity&&var2.length>0) {
			forEnemy=(Entity) var1;
		}
		for(int i = 0; i < 9; i++) {
			EntityFallingBlock nine = new EntityFallingBlock(world, posX, posY+9+i, posZ, Blocks.anvil) {
				Entity enemy = forEnemy;
				protected void fall(float par1)
			    {
			        if (true)
			        {
			            int var2 = MathHelper.ceiling_float_int(par1 - 1.0F);

			            if (var2 > 0)
			            {
			                ArrayList var3 = new ArrayList(this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox));
			                DamageSource var5 = TheAndrewMod.deathBy789;
			                Iterator var6 = var3.iterator();

			                while (var6.hasNext())
			                {
			                    Entity var7 = (Entity)var6.next();
			                    if(enemy==null) {var7.attackEntityFrom(var5,20.0f);}
			                    else var7.attackEntityFrom(new EntityDamageSource("theandrewmod.deathBy789.player", enemy), 20.0f);
			                }
			            }
			        }
			    }
			};
			nine.field_145812_b=1;
			world.spawnEntityInWorld(nine);
		}
	}

}
