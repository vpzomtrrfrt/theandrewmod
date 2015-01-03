package net.reederhome.colin.theandrewmod.entity;

import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityHPCreeper extends EntityCreeper {

	public EntityHPCreeper(World arg0) {
		super(arg0);
	}
	
	public void onUpdate() {
		super.onUpdate();
		if(ticksExisted%((this.dataWatcher.getWatchableObjectByte(17)==1)?5:20)==0) {
			if(!worldObj.isRemote) {
				worldObj.spawnEntityInWorld(new EntityPotion(worldObj, this, new ItemStack(Items.potionitem, 1, 16389)));
			}
		}
	}
	
	public void onDeath(DamageSource src) {
		super.onDeath(src);
		if(worldObj.isRemote) return;
		dropItem(Items.redstone, rand.nextInt(4));
	}

}