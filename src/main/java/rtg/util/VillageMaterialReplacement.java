package rtg.util;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;

/**
 * Created by topisani on 2/24/16.
 */
public class VillageMaterialReplacement {



    private Block defaultBlock;
    private IBlockState replacementBlock;
    private boolean preserveMeta;

    public VillageMaterialReplacement(Block defaultBlock, IBlockState replacementBlock) {
        this.defaultBlock = defaultBlock;
        this.replacementBlock = replacementBlock;
    }

    public VillageMaterialReplacement(Block defaultBlock) {
        this.defaultBlock = defaultBlock;
    }

    public Block getDefault() {
        return defaultBlock;
    }

    public IBlockState getReplacement() {
        return replacementBlock;
    }

    public void setReplacement(IBlockState replacementBlock) {
        this.replacementBlock = replacementBlock;
    }

    public void setReplacement(Block replacementBlock) {
        this.replacementBlock = replacementBlock.getDefaultState();
        this.preserveMeta = true;
    }

    public IBlockState replace(IBlockState oldBlock) {
        if (oldBlock.getBlock() != this.defaultBlock) {
            Logger.debug("VillageMaterialReplacement was asked to replace a block that didnt match it. This should not have happened");
            return oldBlock;
        }
        IBlockState newBlock = this.replacementBlock;
        if (preserveMeta) {
            newBlock = newBlock.getBlock().getStateFromMeta(oldBlock.getBlock().getMetaFromState(oldBlock));
        }
        return newBlock;
    }
}
