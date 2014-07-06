package net.reederhome.colin.theandrewmod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCactusGun extends Item {

	public ItemCactusGun() {
		setCreativeTab(TheAndrewMod.tabAndrew);
		setFull3D();
		setUnlocalizedName("cactusGun");
	}
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(player.inventory.hasItem(new ItemStack(Blocks.cactus).getItem())) {
			world.playSoundAtEntity(player, "random.bow", 0.5f, (itemRand.nextFloat() * 0.4F + 0.8F));
			if(Math.random()<0.3) {
				player.inventory.consumeInventoryItem(new ItemStack(Blocks.cactus).getItem());
			}
			if(!world.isRemote) {
				world.spawnEntityInWorld(new EntityThrownCactus(world, player));
			}
			player.addStat(AchievementsAndrew.cactusGun, 1);
		}
		return stack;
	}
}