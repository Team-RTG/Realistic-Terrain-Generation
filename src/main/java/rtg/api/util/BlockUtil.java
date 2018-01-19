package rtg.api.util;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockNewLeaf;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockSponge;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;

public final class BlockUtil {

    private BlockUtil() {}

    @Nullable
    public static Block getBlock(@Nullable final String resourceLocation) {

        if (resourceLocation == null || resourceLocation.isEmpty()) { return null; }

        ResourceLocation rl = new ResourceLocation(resourceLocation);
        return (Block.REGISTRY.containsKey(rl)) ? Block.REGISTRY.getObject(rl) : null;
    }

    public static Block getBlock(@Nullable final String resourceLocation, final Block fallback) {

        Block block = getBlock(resourceLocation);
        return (block != null) ? block : fallback;
    }

    @Nullable
    public static Block getBlock(@Nullable final ResourceLocation resourceLocation) {

        return (resourceLocation != null && Block.REGISTRY.containsKey(resourceLocation)) ? Block.REGISTRY.getObject(resourceLocation) : null;
    }

    public static Block getBlock(@Nullable final ResourceLocation resourceLocation, final Block fallback) {

        Block block = getBlock(resourceLocation);
        return (block != null) ? block : fallback;
    }

    public static IBlockState getBlockState(@Nullable final String resourceLocation, @Nullable final Integer meta, final IBlockState fallback) {

        Block block = getBlock(resourceLocation);
        return (block!=null) ? block.getStateFromMeta((meta!=null)?meta:0) : fallback;
    }

    public static IBlockState getBlockState(@Nullable final Block block, @Nullable final Integer meta, final IBlockState fallback) {

        return (block!=null) ? block.getStateFromMeta((meta!=null)?meta:0) : fallback;
    }

    @Nullable
    public static IBlockState getBlockStateFromString(@Nullable final String configString) {

        if (configString == null || configString.isEmpty()) { return null; }

        String rl;
        int meta = 0;

        // check if the input string has a meta value "/<meta value>"
        int i = configString.indexOf("/".codePointAt(0));
        if (i >= 0) {
            try {
                meta = Integer.parseInt(configString.substring(i + 1, configString.length()));
            }
            catch (NumberFormatException ignored) {}
            rl = configString.substring(0, i);
        }
        else { rl = configString;}

        rl = (rl.indexOf(":".codePointAt(0)) < 0) ? "minecraft:".concat(rl) : rl;

        Block block = Block.getBlockFromName(rl);
        if (block == null) { return null; }

        @SuppressWarnings("deprecation")
        IBlockState blockState = block.getStateFromMeta(meta);
        return blockState;
    }

    public static IBlockState getBlockStateFromString(@Nullable final String configString, final IBlockState fallback) {

        IBlockState blockState = getBlockStateFromString(configString);
        return (blockState != null) ? blockState : fallback;
    }

