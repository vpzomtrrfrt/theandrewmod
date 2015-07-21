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
import net.reederhome.colin.theandrewmod.block.BlocksAndrew;
import net.reederhome.colin.theandrewmod.entity.EntityThrownCactus;

public class ItemCactusGun extends Item {

	public ItemCactusGun() {
		setCreativeTab(TheAndrewMod.tabAndrew);
		setFull3D();
		setUnlocalizedName("cactusGun");
	}
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		boolean hasCactus = false;
		if(player.inventory.hasItem(new ItemStack(Blocks.cactus).getItem())) {
			hasCactus = true;
			if(!world.isRemote && Math.random()<0.3) {
				player.inventory.consumeInventoryItem(new ItemStack(Blocks.cactus).getItem());
			}
		}
		else if(player.inventory.hasItem(Item.getItemFromBlock(BlocksAndrew.compressedCactus))) {
			hasCactus = true;
			if(!world.isRemote && Math.random()<0.033) {
				player.inventory.consumeInventoryItem(Item.getItemFromBlock(BlocksAndrew.compressedCactus));
			}
		}
		player.inventoryContainer.detectAndSendChanges();
		System.out.println((world.isRemote?"Client":"Server")+" "+(hasCactus?"has":"does not have")+" cactus");
		if(hasCactus) {
			world.playSoundAtEntity(player, "random.bow", 0.5f, (itemRand.nextFloat() * 0.4F + 0.8F));
			if(!world.isRemote) {
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