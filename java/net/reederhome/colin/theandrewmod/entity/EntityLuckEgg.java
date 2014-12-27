package net.reederhome.colin.theandrewmod.entity;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.item.ItemsAndrew;

public class EntityLuckEgg extends EntityEgg {
	
	public EntityLuckEgg(World p_i1780_1_) {
		super(p_i1780_1_);
	}
	
	public EntityLuckEgg(World p_i1780_1_, EntityLivingBase ent) {
		super(p_i1780_1_, ent);
	}
	
	public void onImpact(MovingObjectPosition mop) {
		if(!worldObj.isRemote) {
			LuckEggAction.run(this);
			this.setDead();
		}
		worldObj.spawnParticle("snowballpoof", posX, posY, posZ, 0, 0, 0);
	}
	
	public static void initActions() {
		/*
		new LuckEggAction() {
			public void run(World w, double x, double y, double z, EntityLuckEgg e) {
				
			}
		};
		 */
		new LuckEggAction() {
			public void run(World w, double x, double y, double z, EntityLuckEgg e) {
				EntityChicken var4 = new EntityChicken(w);
                var4.setGrowingAge(-24000);
                var4.setLocationAndAngles(x, y, z, 0f, 0f);
                w.spawnEntityInWorld(var4);
			}
		};
		new LuckEggAction() {
			public void run(World w, double x, double y, double z, EntityLuckEgg e) {
				e.dropItem(ItemsAndrew.luckEgg, Math.random()<0.1?2:1);
			}
		};
		new LuckEggAction() {
			public void run(World w, double x, double y, double z, EntityLuckEgg e) {
				w.setBlock((int)x, (int)y, (int)z, Blocks.bookshelf);
			}
		};
		new LuckEggAction() {
			public void run(World w, double x, double y, double z, EntityLuckEgg e) {
				Entity t = new EntityTrevor(w);
				t.setLocationAndAngles(x, y, z, 0, 0);
				w.spawnEntityInWorld(t);
			}
		};
		new LuckEggAction() {
			public void run(World w, double x, double y, double z, EntityLuckEgg e) {
				Entity t = new EntityTNTPrimed(w, x, y, z, null);
				w.spawnEntityInWorld(t);
			}
		};
		new LuckEggAction() {
			public void run(World w, double x, double y, double z, EntityLuckEgg e) {
				w.addWeatherEffect(new EntityLightningBolt(w, x, Math.min(255,y), z));
			}
		};
		new LuckEggAction() {
			public void run(World w, double x, double y, double z, EntityLuckEgg e) {
				Entity h = new EntityHPCreeper(w);
				h.setLocationAndAngles(x, y, z, 0, 0);
				w.spawnEntityInWorld(h);
			}
		};
		new LuckEggAction() {
			public void run(World w, double x, double y, double z, EntityLuckEgg e) {
				int n = new Random().nextInt(20)+2;
				for(int i = 0; i < n; i++) {
					EntityOcelot h = new EntityOcelot(w);
					if(e.getThrower() instanceof EntityPlayer) {
						NBTTagCompound tag = new NBTTagCompound();
						tag.setString("Owner", e.getThrower().getCommandSenderName());
						h.readFromNBT(tag);
						h.setTameSkin(1+new Random().nextInt(3));
					}
					h.setLocationAndAngles(x, y, z, 0, 0);
					w.spawnEntityInWorld(h);
				}
			}
		};
		new LuckEggAction() {
			public void run(World w, double x, double y, double z, EntityLuckEgg e) {
				if(e.getThrower() instanceof EntityPlayer) {
					((EntityPlayer)e.getThrower()).addChatMessage(new ChatComponentText("Nothing happened?"));;
				}
			}
		};
		new LuckEggAction() {
			public void run(World w, double x, double y, double z, EntityLuckEgg e) {
				e.dropItem(ItemsAndrew.bulbasauce, 1);
			}
		};
	}
	
	public static abstract class LuckEggAction {
		
		public LuckEggAction() {
			listActions.add(this);
		}
		
		private static ArrayList<LuckEggAction> listActions = new ArrayList<LuckEggAction>();
		
		public abstract void run(World w, double x, double y, double z, EntityLuckEgg e);
		
		public static void run(EntityLuckEgg e) {
			listActions.get(e.rand.nextInt(listActions.size())).run(e.worldObj, e.posX, e.posY, e.posZ, e);
		}
	}
}