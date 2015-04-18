package net.reederhome.colin.theandrewmod.support.morph;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.reederhome.colin.theandrewmod.TheAndrewMod;
import morph.api.Ability;

public class AbilityExplodeOnDeath extends Ability {

	@Override
	public String getType() {
		return "explodeOnDeath";
	}

	@Override
	public void tick() {}

	@Override
	public void kill() {
	}

	@Override
	public Ability clone() {
		return new AbilityExplodeOnDeath();
	}

	@Override
	public void save(NBTTagCompound tag) {}

	@Override
	public void load(NBTTagCompound tag) {}

	@Override
	public void postRender() {}

	@Override
	public ResourceLocation getIcon() {
		return new ResourceLocation(TheAndrewMod.MODID+":textures/support/morph/explodeOnDeath.png");
	}

}
