package highlands;

import highlands.api.HighlandsBlocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class HighlandsFuelHandler implements IFuelHandler
{
	public int getBurnTime(ItemStack fuel) {
		
		Item item = fuel.getItem();
		
		//TODO- all this ItemStack stuff makes me worry...
		if(fuel == new ItemStack(HighlandsBlocks.firWood))return 300;
		if(fuel == new ItemStack(HighlandsBlocks.acaciaWood))return 300;
		if(fuel == new ItemStack(HighlandsBlocks.redwoodWood))return 300;
		if(fuel == new ItemStack(HighlandsBlocks.canopyWood))return 300;
		if(fuel == new ItemStack(HighlandsBlocks.poplarWood))return 300;
		if(fuel == new ItemStack(HighlandsBlocks.mangroveWood))return 300;
		if(fuel == new ItemStack(HighlandsBlocks.ashWood))return 300;
		if(fuel == new ItemStack(HighlandsBlocks.palmWood))return 300;
		if(fuel == new ItemStack(HighlandsBlocks.ironWood))return 300;
		
		if(fuel == new ItemStack(HighlandsBlocks.hlplanks))return 300;
		if(fuel == new ItemStack(HighlandsBlocks.hlplankstairs0))return 300;
		if(fuel == new ItemStack(HighlandsBlocks.hlplankstairs1))return 300;
		if(fuel == new ItemStack(HighlandsBlocks.hlplankstairs2))return 300;
		if(fuel == new ItemStack(HighlandsBlocks.hlplankstairs3))return 300;
		if(fuel == new ItemStack(HighlandsBlocks.hlplankhalf))return 150;
		
		if(fuel == new ItemStack(HighlandsBlocks.firSapling))return 100;
	    if(fuel == new ItemStack(HighlandsBlocks.acaciaSapling))return 100;
	    if(fuel == new ItemStack(HighlandsBlocks.redwoodSapling))return 100;
	    if(fuel == new ItemStack(HighlandsBlocks.poplarSapling))return 100;
	    if(fuel == new ItemStack(HighlandsBlocks.canopySapling))return 100;
	    if(fuel == new ItemStack(HighlandsBlocks.greatOakSapling))return 100;
	    if(fuel == new ItemStack(HighlandsBlocks.beechSapling))return 100;
	    if(fuel == new ItemStack(HighlandsBlocks.deadSapling))return 100;
	    if(fuel == new ItemStack(HighlandsBlocks.evergreenBushSapling))return 100;
	    if(fuel == new ItemStack(HighlandsBlocks.deciduousBushSapling))return 100;
	    if(fuel == new ItemStack(HighlandsBlocks.palmSapling))return 100;
	    if(fuel == new ItemStack(HighlandsBlocks.ironwoodSapling))return 100;
	    if(fuel == new ItemStack(HighlandsBlocks.mangroveSapling))return 100;
	    if(fuel == new ItemStack(HighlandsBlocks.ashSapling))return 100;
	    if(fuel == new ItemStack(HighlandsBlocks.autumnYellowSapling))return 100;
	    if(fuel == new ItemStack(HighlandsBlocks.autumnOrangeSapling))return 100;
		
		
		return 0;
	}
}