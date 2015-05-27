package net.reederhome.colin.theandrewmod.item;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

public class ItemWallumGrinding extends Item {

	public ItemWallumGrinding() {
		super();
		setUnlocalizedName("wallumGrinding");
		setMaxDamage(4200);
		setMaxStackSize(1);
		setTextureName(TheAndrewMod.MODID+":wallumGrinding");
		setFull3D();
		setCreativeTab(TheAndrewMod.tabAndrew);
	}
	
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.eat;
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		return par1ItemStack;
	}
	
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 64;
    }
	
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		double x = par3EntityPlayer.posX;
		double y = par3EntityPlayer.posY;
		double z = par3EntityPlayer.posZ;
		double r = 7;
		List<Entity> l = par2World.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(x-r, y-r, z-r, x+r, y+r, z+r));
		Iterator<Entity> i = l.iterator();
		while(i.hasNext()) {
			Entity en = i.next();
			if(en instanceof EntityItem) {
				EntityItem it = (EntityItem)en;
				ItemStack stack = it.getEntityItem();
				if(par1ItemStack.getItemDamage()<=this.getMaxDamage()-200*stack.stackSize||par3EntityPlayer.capabilities.isCreativeMode) {
					ItemStack res = null;
					Set<String> keys = ItemWallumDust.listWallumDust.keySet();
					Iterator<String> sit = keys.iterator();
					while(sit.hasNext()) {
						String key = sit.next();
						//System.out.println("=="+key);
						ArrayList<ItemStack> ores = OreDictionary.getOres("ore"+key);
						ArrayList<ItemStack> ingots = new ArrayList<ItemStack>();
						for(int o = 0; o < ores.size(); o++) {
							ItemStack ingot = FurnaceRecipes.smelting().getSmeltingResult(ores.get(o));
							if(ingot != null) {
								ingots.add(ingot);
							}
							//System.out.println(ores.get(o));
							if(ores.get(o).getItem().equals(stack.getItem())) {
								res = ItemWallumDust.listWallumDust.get(key).copy();
							}
						}
						for(int o = 0; o < ingots.size(); o++) {
							if(ingots.get(o).getItem().equals(stack.getItem())) {
								res = ItemWallumDust.listWallumDust.get(key).copy();
							}
						}
					}
					if(res==null) {
						if(OreDictionary.itemMatches(new ItemStack(Blocks.redstone_ore), stack, false)) {
							res = new ItemStack(Items.redstone, new Random().nextInt(8));
						}
						else if(OreDictionary.itemMatches(new ItemStack(Blocks.cobblestone), stack, false)) {
							res = new ItemStack(Blocks.sand, 1);
						}
					}
					if(res!=null) {
						ItemStack tm = res.copy();
						tm.stackSize*=stack.stackSize;
						it.setEntityItemStack(tm);
						par1ItemStack.damageItem(20*stack.stackSize, par3EntityPlayer);
					}
				}
			}
			else {
				if(par1ItemStack.getItemDamage()<this.getMaxDamage()-50&&!en.equals(par3EntityPlayer)) {
					en.attackEntityFrom(TheAndrewMod.deathByGrindingWallum, 10);
					par1ItemStack.damageItem(50, par3EntityPlayer);
				}
			}
		}
		return par1ItemStack;
	}
}