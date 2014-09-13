package net.reederhome.colin.theandrewmod.block;

import net.minecraft.block.BlockCake;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDye;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.reederhome.colin.theandrewmod.TheAndrewMod;
import net.reederhome.colin.theandrewmod.entity.EntityCharlie;

public class BlockDyedCake extends BlockCake {
	public static BlockDyedCake[] listDyedCake = new BlockDyedCake[16];
	static Potion[] cakePotions = null;
	static int[] cakeFood = null;
	private int clr;
	
	public int getColor() {
		return clr;
	}
	public BlockDyedCake(int clr) {
		this.clr=clr;
		listDyedCake[clr]=this;
		setBlockTextureName(TheAndrewMod.MODID+":dyed_cake_"+ItemDye.field_150921_b[clr]);
		if(cakePotions==null) {
			cakePotions = new Potion[16];
			cakePotions[0] = Potion.wither;
			cakePotions[1] = Potion.heal;
			cakePotions[2] = Potion.poison;
			cakePotions[3] = Potion.resistance;
			cakePotions[4] = Potion.waterBreathing;
			cakePotions[5] = Potion.confusion;
			cakePotions[6] = Potion.moveSpeed;
			cakePotions[7] = Potion.invisibility;
			cakePotions[8] = Potion.blindness;
			cakePotions[9] = Potion.regeneration;
			cakePotions[10]= Potion.jump;
			cakePotions[11]= Potion.digSpeed;
			cakePotions[12]= Potion.nightVision;
			cakePotions[14]= Potion.fireResistance;
			
			cakeFood = new int[16];
			cakeFood[0] = 7;
			cakeFood[2] = 4;
			cakeFood[3] = 1;
			cakeFood[5] = 4;
			cakeFood[7] = 1;
			cakeFood[8] = 4;
			cakeFood[12]= 1;
			cakeFood[14]= 1;
			cakeFood[15]=-2;
		}
		setHardness(10);
	}
	
	public boolean doEatCake(World world, int x, int y, int z, EntityPlayer player) {
		if (player.canEat(false)||cakeFood[clr]<0)
        {
            player.getFoodStats().addStats(cakeFood[clr]==0?2:cakeFood[clr], 0.1F);
            int var6 = world.getBlockMetadata(x, y, z) + 1;

            if (var6 >= 6)
            {
                world.setBlockToAir(x, y, z);
            }
            else
            {
                world.setBlockMetadataWithNotify(x, y, z, var6, 2);
            }
            applyPotion(player);
            if(clr==13&&Math.random()<0.2) {
            	EntityCharlie mob = new EntityCharlie(world);
            	mob.setLocationAndAngles(x, y+2, z, 0, 0);
            	world.spawnEntityInWorld(mob);
            }
            return true;
        }
		return false;
	}
	
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
		doEatCake(world, x, y, z, player);
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		return doEatCake(world, x, y, z, player);
	}
	
	public void applyPotion(EntityPlayer player) {
		if(cakePotions[clr]!=null) {
			player.addPotionEffect(new PotionEffect(cakePotions[clr].id, cakeFood[clr]==1?1200:180, 1));
		}
	}
}
