package rtg.util;

import net.minecraft.block.*;
import net.minecraft.init.Blocks;
/**
 * Created by topisani on 2/23/16.
 */
public class VillageMaterial {

    public final VillageMaterialReplacement path = new VillageMaterialReplacement(Blocks.gravel);
    public final VillageMaterialReplacement foundation = new VillageMaterialReplacement(Blocks.cobblestone);
    public final VillageMaterialReplacement wall = new VillageMaterialReplacement(Blocks.planks);
    public final VillageMaterialReplacement corner = new VillageMaterialReplacement(Blocks.log);
    public final VillageMaterialReplacement roof = new VillageMaterialReplacement(Blocks.oak_stairs);
    public final VillageMaterialReplacement stairs = new VillageMaterialReplacement(Blocks.stone_stairs);
    public final VillageMaterialReplacement blacksmith_roof = new VillageMaterialReplacement(Blocks.stone_slab);
    public final VillageMaterialReplacement fence = new VillageMaterialReplacement(Blocks.oak_fence);
    public final VillageMaterialReplacement door = new VillageMaterialReplacement(Blocks.oak_door);

    public enum Preset {
        PLAINS,
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
}
