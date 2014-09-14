package net.reederhome.colin.theandrewmod.item;

import java.util.HashMap;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.OreDictionary;
import net.reederhome.colin.theandrewmod.TheAndrewMod;

public class ItemWallumDust extends Item {

	public static HashMap<String,ItemStack> listWallumDust = new HashMap<String,ItemStack>();
	IIcon[] icons;
	String[] ores;
	
	public ItemWallumDust(String[] ores) {
		this.ores=ores;
		setMaxDamage(0);
		setHasSubtypes(true);
		setCreativeTab(TheAndrewMod.tabAndrew);
		for(int i = 0; i < ores.length; i++) {
			listWallumDust.put(ores[i], new ItemStack(this, 1, i));
			OreDictionary.registerOre("dust"+ores[i], new ItemStack(this, 1, i));
		}
		setUnlocalizedName("wallumDust");
	}
	
	public void registerIcons(IIconRegister ir) {
		icons=new IIcon[ores.length];
		for(int i = 0; i < ores.length; i++) {
			icons[i]=ir.registerIcon(TheAndrewMod.MODID+":wallumDust"+ores[i]);
		}
	}
	
	public IIcon getIconFromDamage(int meta) {
		return icons[meta];
	}
	
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName(stack)+"."+ores[stack.getItemDamage()];
	}
	
	public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_)
    {
        for (int var4 = 0; var4 < ores.length; ++var4)
        {
            p_150895_3_.add(new ItemStack(p_150895_1_, 1, var4));
        }
    }

}