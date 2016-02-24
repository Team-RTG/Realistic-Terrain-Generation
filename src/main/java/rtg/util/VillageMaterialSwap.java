package rtg.util;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;

/**
 * The exact materials to be swapped on village generation
 */
public class VillageMaterialSwap {

    private Block defaultBlock;
    private IBlockState replacementBlock = null;
    private boolean preserveMeta;

    /**
     * Might be used for custom swaps later.
     * @param defaultBlock the block to replace with replacementBlock
     * @param replacementBlock the block to replace defaultBlock with
     */
    public VillageMaterialSwap(Block defaultBlock, IBlockState replacementBlock) {
        this.defaultBlock = defaultBlock;
        this.replacementBlock = replacementBlock;
    }

    /**
     * @param defaultBlock the block to replace
     */
    public VillageMaterialSwap(Block defaultBlock) {
        this.defaultBlock = defaultBlock;
    }
    public Block getDefault() {
        return defaultBlock;
    }

    public IBlockState getReplacement() {
        return replacementBlock;
    }

    /**
     * Sets the replacement blockstate
     * @param replacementBlock
     */
    public void setReplacement(IBlockState replacementBlock) {
        this.replacementBlock = replacementBlock;
    }

    /**
     * Will preserve the metadata of the original block when replaced.
     * @param replacementBlock
     */
    public void setReplacement(Block replacementBlock) {
        this.replacementBlock = replacementBlock.getDefaultState();
        this.preserveMeta = true;
    }

    /**
     * Don't replace this block
     */
    public void clearReplacement() {
        this.replacementBlock = null;
    }

    /**
     * If set to true, the metadata of the original block will be preserved.
     * @param preserveMeta
     */
    public void setPreserveMeta(boolean preserveMeta) {
        this.preserveMeta = preserveMeta;
    }

    /**
     * Get the replacement BlockState for oldBlock
     * @param oldBlock the BlockState to replace
     * @return the block to replace oldBlock with. Will have metadata from oldBlock if preserveMetadata is on.
     */
    public IBlockState replace(IBlockState oldBlock) {
        if (oldBlock.getBlock() != this.defaultBlock) {
            Logger.debug("VillageMaterialSwap was asked to replace a block that didnt match it. This should not have happened");
            return oldBlock;
        }
        IBlockState newBlock = this.replacementBlock;
        if (preserveMeta && newBlock != null) {
            newBlock = newBlock.getBlock().getStateFromMeta(oldBlock.getBlock().getMetaFromState(oldBlock));
        }
        return newBlock;
    }
}
