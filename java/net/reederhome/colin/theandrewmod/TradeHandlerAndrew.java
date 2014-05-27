package net.reederhome.colin.theandrewmod;

import java.util.Random;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;

public class TradeHandlerAndrew implements IVillageTradeHandler {

	@Override
	public void manipulateTradesForVillager(EntityVillager villager,
			MerchantRecipeList recipeList, Random random) {
		recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, random.nextInt(8)), TheAndrewMod.butterDust));
		recipeList.add(new MerchantRecipe(new ItemStack(Blocks.cactus, random.nextInt(32)), new ItemStack(Items.emerald, random.nextInt(16))));
		recipeList.add(new MerchantRecipe(new ItemStack(Items.dye, random.nextInt(64), 4), new ItemStack(Items.spawn_egg, random.nextInt(16), TheAndrewMod.teidThomas)));
		recipeList.add(new MerchantRecipe(new ItemStack(Items.dye, random.nextInt(64), 7), new ItemStack(Items.spawn_egg, random.nextInt(16), TheAndrewMod.teidJack)));
		recipeList.add(new MerchantRecipe(new ItemStack(TheAndrewMod.potatoLiver), Items.emerald));
		recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, random.nextInt(32)), TheAndrewMod.cactusGun));
	}
	
}