    public static IBlockState getStateSand(final int meta) {

        switch (meta) {
            case 0:
                return Blocks.SAND.getDefaultState();
            case 1:
                return Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND);
            default:
                return Blocks.SAND.getDefaultState();
        }
    }

    public static IBlockState getStateClay(final int meta) {
        switch (meta) {
            case 0:
                return Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.WHITE);
            case 1:
                return Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.ORANGE);
            case 2:
                return Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.MAGENTA);
            case 3:
                return Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.LIGHT_BLUE);
            case 4:
                return Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.YELLOW);
            case 5:
                return Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.LIME);
            case 6:
                return Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.PINK);
            case 7:
                return Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.GRAY);
            case 8:
                return Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.SILVER);
            case 9:
                return Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.CYAN);
            case 10:
                return Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.PURPLE);
            case 11:
                return Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.BLUE);
            case 12:
                return Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.BROWN);
            case 13:
                return Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.GREEN);
            case 14:
                return Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.RED);
            case 15:
                return Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.BLACK);
            default:
                return Blocks.HARDENED_CLAY.getDefaultState();
        }
    }

    public static IBlockState getStateLog(final int meta) {
        switch (meta) {
            case 0:
                return Blocks.LOG.getDefaultState();
            case 1:
                return Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE);
            case 2:
                return Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.BIRCH);
            case 3:
                return Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE);
            default:
                return Blocks.LOG.getDefaultState();
        }
    }

    public static IBlockState getStateLeaf(final int meta) {
        switch (meta) {
            case 0:
                return Blocks.LEAVES.getDefaultState();
            case 1:
                return Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.SPRUCE);
            case 2:
                return Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.BIRCH);
            case 3:
                return Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.JUNGLE);
            default:
                return Blocks.LEAVES.getDefaultState();
        }
    }

    public static IBlockState getStateLog2(final int meta) {
        switch (meta) {
            case 0:
                return Blocks.LOG2.getDefaultState();
            case 1:
                return Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK);
            default:
                return Blocks.LOG2.getDefaultState();
        }
    }

    public static IBlockState getStateLeaf2(final int meta) {
        switch (meta) {
            case 0:
                return Blocks.LEAVES2.getDefaultState();
            case 1:
                return Blocks.LEAVES2.getDefaultState().withProperty(BlockNewLeaf.VARIANT, BlockPlanks.EnumType.DARK_OAK);
            default:
                return Blocks.LEAVES2.getDefaultState();
        }
    }

    public static IBlockState getStateDirt(final int meta) {
        switch(meta) {
            case 0:
                return Blocks.DIRT.getDefaultState();
            case 1:
                return Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT);
            case 2:
                return Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.PODZOL);
            default:
                return Blocks.DIRT.getDefaultState();
        }
    }

    public static IBlockState getStateSapling (final int meta) {
        switch (meta) {
            case 0:
                return Blocks.SAPLING.getDefaultState();
            case 1:
                return Blocks.SAPLING.getDefaultState().withProperty(BlockSapling.TYPE, BlockPlanks.EnumType.SPRUCE);
            case 2:
                return Blocks.SAPLING.getDefaultState().withProperty(BlockSapling.TYPE, BlockPlanks.EnumType.BIRCH);
            case 3:
                return Blocks.SAPLING.getDefaultState().withProperty(BlockSapling.TYPE, BlockPlanks.EnumType.JUNGLE);
            case 4:
                return Blocks.SAPLING.getDefaultState().withProperty(BlockSapling.TYPE, BlockPlanks.EnumType.ACACIA);
            case 5:
                return Blocks.SAPLING.getDefaultState().withProperty(BlockSapling.TYPE, BlockPlanks.EnumType.DARK_OAK);
            default:
                return Blocks.SAPLING.getDefaultState();
        }
    }

    public static IBlockState getSponge (final int meta) {
        switch (meta) {
            case 0:
                return Blocks.SPONGE.getDefaultState();
            case 1:
                return Blocks.SPONGE.getDefaultState().withProperty(BlockSponge.WET, true);
            default:
                return Blocks.SPONGE.getDefaultState();
        }
    }

    /**    FLOWER LIST:
      *    0 Poppy,       1 Blue Orchid,    2 Allium,     3 Azure Bluet,  4 Red Tulip,         5 Orange Tulip,  6 White Tulip,  7 Pink Tulip
      *    8 Oxeye Daisy, 9 Yellow Flower, 10 Sunflower, 11 Lilac,       12 Double Tallgrass, 13 Large Fern,   14 Rose Bush,   15 Peony      */
    public static IBlockState getStateFlower (final int meta) {
        switch (meta) {
            case 0:
                return Blocks.RED_FLOWER.getDefaultState();
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return Blocks.RED_FLOWER.getStateFromMeta(meta);
            case 9:
                return Blocks.YELLOW_FLOWER.getDefaultState();
            case 10:
                return Blocks.DOUBLE_PLANT.getDefaultState()
                    .withProperty(BlockDoublePlant.VARIANT, BlockDoublePlant.EnumPlantType.SUNFLOWER);
            case 11:
                return Blocks.DOUBLE_PLANT.getDefaultState()
                    .withProperty(BlockDoublePlant.VARIANT, BlockDoublePlant.EnumPlantType.SYRINGA);
            case 12:
                return Blocks.DOUBLE_PLANT.getDefaultState()
                    .withProperty(BlockDoublePlant.VARIANT, BlockDoublePlant.EnumPlantType.GRASS);
            case 13:
                return Blocks.DOUBLE_PLANT.getDefaultState()
                    .withProperty(BlockDoublePlant.VARIANT, BlockDoublePlant.EnumPlantType.FERN);
            case 14:
                return Blocks.DOUBLE_PLANT.getDefaultState()
                    .withProperty(BlockDoublePlant.VARIANT, BlockDoublePlant.EnumPlantType.ROSE);
            case 15:
                return Blocks.DOUBLE_PLANT.getDefaultState()
                    .withProperty(BlockDoublePlant.VARIANT, BlockDoublePlant.EnumPlantType.PAEONIA);
            default:
                return Blocks.RED_FLOWER.getStateFromMeta(meta);
        }
    }

    public static IBlockState getStateStone(final StoneType stoneType) {

        switch (stoneType) {
            case GRANITE:
                return Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE);
            case POLISHED_GRANITE:
                return Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE_SMOOTH);
            case DIORITE:
                return Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE);
            case POLISHED_DIORITE:
                return Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE_SMOOTH);
            case ANDESITE:
                return Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE);
            case POLISHED_ANDESITE:
                return Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE_SMOOTH);
            default:
                return Blocks.STONE.getDefaultState();
        }
    }

    @Nullable
    public static Material getMaterial(final String name) {
        switch (name) {
            case "AIR":             return Material.AIR;
            case "ANVIL":           return Material.ANVIL;
            case "BARRIER":         return Material.BARRIER;
            case "CACTUS":          return Material.CACTUS;
            case "CAKE":            return Material.CAKE;
            case "CARPET":          return Material.CARPET;
            case "CIRCUITS":        return Material.CIRCUITS;
            case "CLAY":            return Material.CLAY;
            case "CLOTH":           return Material.CLOTH;
            case "CORAL":           return Material.CORAL;
            case "CRAFTED_SNOW":    return Material.CRAFTED_SNOW;
            case "DRAGON_EGG":      return Material.DRAGON_EGG;
            case "FIRE":            return Material.FIRE;
            case "GLASS":           return Material.GLASS;
            case "GOURD":           return Material.GOURD;
            case "GRASS":           return Material.GRASS;
            case "GROUND":          return Material.GROUND;
            case "ICE":             return Material.ICE;
            case "IRON":            return Material.IRON;
            case "LAVA":            return Material.LAVA;
            case "LEAVES":          return Material.LEAVES;
            case "PACKED_ICE":      return Material.PACKED_ICE;
            case "PISTON":          return Material.PISTON;
            case "PLANTS":          return Material.PLANTS;
            case "PORTAL":          return Material.PORTAL;
            case "REDSTONE_LIGHT":  return Material.REDSTONE_LIGHT;
            case "ROCK":            return Material.ROCK;
            case "SAND":            return Material.SAND;
            case "SNOW":            return Material.SNOW;
            case "SPONGE":          return Material.SPONGE;
            case "STRUCTURE_VOID":  return Material.STRUCTURE_VOID;
            case "TNT":             return Material.TNT;
            case "VINE":            return Material.VINE;
            case "WATER":           return Material.WATER;
            case "WEB":             return Material.WEB;
            case "WOOD":            return Material.WOOD;
            default:                return null;
        }
    }

    public enum StoneType {
        STONE,
        GRANITE,
        POLISHED_GRANITE,
        DIORITE,
        POLISHED_DIORITE,
        ANDESITE,
        POLISHED_ANDESITE
    }
}