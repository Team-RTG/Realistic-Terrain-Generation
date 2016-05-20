package highlands.block;

import highlands.api.HighlandsBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.ItemSlab;

public class ItemSlabPlanks extends ItemSlab
{
    public ItemSlabPlanks(Block block)
    {
        super(block, (BlockSlab)HighlandsBlocks.hlplankhalf, (BlockSlab)HighlandsBlocks.hlplankhalfdouble, false);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }
}
