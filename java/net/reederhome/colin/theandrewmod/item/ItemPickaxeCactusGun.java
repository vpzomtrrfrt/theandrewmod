package net.reederhome.colin.theandrewmod.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

public class ItemPickaxeCactusGun extends ItemPickaxe {

	public ItemPickaxeCactusGun(ToolMaterial arg0) {
		super(arg0);
		setCreativeTab(TheAndrewMod.tabAndrew);
		setTextureName(TheAndrewMod.MODID+":pickaxeCactusGun"+arg0);
		setUnlocalizedName("pickaxeCactusGun"+arg0);
	}
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		return ItemsAndrew.cactusGun.onItemRightClick(stack, world, player);
	}
	
}
