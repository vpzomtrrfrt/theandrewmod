package net.reederhome.colin.theandrewmod.item;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.TheAndrewMod;
import net.reederhome.colin.theandrewmod.entity.EntityLuckEgg;

public class ItemLuckEgg extends ItemEgg {
	public ItemLuckEgg() {
		super();
		setCreativeTab(TheAndrewMod.tabAndrew);
		setTextureName(TheAndrewMod.MODID+":luckyEgg");
		setUnlocalizedName("luckEgg");
		BlockDispenser.dispenseBehaviorRegistry.putObject(this, new BehaviorProjectileDispense() {
			@Override
			protected IProjectile getProjectileEntity(World arg0, IPosition arg1) {
				EntityLuckEgg e = new EntityLuckEgg(arg0);
				e.setLocationAndAngles(arg1.getX(), arg1.getY(), arg1.getZ(), 0, 0);
				return e;
			}
		});
	}
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(!player.capabilities.isCreativeMode) {
			--stack.stackSize;
		}
		world.playSoundAtEntity(player, "random.bow", 0.5f, 0.4f/(itemRand.nextFloat()*0.4f+0.8f));
		if(!world.isRemote) {
			world.spawnEntityInWorld(new EntityLuckEgg(world, player));
		}
		return stack;
	}
}