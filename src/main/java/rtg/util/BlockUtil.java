package rtg.util;

import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;

public class BlockUtil {

    public static Block getBlock(String id) {
        return Block.getBlockFromName(id);
    }

    public static IBlockState getStateSand(int meta) {

        switch (meta) {
            case 0:
                return Blocks.sand.getDefaultState();
            case 1:
                return Blocks.sand.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND);
            default:
                return Blocks.sand.getDefaultState();
        }
    }

    public static IBlockState getStateClay(int meta) {
        switch (meta) {
            case 0:
                return Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.WHITE);
            case 1:
                return Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.ORANGE);
            case 2:
                return Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.MAGENTA);
            case 3:
                return Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.LIGHT_BLUE);
            case 4:
                return Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.YELLOW);
            case 5:
                return Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.LIME);
            case 6:
                return Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.PINK);
            case 7:
                return Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.GRAY);
            case 8:
                return Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.SILVER);
            case 9:
                return Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.CYAN);
            case 10:
                return Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.PURPLE);
            case 11:
                return Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.BLUE);
            case 12:
                return Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.BROWN);
            case 13:
                return Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.GREEN);
            case 14:
                return Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.RED);
            case 15:
                return Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.BLACK);
            default:
                return Blocks.hardened_clay.getDefaultState();
        }
    }

    public static IBlockState getStateLog(int meta) {
        switch (meta) {
            case 0:
                return Blocks.log.getDefaultState();
            case 1:
                return Blocks.log.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE);
            case 2:
                return Blocks.log.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.BIRCH);
            case 3:
                return Blocks.log.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE);
            default:
                return Blocks.log.getDefaultState();
        }
    }

    public static IBlockState getStateLeaf(int meta) {
        switch (meta) {
            case 0:
                return Blocks.leaves.getDefaultState();
            case 1:
                return Blocks.leaves.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.SPRUCE);
            case 2:
                return Blocks.leaves.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.BIRCH);
            case 3:
                return Blocks.leaves.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.JUNGLE);
            default:
                return Blocks.leaves.getDefaultState();
        }
    }

    public static IBlockState getStateLog2(int meta) {
        switch (meta) {
            case 0:
                return Blocks.log2.getDefaultState();
            case 1:
                return Blocks.log2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK);
            default:
                return Blocks.log2.getDefaultState();
        }
    }

    public static IBlockState getStateLeaf2(int meta) {
        switch (meta) {
            case 0:
                return Blocks.leaves2.getDefaultState();
            case 1:
                return Blocks.leaves2.getDefaultState().withProperty(BlockNewLeaf.VARIANT, BlockPlanks.EnumType.DARK_OAK);
            default:
                return Blocks.leaves2.getDefaultState();
        }
    }

    public static IBlockState getStateDirt(int meta) {
        switch(meta) {
            case 0:
                return Blocks.dirt.getDefaultState();
            case 1:
                return Blocks.dirt.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT);
            case 2:
                return Blocks.dirt.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.PODZOL);
            default:
                return Blocks.dirt.getDefaultState();
        }
    }

    public static IBlockState getStateSapling (int meta) {
        switch (meta) {
            case 0:
                return Blocks.sapling.getDefaultState();
            case 1:
                return Blocks.sapling.getDefaultState().withProperty(BlockSapling.TYPE, BlockPlanks.EnumType.SPRUCE);
            case 2:
                return Blocks.sapling.getDefaultState().withProperty(BlockSapling.TYPE, BlockPlanks.EnumType.BIRCH);
            case 3:
                return Blocks.sapling.getDefaultState().withProperty(BlockSapling.TYPE, BlockPlanks.EnumType.JUNGLE);
            case 4:
                return Blocks.sapling.getDefaultState().withProperty(BlockSapling.TYPE, BlockPlanks.EnumType.ACACIA);
            case 5:
                return Blocks.sapling.getDefaultState().withProperty(BlockSapling.TYPE, BlockPlanks.EnumType.DARK_OAK);
            default:
                return Blocks.sapling.getDefaultState();
        }
    }

    /*
     * FLOWER LIST:
     * 0	Poppy
     * 1	Blue Orchid
     * 2	Allium
     * 3	Azure Bluet
     * 4	Red Tulip
     * 5	Orange Tulip
     * 6	White Tulip
     * 7	Pink Tulip
     * 8	Oxeye Daisy
     * 9	Yellow Flower
     * 10	Sunflower
     * 11	Lilac
     * 12	Double Tallgrass
     * 13	Large Fern
     * 14	Rose Bush
     * 15	Peony
     */
    public static IBlockState getStateFlower (int meta) {
        switch (meta) {
            case 0:
                return Blocks.red_flower.getDefaultState();
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return Blocks.red_flower.getStateFromMeta(meta);
            case 9:
                return Blocks.yellow_flower.getDefaultState();
            case 10:
                return Blocks.double_plant.getDefaultState()
                    .withProperty(BlockDoublePlant.VARIANT, BlockDoublePlant.EnumPlantType.SUNFLOWER);
            case 11:
                return Blocks.double_plant.getDefaultState()
                    .withProperty(BlockDoublePlant.VARIANT, BlockDoublePlant.EnumPlantType.SYRINGA);
            case 12:
                return Blocks.double_plant.getDefaultState()
                    .withProperty(BlockDoublePlant.VARIANT, BlockDoublePlant.EnumPlantType.GRASS);
            case 13:
                return Blocks.double_plant.getDefaultState()
                    .withProperty(BlockDoublePlant.VARIANT, BlockDoublePlant.EnumPlantType.FERN);
            case 14:
                return Blocks.double_plant.getDefaultState()
                    .withProperty(BlockDoublePlant.VARIANT, BlockDoublePlant.EnumPlantType.ROSE);
            case 15:
                return Blocks.double_plant.getDefaultState()
                    .withProperty(BlockDoublePlant.VARIANT, BlockDoublePlant.EnumPlantType.PAEONIA);
            default:
                return Blocks.red_flower.getStateFromMeta(meta);
        }
    }
}