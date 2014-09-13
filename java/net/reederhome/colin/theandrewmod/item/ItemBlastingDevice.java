package net.reederhome.colin.theandrewmod.item;

import java.util.Set;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

public class ItemBlastingDevice extends Item {

	static final String griefingRule = "blastingDeviceGriefing";
	public ItemBlastingDevice() {
		setMaxDamage(18);
		setUnlocalizedName("blastingDevice");
		setFull3D();
		setCreativeTab(TheAndrewMod.tabAndrew);
	}
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(!world.isRemote) {
			if(!world.getGameRules().hasRule(griefingRule)) {world.getGameRules().addGameRule(griefingRule, "false");}
			boolean griefing = world.getGameRules().getGameRuleBooleanValue(griefingRule);
			world.createExplosion(player, player.posX-2, player.posY, player.posZ, 3, griefing);
			world.createExplosion(player, player.posX+2, player.posY, player.posZ, 3, griefing);
			world.createExplosion(player, player.posX, player.posY, player.posZ-2, 3, griefing);
			world.createExplosion(player, player.posX, player.posY, player.posZ+2, 3, griefing);
		}
		stack.damageItem(1, player);
		return stack;
	}
}
