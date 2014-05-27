package net.reederhome.colin.theandrewmod;

import java.util.Set;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;

public class ItemBlastingDevice extends Item {

	public ItemBlastingDevice() {
		setMaxDamage(18);
		setUnlocalizedName("blastingDevice");
		setFull3D();
		setCreativeTab(CreativeTabs.tabCombat);
	}
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		world.createExplosion(player, player.posX-2, player.posY, player.posZ, 3, false);
		world.createExplosion(player, player.posX+2, player.posY, player.posZ, 3, false);
		world.createExplosion(player, player.posX, player.posY, player.posZ-2, 3, false);
		world.createExplosion(player, player.posX, player.posY, player.posZ+2, 3, false);
		stack.damageItem(1, player);
		return stack;
	}
}
