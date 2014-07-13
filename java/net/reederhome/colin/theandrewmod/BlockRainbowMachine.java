package net.reederhome.colin.theandrewmod;

import java.awt.Color;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockRainbowMachine extends Block {

	IIcon sideIcon;
	static int nc;
	public BlockRainbowMachine() {
		super(Material.cloth);
		setBlockName("rainbowMachine");
		setCreativeTab(TheAndrewMod.tabAndrew);
	}
	
	public IIcon getIcon(int side, int meta) {
		return side==meta?this.blockIcon:(side==Facing.oppositeSide[meta]?Blocks.dispenser.getIcon(0, 2):sideIcon);
	}
	
	public void registerBlockIcons(IIconRegister ir) {
		blockIcon=ir.registerIcon(TheAndrewMod.MODID+":rainbowMachineFront");
		sideIcon =ir.registerIcon(TheAndrewMod.MODID+":rainbowMachineSide");
	}
	
	public int getRenderType()
    {
        return 16;
    }

	
	public void onBlockPlacedBy(World world, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
		world.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, Facing.oppositeSide[BlockPistonBase.determineOrientation(world, p_149689_2_, p_149689_3_, p_149689_4_, p_149689_5_)], 3);
	}
	
	public void onBlockAdded(World world, int x, int y, int z) {
		updateTick(world, x, y, z, world.rand);
	}
	
	public void updateTick(World world, int x, int y, int z, Random r) {
		int m = world.getBlockMetadata(x, y, z);
		int bx = x+Facing.offsetsXForSide[m];
		int by = y+Facing.offsetsYForSide[m];
		int bz = z+Facing.offsetsZForSide[m];
		Block b = world.getBlock(bx, by, bz);
		nc--;
		if(nc<0) {
			nc=15;
		}
		if(b.equals(Blocks.wool)||b.equals(Blocks.stained_hardened_clay)||b.equals(Blocks.stained_glass)||b.equals(Blocks.stained_glass_pane)||b.equals(Blocks.carpet)||b.equals(TheAndrewMod.dyedCactus)) {
			world.setBlockMetadataWithNotify(bx, by, bz, nc, 3);
		}
		else if(b instanceof BlockDyedCake) {
			int meta = world.getBlockMetadata(bx, by, bz);
			world.setBlock(bx, by, bz, BlockDyedCake.listDyedCake[nc]);
			world.setBlockMetadataWithNotify(bx, by, bz, meta, 3);
		}
		else if(b.getMaterial().equals(Material.air)) {
			List<Entity> el = world.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(bx, by, bz, bx+1, by+1, bz+1));
			Iterator<Entity> i = el.iterator();
			while(i.hasNext()) {
				Entity e = i.next();
				if(e instanceof EntitySheep) {
					((EntitySheep)e).setFleeceColor(nc);
				}
				else if(e instanceof EntityCharlie) {
					((EntityCharlie)e).getDataWatcher().updateObject(16, nc);
				}
				else if(e instanceof EntityWolf) {
					EntityWolf wolf = (EntityWolf)e;
					if(wolf.isTamed()) {
						wolf.setCollarColor(nc);
					}
				}
				else if(e instanceof EntityLivingBase) {
					for(int s = 1; s < 5; s++) {
						ItemStack stack = ((EntityLivingBase)e).getEquipmentInSlot(s);
						if(stack!=null) {
							if(stack.getItem() instanceof ItemArmor) {
								ItemArmor ita = (ItemArmor) stack.getItem();
								if(ita.getArmorMaterial().equals(ItemArmor.ArmorMaterial.CLOTH)) {
									stack.getTagCompound().getCompoundTag("display").setInteger("color", ItemDye.field_150922_c[nc]);
								}
							}
						}
					}
				}
			}
		}
		world.scheduleBlockUpdate(x, y, z, this, 2);
	}
	
	public int colorcode(Color clr) {
		return (clr.getRed()<<16)+(clr.getGreen()<<8)+(clr.getBlue());
	}

}
