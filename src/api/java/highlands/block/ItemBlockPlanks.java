package highlands.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

public class ItemBlockPlanks extends ItemBlock
{

    public ItemBlockPlanks(Block par1)
    {
        super(par1);
        setHasSubtypes(true);
    }
    
    @Override
    public int getMetadata(int par1){
    	return par1;
    }
    
	@Override
	public IIcon getIconFromDamage(int meta)
	{
		return field_150939_a.getIcon(0, meta);
	}
	@Override
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        int metadata = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 15);
        if (metadata < 0 || metadata >= BlockHighlandsPlanks.woodType.length)
        {
            metadata = 0;
        }

        return super.getUnlocalizedName() + "." + BlockHighlandsPlanks.woodType[metadata];
    }
}
