package net.reederhome.colin.theandrewmod.item;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.AchievementsAndrew;
import net.reederhome.colin.theandrewmod.TheAndrewMod;
import net.reederhome.colin.theandrewmod.entity.EntityThrownCactus;

public class ItemCactusGun extends Item {

	public ItemCactusGun() {
		setCreativeTab(TheAndrewMod.tabAndrew);
		setFull3D();
		setUnlocalizedName("cactusGun");
	}
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(player.inventory.hasItem(new ItemStack(Blocks.cactus).getItem())) {
			world.playSoundAtEntity(player, "random.bow", 0.5f, (itemRand.nextFloat() * 0.4F + 0.8F));
			if(!world.isRemote) {
				if(Math.random()<0.3) {
					player.inventory.consumeInventoryItem(new ItemStack(Blocks.cactus).getItem());
				}
				EntityThrownCactus ent = new EntityThrownCactus(world, player);
				if(player.inventory.hasItem(ItemsAndrew.rainbowCoreAdvanced)) {
					ent.setClr(new Random().nextInt(16));
				}
				world.spawnEntityInWorld(ent);
			}
			player.addStat(AchievementsAndrew.cactusGun, 1);
		}
		return stack;
	}
}