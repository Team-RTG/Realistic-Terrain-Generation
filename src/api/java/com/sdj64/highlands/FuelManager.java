package com.sdj64.highlands;

import com.sdj64.highlands.block.BlockHighlandsLog;
import com.sdj64.highlands.block.BlockHighlandsPlanks;
import com.sdj64.highlands.block.BlockHighlandsPlant;
import com.sdj64.highlands.block.BlockHighlandsSapling;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;

public class FuelManager implements IFuelHandler{

	@Override
	public int getBurnTime(ItemStack fuel) {
		Item i = fuel.getItem();
		Block b = Block.getBlockFromItem(i);
		if(b instanceof BlockHighlandsLog || b instanceof BlockHighlandsPlanks){
			return 300;
		}
		if((b instanceof BlockHighlandsSapling || b instanceof BlockHighlandsPlant)){
			return 100;
		}
		return 0;
	}

}
