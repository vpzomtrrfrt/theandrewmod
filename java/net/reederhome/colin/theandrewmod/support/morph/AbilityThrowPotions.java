package net.reederhome.colin.theandrewmod.support.morph;

import morph.api.Ability;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

public class AbilityThrowPotions extends Ability {

	@Override
	public String getType() {
		return "throwPotions";
	}

	@Override
	public void tick() {
		if(getParent().ticksExisted%20==0 && !getParent().worldObj.isRemote) {
			getParent().worldObj.spawnEntityInWorld(new EntityPotion(getParent().worldObj, getParent(), new ItemStack(Items.potionitem, 1, 16389)));
		}
	}

	@Override
	public void kill() {}

	@Override
	public Ability clone() {
		return new AbilityThrowPotions();
	}

	@Override
	public void save(NBTTagCompound tag) {}

	@Override
	public void load(NBTTagCompound tag) {}

	@Override
	public void postRender() {}

	@Override
	public ResourceLocation getIcon() {
		return new ResourceLocation(TheAndrewMod.MODID+":textures/support/morph/throwPotions.png");
	}

}
