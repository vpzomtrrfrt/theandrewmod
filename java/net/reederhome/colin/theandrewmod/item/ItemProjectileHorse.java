package net.reederhome.colin.theandrewmod.item;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.TheAndrewMod;
import net.reederhome.colin.theandrewmod.entity.EntityProjectileHorse;

public class ItemProjectileHorse extends Item {

	public ItemProjectileHorse() {
		super();
		setCreativeTab(TheAndrewMod.tabAndrew);
		setTextureName(TheAndrewMod.MODID+":horseBomb");
		setUnlocalizedName("horseBomb");
	}
	
	public ItemStack onItemRightClick(ItemStack stack, World w, EntityPlayer p) {
		if(!w.isRemote) {
			EntityProjectileHorse horse = new EntityProjectileHorse(w);
			horse.onSpawnWithEgg(new IEntityLivingData(){});
	        double mx = (double)(-MathHelper.sin(p.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(p.rotationPitch / 180.0F * (float)Math.PI) * 0.4);
	        double mz = (double)(MathHelper.cos(p.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(p.rotationPitch / 180.0F * (float)Math.PI) * 0.4);
	        double my = (double)(-MathHelper.sin(p.rotationPitch / 180.0F * (float)Math.PI) * 0.4);
			horse.setThrowableHeading(mx, my, mz);
			horse.setPosition(p.posX, p.posY, p.posZ);
			w.spawnEntityInWorld(horse);
		}
		if(!p.capabilities.isCreativeMode) {
			if(stack.stackSize>1) {
				stack.stackSize--;
				return stack;
			}
			else {
				return null;
			}
		}
		else {
			return stack;
		}
	}
}