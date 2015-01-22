package net.reederhome.colin.theandrewmod.item;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

public class ItemIceBucket extends Item {
	
	public ItemIceBucket() {
		super();
		setMaxStackSize(1);
		setCreativeTab(TheAndrewMod.tabAndrew);
		setUnlocalizedName("iceBucket");
		setTextureName(TheAndrewMod.MODID+":ice_bucket");
	}

	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer p, World w, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		if(w.isRemote) return true;
		Block var11 = w.getBlock(par4, par5, par6);
	    par4 += Facing.offsetsXForSide[par7];
	    par5 += Facing.offsetsYForSide[par7];
	    par6 += Facing.offsetsZForSide[par7];
	    double var12 = 0.0D;
	
	    if (par7 == 1 && var11.getRenderType() == 11)
	    {
	        var12 = 0.5D;
	    }
	
	    EntityFallingBlock var14 = new EntityFallingBlock(w, par4+0.5, par5+var12+0.5, par6+0.5, Blocks.ice);
	    NBTTagCompound tag = new NBTTagCompound();
	    var14.writeToNBT(tag);
	    tag.setFloat("FallHurtAmount", 8);
	    var14.readFromNBT(tag);
	    var14.func_145806_a(true);
	    var14.field_145812_b=1;
	    w.spawnEntityInWorld(var14);
	    if(!p.capabilities.isCreativeMode) {
	    	p.inventory.setInventorySlotContents(p.inventory.currentItem,new ItemStack(Items.bucket));
	    }
	
	    return true;
	}
}