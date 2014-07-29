package net.reederhome.colin.theandrewmod;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityItemButterDust extends EntityItem {

	public EntityItemButterDust(World par1World) {
		super(par1World);
		this.setEntityItemStack(new ItemStack(ItemsAndrew.butterDust));
	}
	
	public void onUpdate() {
		super.onUpdate();
		if(this.isBurning()) {
			System.out.println("I'm burning!");
			this.setDead();
			int bdr = 10;
			List<EntityLivingBase> l = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(posX-bdr, posY-bdr, posZ-bdr, posX+bdr, posY+bdr, posZ+bdr));
			Iterator<EntityLivingBase> i = l.iterator();
			while(i.hasNext()) {
				EntityLivingBase thing = i.next();
				int md = 600;
				int dur = (int)(md-(md/bdr)*Math.sqrt(Math.pow(posX-thing.posX, 2)+Math.pow(posY-thing.posY, 2)+Math.pow(posZ-thing.posZ, 2)));
				thing.addPotionEffect(new PotionEffect(Potion.confusion.getId(), dur, 0));
				thing.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), dur, 95));
				thing.addPotionEffect(new PotionEffect(Potion.digSlowdown.getId(), dur, 1));
			}
		}
	}

}
