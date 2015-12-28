package enhancedbiomes.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import enhancedbiomes.blocks.renderer.BlockGrassRenderer;
import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BlockShoji extends BlockPane {
	protected BlockShoji(String p_i45432_1_, String p_i45432_2_, Material p_i45432_3_, boolean p_i45432_4_) {
		super(p_i45432_1_, p_i45432_2_, p_i45432_3_, p_i45432_4_);
	}

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Items.paper;
    }

	@Override
	public int getRenderType() {
		return Blocks.glass_pane.getRenderType();
	}

    /**
     * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
     */
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 1;
    }
}
