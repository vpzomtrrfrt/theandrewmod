package net.reederhome.colin.theandrewmod.item;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.TheAndrewMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWallumTeleport extends Item {

	public ItemWallumTeleport() {
		super();
		setUnlocalizedName("wallumTeleport");
		setTextureName(TheAndrewMod.MODID+":wallumTeleport");
		setMaxDamage(300);
		setFull3D();
		setCreativeTab(TheAndrewMod.tabAndrew);
		setMaxStackSize(1);
	}
	
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.eat;
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		return par1ItemStack;
	}
	
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 64;
    }
	
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer p) {
		NBTTagCompound tag = stack.getTagCompound();
		boolean success = false;
		if(tag!=null && tag.hasKey("Pos")) {
			int[] pos = tag.getIntArray("Pos");
			if(pos.length==4) {
				if(pos[0] != world.provider.dimensionId) {
					p.travelToDimension(pos[0]);
				}
				p.setPosition(pos[1]+0.5, pos[2], pos[3]+0.5);
				success = true;
			}
		}
		if(!success) {
			p.addChatMessage(new ChatComponentText("This Teleport Wallum is invalid."));
		}
		else if(!p.capabilities.isCreativeMode) {
			stack.damageItem(1, p);
		}
		return stack;
	}
	
	public void addInformation(ItemStack stack, EntityPlayer p, List l, boolean b) {
		boolean Bob = false;
		NBTTagCompound tag = stack.getTagCompound();
		if(tag!=null && tag.hasKey("Pos")) {
			int[] pos = tag.getIntArray("Pos");
			if(pos.length==4) {
				l.add("Bound to "+pos[1]+", "+pos[2]+", "+pos[3]+" in dimension "+pos[0]);
				Bob = true;
			}
		}
		if(tag!=null && tag.getBoolean("CRAFTWAT")) {
			l.add("Will bind to your current location");
		}
		else if(!Bob/*the Chicken*/) {
			l.add("Invalid Teleport Wallum.");
		}
	}
	
	public ItemStack getIt(ItemStack st, EntityPlayer p) {
		if(st==null) {
			st = new ItemStack(ItemsAndrew.wallumTeleport);
		}
		NBTTagCompound tag = new NBTTagCompound();
		tag.setIntArray("Pos", new int[]{p.worldObj.provider.dimensionId, (int)p.posX, (int)p.posY, (int)p.posZ});
		st.setTagCompound(tag);
		return st;
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item i, CreativeTabs tab, List l) {
		l.add(getIt(null, Minecraft.getMinecraft().thePlayer));
	}
}