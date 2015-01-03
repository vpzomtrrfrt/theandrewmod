package net.reederhome.colin.theandrewmod.entity;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.dispenser.IPosition;
import net.minecraft.dispenser.PositionImpl;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.DungeonHooks;
import net.reederhome.colin.theandrewmod.block.BlockDyedCake;
import net.reederhome.colin.theandrewmod.block.BlocksAndrew;
import net.reederhome.colin.theandrewmod.command.RadiationCommand;
import net.reederhome.colin.theandrewmod.item.ItemsAndrew;
import net.reederhome.colin.theandrewmod.tileentity.TileEntityTNTChest;

public class EntityLuckEgg extends EntityEgg {
	
	public EntityLuckEgg(World p_i1780_1_) {
		super(p_i1780_1_);
	}
	
	public EntityLuckEgg(World p_i1780_1_, EntityLivingBase ent) {
		super(p_i1780_1_, ent);
	}
	
	public void onImpact(MovingObjectPosition mop) {
		if(mop.entityHit!=null) {
			mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), Math.random()<0.2?rand.nextInt(2):0);
		}
		LuckEggAction.run(this);
		if(!worldObj.isRemote) {
			this.setDead();
		}
		worldObj.spawnParticle("snowballpoof", posX, posY, posZ, 0, 0, 0);
	}
	
	public static void initActions() {
		new LuckEggAction() {
			public void run(World w, double x, double y, double z, EntityLuckEgg e) {
				EntityChicken var4 = Math.random()<0.2?new EntityCharlie(w):new EntityChicken(w);
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
				ChunkPosition c = w.findClosestStructure("Stronghold", (int)x, (int)y, (int)z);
				if(c!=null&&Math.random()<0.1) {
					System.out.println(c.chunkPosX+","+c.chunkPosZ);
					int dx = c.chunkPosX;
					int dz = c.chunkPosY;
					int dy = (int)y;
					int trials = 0;
					while(trials<255) {
						if(w.isAirBlock(dx, dy-1, dz)) {
							dy--;
						}
						else if(!w.isAirBlock(dx, dy+1, dz)) {
							dy++;
						}
						else {
							break;
						}
					}
					if(trials<255) {
						if(e.getThrower()!=null) {
							e.getThrower().setPositionAndUpdate(dx, dy, dz);
						}
					}
				}
				else if(e.getThrower() instanceof EntityPlayer) {
					((EntityPlayer)e.getThrower()).addChatMessage(new ChatComponentText("Nothing happened?"));
				}
			}
		};
		new LuckEggAction() {
			public void run(World w, double x, double y, double z, EntityLuckEgg e) {
				e.dropItem(ItemsAndrew.multiplier, 1);
			}
		};
		new LuckEggAction() {
			public void run(World w, double x, double y, double z, EntityLuckEgg e) {
				EntityZombie b = new EntityZombie(w);
				b.setLocationAndAngles(x, y, z, 0, 0);
				b.setCurrentItemOrArmor(4, new ItemStack(Items.diamond_helmet));
				b.setCurrentItemOrArmor(1, new ItemStack(ItemsAndrew.networkBoots));
				b.setCanPickUpLoot(true);
				w.spawnEntityInWorld(b);
			}
		};
		new LuckEggAction() {
			public void run(World w, double x, double y, double z, EntityLuckEgg e) {
				w.createExplosion(e, x, y, z, 2, false);
			}
		};
		new LuckEggAction() {
			public void run(World w, double x, double y, double z, EntityLuckEgg e) {
				boolean t = Math.random()<0.2;
				IInventory inv;
				if(!t) {
					inv = new TileEntityChest();
				}
				else {
					inv = new TileEntityTNTChest();
				}
				WeightedRandomChestContent.generateChestContents(new Random(), ChestGenHooks.getItems(ChestGenHooks.DUNGEON_CHEST, new Random()), inv, ChestGenHooks.getCount(ChestGenHooks.DUNGEON_CHEST, new Random()));
				w.setBlock((int)x, (int)y, (int)z, t?BlocksAndrew.tntChest:Blocks.chest);
				w.setTileEntity((int)x, (int)y, (int)z, (TileEntity)inv);
			}
		};
		new LuckEggAction() {
			public void run(World w, double x, double y, double z, EntityLuckEgg e) {
				Entity d = EntityList.createEntityByName(DungeonHooks.getRandomDungeonMob(new Random()), w);
				d.setLocationAndAngles(x, y, z, 0, 0);
				if(d instanceof EntityLiving) {
					((EntityLiving) d).onSpawnWithEgg(null);
				}
				w.spawnEntityInWorld(d);
			}
		};
		new LuckEggAction() {
			public void run(World w, double x, double y, double z, EntityLuckEgg e) {
				w.setBlock((int)x, (int)y, (int)z, BlockDyedCake.listDyedCake[new Random().nextInt(16)]);
			}
		};
		new LuckEggAction() {
			public void run(World w, double x, double y, double z, EntityLuckEgg e) {
				int n = new Random().nextInt(7)+2;
				for(int i = 0; i < n; i++) {
					IPosition var4 = new PositionImpl(x, y, z);
					EnumFacing var5 = EnumFacing.UP;
					EntityLuckEgg var6 = new EntityLuckEgg(w, e.getThrower());
					var6.setLocationAndAngles(x, y, z, 0, 0);
					var6.setThrowableHeading((double)var5.getFrontOffsetX(), (double)((float)var5.getFrontOffsetY() + 0.1F), (double)var5.getFrontOffsetZ(), 1.1f, 6);
					w.spawnEntityInWorld(var6);
				}
			}
		};
		new LuckEggAction() {
			public void run(World w, double x, double y, double z, EntityLuckEgg e) {
				w.setBlock((int)x, (int)Math.min(255, y+5), (int)z, Blocks.flowing_water);
				w.setBlockMetadataWithNotify((int)x, (int)Math.min(255, y+5), (int)z, 0, 7);
			}
		};
		new LuckEggAction() {
			public void run(World w, double x, double y, double z, EntityLuckEgg e) {
				w.setBlock((int)x, (int)Math.min(255, y+15), (int)z, Blocks.anvil);
				w.setBlockMetadataWithNotify((int)x, (int)Math.min(y+15,255), (int)z, 0, 7);
			}
		};
		new LuckEggAction() {
			public void run(World w, double x, double y, double z, EntityLuckEgg e) {
				w.setBlock((int)x, (int)y, (int)z, Blocks.glowstone);
			}
		};
		new LuckEggAction() {
			public void run(World w, double x, double y, double z, EntityLuckEgg e) {
				EntityLiving b = new EntityPigZombie(w);
				b.setLocationAndAngles(x, y, z, 0, 0);
				if(Math.random()<0.1) {
					b.setHealth(0);
				}
				w.spawnEntityInWorld(b);
			}
		};
		new LuckEggAction() {
			public void run(World w, double x, double y, double z, EntityLuckEgg e) {
				w.setBlock((int)x, (int)y, (int)z, Blocks.obsidian);
			}
		};
		new LuckEggAction() {
			public void run(World w, double x, double y, double z, EntityLuckEgg e) {
				w.setBlock((int)x, (int)y, (int)z, Blocks.fire);
			}
		};
		new LuckEggAction() {
			public void run(World w, double x, double y, double z, EntityLuckEgg e) {
				e.dropItem(Items.skull, 1).getEntityItem().setItemDamage(1);
			}
		};
		new LuckEggAction() {
			public void run(World w, double x, double y, double z, EntityLuckEgg e) {
				if(e.getThrower()!=null) {
					if(e.getThrower() instanceof EntityPlayerMP) {
						EntityPlayerMP p = (EntityPlayerMP) e.getThrower();
						p.setPositionAndUpdate(x, y, z);
						p.fallDistance=3;
					}
					//e.getThrower().setPosition(x, y, z);
				}
			}
			public void dcs(World w, double x, double y, double z, EntityLuckEgg e) {
				for (int var2 = 0; var2 < 32; ++var2)
		        {
		            w.spawnParticle("portal", x, y + e.rand.nextDouble() * 2.0D, z, e.rand.nextGaussian(), 0.0D, e.rand.nextGaussian());
		        }
			}
		};
		new LuckEggAction() {
			public void run(World w, double x, double y, double z, EntityLuckEgg e) {
				w.setBlock((int)x, (int)y, (int)z, Blocks.dirt);
			}
		};
		new LuckEggAction() {
			public void run(World w, double x, double y, double z, EntityLuckEgg e) {
				RadiationCommand.doBomb(w, new ChunkCoordinates((int)x, (int)y, (int)z), e);
			}
		};
		new LuckEggAction() {
			public void run(World w, double x, double y, double z, EntityLuckEgg e) {
				e.dropItem(Items.gold_ingot, 1);
			}
		};
		/*
		new LuckEggAction() {
			public void run(World w, double x, double y, double z, EntityLuckEgg e) {
				
			}
		};
		 */
	}

	public static abstract class LuckEggAction {
		
		public LuckEggAction() {
			listActions.add(this);
		}
		
		private static ArrayList<LuckEggAction> listActions = new ArrayList<LuckEggAction>();
		
		public abstract void run(World w, double x, double y, double z, EntityLuckEgg e);
		public void dcs(World w, double x, double y, double z, EntityLuckEgg e) {}
		
		public static void run(EntityLuckEgg e) {
			LuckEggAction a = listActions.get(e.rand.nextInt(listActions.size()));
			if(e.worldObj.isRemote) {
				a.dcs(e.worldObj, e.posX, e.posY, e.posZ, e);
			}
			else {
				a.run(e.worldObj, e.posX, e.posY, e.posZ, e);
			}
		}
	}
}