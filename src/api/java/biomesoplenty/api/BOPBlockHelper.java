package biomesoplenty.api;

import net.minecraft.block.Block;

public class BOPBlockHelper
{
    public static String getUniqueName(Block block)
    {
        return block.getRegistryName();
    }
}
