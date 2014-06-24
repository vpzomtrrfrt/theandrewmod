package net.reederhome.colin.theandrewmod;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class ItemWallumCooking extends Item {

	public ItemWallumCooking() {
		super();
		setUnlocalizedName("wallumCooking");
		setMaxDamage(20000);
		setMaxStackSize(1);
		setTextureName(TheAndrewMod.MODID+":wallumCooking");
		setFull3D();
	}
	
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.eat;
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if(par1ItemStack.getItemDamage()<=this.getMaxDamage()-200) {
			par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		}
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
				if(par1ItemStack.getItemDamage()<=this.getMaxDamage()-200*stack.stackSize) {
					ItemStack res = FurnaceRecipes.smelting().getSmeltingResult(stack);
					if(res!=null) {
						ItemStack tm = res.copy();
						tm.stackSize=stack.stackSize;
						it.setEntityItemStack(tm);
						par1ItemStack.damageItem(200*stack.stackSize, par3EntityPlayer);
					}
				}
			}
			else {
				if(par1ItemStack.getItemDamage()<this.getMaxDamage()-50&&!en.equals(par3EntityPlayer)) {
					en.setFire(50);
					par1ItemStack.damageItem(50, par3EntityPlayer);
				}
			}
		}
		return par1ItemStack;
	}
}
