package rtg.util;

import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
/**
 * Holds all village blocks that should be replaced for the biome it belongs to
 */
public class VillageMaterial {

    public final VillageMaterialSwap path = new VillageMaterialSwap(Blocks.gravel);
    public final VillageMaterialSwap foundation = new VillageMaterialSwap(Blocks.cobblestone);
    public final VillageMaterialSwap wall = new VillageMaterialSwap(Blocks.planks);
    public final VillageMaterialSwap corner = new VillageMaterialSwap(Blocks.log);
    public final VillageMaterialSwap roof = new VillageMaterialSwap(Blocks.oak_stairs);
    public final VillageMaterialSwap stairs = new VillageMaterialSwap(Blocks.stone_stairs);
    public final VillageMaterialSwap blacksmith_roof = new VillageMaterialSwap(Blocks.stone_slab);
    public final VillageMaterialSwap fence = new VillageMaterialSwap(Blocks.oak_fence);
    public final VillageMaterialSwap door = new VillageMaterialSwap(Blocks.oak_door);

    public enum Preset {
        BASE,
        DESERT,
        SAVANNA,
        FOREST,
        SWAMP,
        ICE,
        JUNGLE,
        RED_SAND
    }

    public VillageMaterial(Preset preset) {

        switch ( preset ) {
            case DESERT:
                path.setReplacement(Blocks.sandstone.getDefaultState());
                foundation.setReplacement(Blocks.sandstone.getDefaultState());
                wall.setReplacement(Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()));
                corner.setReplacement(Blocks.sandstone.getDefaultState());
                roof.setReplacement(Blocks.sandstone_stairs);
                stairs.setReplacement(Blocks.sandstone_stairs);
                blacksmith_roof.setReplacement(Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.SAND.getMetadata()));
            case SAVANNA:
                wall.setReplacement(Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.ACACIA.getMetadata()));
                corner.setReplacement(Blocks.log2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA));
                stairs.setReplacement(Blocks.acacia_stairs);
                fence.setReplacement(Blocks.acacia_fence);
                door.setReplacement(Blocks.acacia_door);
        }

    }

    /**
     * Gets the replacement for a BlockState.
     * @param originalBlock BlockState to replace
     * @return replacement BlockState.
     */
    public IBlockState replace(IBlockState originalBlock) {
        Block oldBlock = originalBlock.getBlock();
        if (oldBlock == path.getDefault()) return path.replace(originalBlock);
        if (oldBlock == foundation.getDefault()) return foundation.replace(originalBlock);
        if (oldBlock == wall.getDefault()) return wall.replace(originalBlock);
        if (oldBlock == corner.getDefault()) return corner.replace(originalBlock);
        if (oldBlock == roof.getDefault()) return roof.replace(originalBlock);
        if (oldBlock == stairs.getDefault()) return stairs.replace(originalBlock);
        if (oldBlock == blacksmith_roof.getDefault()) return blacksmith_roof.replace(originalBlock);
        if (oldBlock == door.getDefault()) return door.replace(originalBlock);
        if (oldBlock == fence.getDefault()) return fence.replace(originalBlock);
        return null;
    }
}
