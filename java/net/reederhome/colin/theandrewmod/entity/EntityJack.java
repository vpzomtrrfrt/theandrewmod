package net.reederhome.colin.theandrewmod.entity;


import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.Achievement;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.AchievementsAndrew;
import net.reederhome.colin.theandrewmod.TheAndrewMod;
import net.reederhome.colin.theandrewmod.block.BlocksAndrew;

public class EntityJack extends EntityCreature implements IRangedAttackMob {

	public EntityJack(World par1World) {
		super(par1World);
		this.tasks.addTask(0, new EntityAIMoveRandomly(this));
		this.tasks.addTask(1, new EntityAIArrowAttack(this, 0.4D, 0, 60, 15.0F));
		this.tasks.addTask(2, new EntityAIRideCharlie(this));
		this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityThomas.class, 2, true));
		this.setCurrentItemOrArmor(0, new ItemStack(Blocks.gravel));
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0);
		this.setHealth(40);
		this.getDataWatcher().addObject(12, (byte)0);
	}
	public boolean isAIEnabled() {
		return true;
	}
	public void onLivingUpdate() {
		super.onLivingUpdate();
		/*if(this.getNavigator().noPath()) {
			Vec3 target = RandomPositionGenerator.findRandomTarget(this, 10, 1);
			this.getNavigator().tryMoveToXYZ(target.xCoord, target.yCoord, target.zCoord, 16);
		}*/
		if(this.worldObj.isRemote) return;
		if(Math.random()>0.05) return;
		List<EntityThomas> th = worldObj.getEntitiesWithinAABB(EntityThomas.class, AxisAlignedBB.getBoundingBox(posX-10, posY-10, posZ-10, posX+10, posY+10, posZ+10));
		if(Math.random()<0.1&&th.size()>0) {
			Vec3 t = RandomPositionGenerator.findRandomTargetBlockTowards(this, 2, 10, Vec3.createVectorHelper(th.get(0).posX, th.get(0).posY, th.get(0).posZ));
			int x = (int)t.xCoord;
			int y = (int)t.yCoord;
			int z = (int)t.zCoord;
			while(true) {
				if(y>-1&&(worldObj.getBlock(x, y, z).equals(Blocks.air)||worldObj.getBlock(x, y-1, z).equals(Blocks.air))) {y--;continue;}
				break;
			}
			worldObj.setBlock(x, y, z, Blocks.air);
			worldObj.setBlock(x, y-1, z, Blocks.air);
			worldObj.setBlock(x, y-2, z, BlocksAndrew.jumpPad);
		}
		Vec3 g = RandomPositionGenerator.findRandomTarget(this, 2, 3);
		//System.out.println(g);
		if(this.worldObj.isAirBlock((int)g.xCoord, (int)g.yCoord, (int)g.zCoord)) {
			this.worldObj.setBlock((int)g.xCoord, (int)g.yCoord, (int)g.zCoord, (th.size()>0&&Math.random()<0.4)?Blocks.tnt:Blocks.gravel);
		}
	}
	public void doJump() {
		if(this.onGround) {jump();}
	}
	protected String getLivingSound() {
		return TheAndrewMod.MODID+":mob.jack.say";
	}
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase var1, float var2) {
		for(int e = 0; e < 4; e++) {
			EntityEgg egg = new EntityEgg(worldObj, this);
			egg.setThrowableHeading(var1.posX-this.posX+Math.random()/4, var1.posY-this.posY+var2, var1.posZ-this.posZ+Math.random()/4, 1.6f, 1.4f);
			worldObj.spawnEntityInWorld(egg);
		}
	}
	public boolean interact(EntityPlayer p) {
		ItemStack s = p.getCurrentEquippedItem();
		if(s==null) return false;
		if((!isChild())&&s!=null&&s.getItem().equals(Items.shears)) {
			if(!worldObj.isRemote) {
				EntityLiving baby;
				if(Math.random()<0.9) {
					baby = new EntityJack(this.worldObj);
					((EntityJack) baby).setChild(true);
				}
				else {
					baby = new EntityCharlie(worldObj);
				}
				baby.setLocationAndAngles(posX, posY, posZ, rotationPitch, rotationYaw);
				this.worldObj.spawnEntityInWorld(baby);
			}
			else {
				for(int i = 0; i < this.rand.nextInt(16); i++) {
					this.worldObj.spawnParticle("heart", this.posX, posY, posZ, this.rand.nextFloat(), this.rand.nextFloat(), this.rand.nextFloat());
				}
			}
			s.damageItem(1, p);
			p.addStat(AchievementsAndrew.shearThem, 1);
			return true;
		}
		else if(isChild()&&s!=null&s.getItem().equals(Items.dye)) {
			setChild(false);
			s.stackSize--;
			return true;
		}
		else if(s.getItem().equals(new ItemStack(Blocks.dirt).getItem())) {
			Entity thing = this;
			while(thing.riddenByEntity!=null) {
				thing=thing.riddenByEntity;
			}
			p.mountEntity(thing);
			return true;
		}
		return false;
	}
	public boolean isChild() {
		return this.getDataWatcher().getWatchableObjectByte(12)==1;
	}
	public void writeEntityToNBT(NBTTagCompound tag) {
		super.writeEntityToNBT(tag);
		tag.setBoolean("Baby", isChild());
	}
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		setChild(tag.getBoolean("Baby"));
	}
	public void setChild(boolean child) {
		this.getDataWatcher().updateObject(12, Byte.valueOf((byte)(child?1:0)));
	}
}
