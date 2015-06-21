package net.reederhome.colin.theandrewmod.entity;


import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.TheAndrewMod;
import net.reederhome.colin.theandrewmod.block.BlocksAndrew;

public class EntityThomas extends EntityTameable implements IRangedAttackMob {

	
	public EntityThomas(World par1World) {
		super(par1World);
		this.tasks.addTask(0, new EntityAIMoveRandomly(this));
		this.tasks.addTask(1, new EntityAIArrowAttack(this, 1.0D, 20, 60, 15.0F));
		this.tasks.addTask(2, new EntityAIRideHorses(this));
		this.tasks.addTask(3, new EntityAIFollowOwner(this, 1, 10, 2));
		this.tasks.addTask(4, new EntityAIMoveTowardsTarget(this, 0.9, 32));
		this.tasks.addTask(5, this.aiSit);
		this.tasks.addTask(6, new EntityAISwimming(this));
		this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityJack.class, 2, true));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityTrevor.class, 2, false));
		this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		this.targetTasks.addTask(3, new EntityAIOwnerHurtByTarget(this));
		this.targetTasks.addTask(4, new EntityAIHurtByTarget(this, false));
		this.setCurrentItemOrArmor(0, new ItemStack(Items.lava_bucket));
		this.isImmuneToFire=true;
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0);
		this.setHealth(40);
		this.getDataWatcher().addObject(15, new Byte((byte)0));
		this.getDataWatcher().addObject(14, new Byte((byte)0));
		this.getDataWatcher().addObject(13, new Byte((byte)0));
		setSize(0.6f, 1.8f);
	}
	
	public float getSoundVolume() {
		return 0.4f;
	}
	
	public boolean isAIEnabled() {
		return true;
	}
	public Item getDropItem() {
		return new ItemStack(BlocksAndrew.invasivePlant).getItem();
	}
	public void onLivingUpdate() {
		super.onLivingUpdate();
		/*if(this.getNavigator().noPath()) {
			Vec3 target = RandomPositionGenerator.findRandomTarget(this, 10, 1);
			this.getNavigator().tryMoveToXYZ(target.xCoord, target.yCoord, target.zCoord, 16);
		}*/
		/*if(this.getAttackTarget()==null) {
			List<EntityJack> l = worldObj.getEntitiesWithinAABB(EntityJack.class, AxisAlignedBB.getBoundingBox(posX-5,posY-5,posZ-5,posX+5,posY+5,posZ+5));
			if(l.size()>0) {this.setAttackTarget(l.get(0));}
		}
		else if(this.getAttackTarget().isDead) {
			this.setAttackTarget(null);
		}*/
		if(this.worldObj.isRemote) return;
		if(Math.random()>0.03) return;
		Vec3 g = RandomPositionGenerator.findRandomTarget(this, 2, 3);
		//System.out.println(g);
		if(this.worldObj.isAirBlock((int)g.xCoord, (int)g.yCoord, (int)g.zCoord)&&this.getDataWatcher().getWatchableObjectByte(13)==0) {
			this.worldObj.setBlock((int)g.xCoord, (int)g.yCoord, (int)g.zCoord, Blocks.lava);
		}
		if(Math.random()<0.2&&this.isEnderThomas()) {
			int maxDist = 10;
			double newX = posX+(Math.random()-0.5)*maxDist;
			double newZ = posZ+(Math.random()-0.5)*maxDist;
			double newY = posY;
			while(this.worldObj.getBlock((int)newX, (int)newY, (int)newZ).getMaterial().blocksMovement()) {
				posY++;
				if(posY>256) {
					return;
				}
			}
			this.setLocationAndAngles(newX, newY, newZ, rotationPitch, rotationYaw);
		}
		if(Math.random()<0.00001) setChild(false);
	}
	public void doJump() {
		if(this.onGround) {jump();}
	}
	protected String getLivingSound() {
		return TheAndrewMod.MODID+":mob.thomas.say";
	}
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		setChild(tag.getBoolean("Baby"));
		this.getDataWatcher().updateObject(14, (byte)(tag.getBoolean("Ender")?1:0));
		this.getDataWatcher().updateObject(13, (byte)(tag.getBoolean("Harmless")?1:0));
	}
	public void writeEntityToNBT(NBTTagCompound tag) {
		super.writeEntityToNBT(tag);
		tag.setBoolean("Baby", isChild());
		tag.setBoolean("Ender", isEnderThomas());
		tag.setBoolean("Harmless", this.getDataWatcher().getWatchableObjectByte(13)==1);
	}
	public boolean isChild() {
		return this.getDataWatcher().getWatchableObjectByte(15)==1;
	}
	public boolean interact(EntityPlayer p) {
		if(super.interact(p)) return true;
		ItemStack s = p.getCurrentEquippedItem();
		if((!isChild())&&s!=null&&s.getItem().equals(Items.shears)) {
			if(!worldObj.isRemote) {
				createChild(this);
			}
			else {
				for(int i = 0; i < this.rand.nextInt(16); i++) {
					this.worldObj.spawnParticle("heart", this.posX, posY, posZ, this.rand.nextFloat(), this.rand.nextFloat(), this.rand.nextFloat());
				}
			}
			s.damageItem(1, p);
			return true;
		}
		else if(isChild()&&s!=null&s.getItem().equals(Items.dye)) {
			setChild(false);
			s.stackSize--;
		}
		else if(s!=null&&!s.getItem().equals(Item.getItemFromBlock(Blocks.cobblestone))&&!s.getItem().equals(Item.getItemFromBlock(Blocks.dirt))) {
			if(!p.capabilities.isCreativeMode) {
				s.stackSize--;
			}
			if((Math.random()<0.1||p.capabilities.isCreativeMode)) {
				this.func_152115_b(p.getCommandSenderName());
				setTamed(true);
				this.getDataWatcher().updateObject(13, (byte)1);
				playTameEffect(true);
			}
			else {
				playTameEffect(false);
			}
		}
		else if(s==null&&p.getCommandSenderName().equals(this.func_152113_b())) {
			if(p.isSneaking()) {
				if(this.getEquipmentInSlot(4)==null) {
					ItemStack head = new ItemStack(Items.skull, 1, 3);
					NBTTagCompound tag = new NBTTagCompound();
					tag.setString("SkullOwner", p.getCommandSenderName());
					head.setTagCompound(tag);
					this.setCurrentItemOrArmor(4, head);
				}
				else {
					this.setCurrentItemOrArmor(4, null);
				}
			}
		}
		return false;
	}
	public void setChild(boolean child) {
		this.getDataWatcher().updateObject(15, Byte.valueOf((byte)(child?1:0)));
	}
	public boolean isEnderThomas() {
		return this.getDataWatcher().getWatchableObjectByte(14)==1;
	}
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase var1, float var2) {
		EntityArrow arrow = new EntityArrow(worldObj, this, var1, 1.6f, 14f);
		arrow.setFire(1000);
		worldObj.spawnEntityInWorld(arrow);
	}
	@Override
	public EntityAgeable createChild(EntityAgeable arg0) {
		EntityThomas baby = new EntityThomas(arg0.worldObj);
		baby.setChild(true);
		if(Math.random()<0.001) baby.setChild(true);
		baby.setLocationAndAngles(arg0.posX, arg0.posY, arg0.posZ, arg0.rotationPitch, arg0.rotationYaw);
		arg0.worldObj.spawnEntityInWorld(baby);
		return baby;
	}
}
