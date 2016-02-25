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
        SAND,
        ACACIA,
        SPRUCE,
        DARK_OAK,
        BIRCH,
        ICE,
        JUNGLE,
        STONE_BRICK,
        RED_SAND
    }

    public VillageMaterial(Preset preset) {

        switch ( preset ) {
            case SAND:
                path.setReplacement(Blocks.sandstone.getDefaultState());
                foundation.setReplacement(Blocks.sandstone.getDefaultState());
                wall.setReplacement(Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()));
                corner.setReplacement(Blocks.sandstone.getDefaultState());
                roof.setReplacement(Blocks.sandstone_stairs);
                stairs.setReplacement(Blocks.sandstone_stairs);
                blacksmith_roof.setReplacement(Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.SAND.getMetadata()));
                break;
            case ACACIA:
                wall.setReplacement(Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.ACACIA.getMetadata()));
                corner.setReplacement(Blocks.log2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA));
                roof.setReplacement(Blocks.acacia_stairs);
                fence.setReplacement(Blocks.acacia_fence);
                door.setReplacement(Blocks.acacia_door);
                break;
            case SPRUCE:
                wall.setReplacement(Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.SPRUCE.getMetadata()));
                corner.setReplacement(Blocks.log.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE));
                roof.setReplacement(Blocks.spruce_stairs);
                fence.setReplacement(Blocks.spruce_fence);
                door.setReplacement(Blocks.spruce_door);
                break;
            case DARK_OAK:
                wall.setReplacement(Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.DARK_OAK.getMetadata()));
                corner.setReplacement(Blocks.log2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK));
                roof.setReplacement(Blocks.dark_oak_stairs);
                fence.setReplacement(Blocks.dark_oak_fence);
                door.setReplacement(Blocks.dark_oak_door);
                break;
            case BIRCH:
                wall.setReplacement(Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.BIRCH.getMetadata()));
                corner.setReplacement(Blocks.log.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.BIRCH));
                roof.setReplacement(Blocks.birch_stairs);
                fence.setReplacement(Blocks.birch_fence);
                door.setReplacement(Blocks.birch_door);
                break;
            case ICE:
                path.setReplacement(Blocks.packed_ice.getDefaultState());
                wall.setReplacement(Blocks.packed_ice.getDefaultState());
                foundation.setReplacement(Blocks.snow.getDefaultState());
                corner.setReplacement(Blocks.snow.getDefaultState());
                roof.setReplacement(Blocks.snow.getDefaultState());
                stairs.setReplacement(Blocks.snow_layer.getDefaultState().withProperty(BlockSnow.LAYERS, 4));
                blacksmith_roof.setReplacement(Blocks.snow_layer.getDefaultState().withProperty(BlockSnow.LAYERS, 4));
                fence.setReplacement(Blocks.birch_fence);
                door.setReplacement(Blocks.spruce_door);
                break;
            case JUNGLE:
                wall.setReplacement(Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.JUNGLE.getMetadata()));
                corner.setReplacement(Blocks.log.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE));
                roof.setReplacement(Blocks.jungle_stairs);
                fence.setReplacement(Blocks.jungle_fence);
                door.setReplacement(Blocks.jungle_door);
                break;
            case STONE_BRICK:
                wall.setReplacement(Blocks.stonebrick.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.DEFAULT));
                foundation.setReplacement(Blocks.stonebrick.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.MOSSY));
                corner.setReplacement(Blocks.stonebrick.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.CHISELED));
                roof.setReplacement(Blocks.stone_brick_stairs);
                stairs.setReplacement(Blocks.stone_brick_stairs);
                blacksmith_roof.setReplacement(Blocks.stone_slab.getDefaultState().withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.BRICK));
                fence.setReplacement(Blocks.cobblestone_wall);
                door.setReplacement(Blocks.spruce_door);
                break;
            case RED_SAND:
                path.setReplacement(Blocks.red_sandstone.getDefaultState());
                foundation.setReplacement(Blocks.red_sandstone.getDefaultState());
                wall.setReplacement(Blocks.red_sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()));
                corner.setReplacement(Blocks.red_sandstone.getDefaultState());
                roof.setReplacement(Blocks.red_sandstone_stairs);
                stairs.setReplacement(Blocks.red_sandstone_stairs);
                blacksmith_roof.setReplacement(Blocks.stone_slab2.getDefaultState().withProperty(BlockStoneSlabNew.VARIANT, BlockStoneSlabNew.EnumType.RED_SANDSTONE));
                door.setReplacement(Blocks.acacia_door);
                break;
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
