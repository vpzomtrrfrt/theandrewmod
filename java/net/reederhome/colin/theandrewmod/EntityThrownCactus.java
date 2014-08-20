package net.reederhome.colin.theandrewmod;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityThrownCactus extends EntityThrowable {

	boolean doHoe=false;
	int fortune=0;
	int power=0;
	int cactus=0;
	boolean silkTouch=false;
	EntityLivingBase user;
	
	public int getClr() {
		try {
			return this.getDataWatcher().getWatchableObjectInt(14);
		} catch(Exception e) {
			return -1;
		}
	}
	public void setClr(int clr) {
		try {
			this.getDataWatcher().updateObject(14, clr);
		} catch(Exception e) {
			this.getDataWatcher().addObject(14, clr);
		}
	}
	public EntityThrownCactus(World par1World,
			EntityLivingBase par2EntityLivingBase) {
		super(par1World, par2EntityLivingBase);
		if(par2EntityLivingBase.isSneaking()) {
			doHoe=true;
		}
		user=par2EntityLivingBase;
		fortune=EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, par2EntityLivingBase.getHeldItem());
		power=EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par2EntityLivingBase.getHeldItem());
		cactus=EnchantmentHelper.getEnchantmentLevel(TheAndrewMod.cactusEnchantment.effectId, par2EntityLivingBase.getHeldItem());
		if(EnchantmentHelper.getEnchantmentLevel(Enchantment.silkTouch.effectId, par2EntityLivingBase.getHeldItem())>0) {
			silkTouch=true;
		}
		this.getDataWatcher().addObject(14, -1);
	}
	
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setBoolean("Hoe", doHoe);
		nbt.setIntArray("Ench", new int[]{fortune,power,cactus});
		nbt.setBoolean("Silk", silkTouch);
		nbt.setInteger("Color", getClr());
	}
	
	public boolean hk(NBTTagCompound nbt, String tag) {
		return nbt.hasKey(tag);
	}
	
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		if(hk(nbt,"Hoe")) {
			doHoe=nbt.getBoolean("Hoe");
		}
		if(hk(nbt,"Ench")) {
			int[] ench = nbt.getIntArray("Ench");
			fortune=ench[0];
			power=ench[1];
			cactus=ench[2];
		}
		if(hk(nbt,"Silk")) {
			silkTouch=nbt.getBoolean("Silk");
		}
		if(hk(nbt,"Color")) {
			setClr(nbt.getInteger("Color"));
		}
	}
	
	public EntityThrownCactus(World world) {
		super(world);
	}

	@Override
	protected void onImpact(MovingObjectPosition var1) {
		if(worldObj.isRemote) return;
		if(var1.entityHit!=null) {
			if(var1.entityHit.isEntityInvulnerable()||(var1.entityHit instanceof EntityPlayer&&((EntityPlayer)var1.entityHit).capabilities.isCreativeMode)) return;
			var1.entityHit.attackEntityFrom(DamageSource.cactus, 4+power);
			var1.entityHit.dropItem(new ItemStack(Blocks.cactus).getItem(), cactus);
		}
		else {
			Block thing = worldObj.getBlock(var1.blockX, var1.blockY, var1.blockZ);
			if(doHoe&&(thing.equals(Blocks.dirt)||thing.equals(Blocks.grass))) {
				worldObj.setBlock(var1.blockX, var1.blockY, var1.blockZ, Blocks.farmland);
				if(user instanceof EntityPlayer) {
					((EntityPlayer) user).addStat(AchievementsAndrew.cactusHoe, 1);
				}
			}
			else if(doHoe&&thing.equals(Blocks.farmland)) {
				if(Math.random()<0.3) {
					Blocks.cactus.dropBlockAsItem(worldObj, var1.blockX, var1.blockY+1, var1.blockZ, 0, 1);
				}
			}
			else {
				int meta = worldObj.getBlockMetadata(var1.blockX, var1.blockY, var1.blockZ);
				worldObj.setBlockToAir(var1.blockX, var1.blockY, var1.blockZ);
				if(!silkTouch) {
					if((thing.equals(BlocksAndrew.cactusOre))&&Math.random()<0.6&&user instanceof EntityPlayer) ((EntityPlayer)this.user).inventory.addItemStackToInventory(new ItemStack(Blocks.cactus)); 
					thing.dropBlockAsItem(worldObj, var1.blockX, var1.blockY, var1.blockZ, meta, fortune);
				}
				else {
					this.dropItem(new ItemStack(thing).getItem(), 1);
				}
			}
		}
		this.setDead();
	}


}
