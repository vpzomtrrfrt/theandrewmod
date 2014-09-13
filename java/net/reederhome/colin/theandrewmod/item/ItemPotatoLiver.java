package net.reederhome.colin.theandrewmod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

public class ItemPotatoLiver extends ItemFood {

	public ItemPotatoLiver() {
		super(1, true);
		setCreativeTab(TheAndrewMod.tabAndrew);
		setUnlocalizedName("potatoLiver");
	}
	/*@Override
	public void registerIcons(IIconRegister ir) {
		this.itemIcon = ir.registerIcon(TheAndrewMod.MODID+":potatoliver");
	}*/
	public void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		player.attackEntityFrom(TheAndrewMod.deathByPotatoLiver, 20f);
	}
}