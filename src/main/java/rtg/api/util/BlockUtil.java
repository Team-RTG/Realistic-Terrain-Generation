package rtg.api.util;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.google.common.base.Optional;
import org.apache.commons.lang3.StringUtils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockNewLeaf;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockSponge;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;

/**
 * A utility class for various operations involving {@link Block}s, and {@link IBlockState}s
 *
 * @author <a href="https://github.com/srs-bsns">srs-bsns</a>
 * @version 1.0.0
 */
@SuppressWarnings("WeakerAccess")
@UtilityClass
public final class BlockUtil
{
    private BlockUtil() {}

    public static final String  GROUP_RESLOC       = "resloc";
    public static final String  GROUP_PROPERTIES   = "props";
    public static final String  BLOCKSTATE_PATTERN = "^(?<" + GROUP_RESLOC + ">[a-z0-9_]*:[a-z0-9_]+)(?:(?:\\[)(?<" + GROUP_PROPERTIES + ">[a-z0-9_]+=[a-z0-9_]+(?:,[a-z0-9_]+=[a-z0-9_]+)*)?(?:\\]))?.*$";
    public static final Pattern BLOCKSTATE_MATCHER = Pattern.compile(BLOCKSTATE_PATTERN);


    // String/ResourceLocation Getters

    /**
     * Gets a Block from the {@link Block#REGISTRY} from a {@link ResourceLocation} if it exists.
     *
     * @param resourceLocation A ResourceLocation for a Block in String format. (ie "minecraft:dirt")
     * @return                 A Block instance if the Block.REGISTRY contains a match, otherwise returns <b>null</b>.
     *
     * @since 1.0.0
     */
    @Nullable
    public static Block getBlock(@Nullable final String resourceLocation) {
        return (!StringUtils.isEmpty(resourceLocation)) ? getBlock(new ResourceLocation(resourceLocation)) : null;
    }

    /**
     * Gets a Block from the {@link Block#REGISTRY} from a {@link ResourceLocation} if it exists.
     *
     * @param resourceLocation A ResourceLocation for a Block.
     * @return                 A Block instance if the Block.REGISTRY contains a match, otherwise returns <b>null</b>.
     *
     * @since 1.0.0
     */
    @Nullable
    public static Block getBlock(@Nullable final ResourceLocation resourceLocation) {
        return (resourceLocation != null && Block.REGISTRY.containsKey(resourceLocation)) ? Block.REGISTRY.getObject(resourceLocation) : null;
    }

    /**
     * Gets a Block from the {@link Block#REGISTRY} from a {@link ResourceLocation} if it exists,
     * otherwise returns a fallback Block if a match isn't found.
     *
     * @param resourceLocation A ResourceLocation for a Block in String format. (ie "minecraft:dirt")
     * @param fallback         A fallback Block if the lookup returns null.
     * @return                 A Block instance if the Block.REGISTRY contains a match, otherwise returns {@code fallback}.
     *
     * @since 1.0.0
     */
    public static Block getBlock(@Nullable final String resourceLocation, final Block fallback) {
        return (!StringUtils.isEmpty(resourceLocation)) ? getBlock(new ResourceLocation(resourceLocation), fallback) : fallback;
    }

    /**
     * Gets a Block from the {@link Block} REGISTRY from a {@link ResourceLocation} if it exists,
     * otherwise returns a fallback Block if a match isn't found.
     *
     * @param resourceLocation A ResourceLocation for a Block.
     * @param fallback         A fallback Block if the lookup returns null.
     * @return                 A Block instance if the Block.REGISTRY contains a match, otherwise returns {@code fallback}.
     *
     * @since 1.0.0
     */
    public static Block getBlock(@Nullable final ResourceLocation resourceLocation, final Block fallback) {
        Block block = getBlock(resourceLocation);
        return (block != null) ? block : fallback;
    }

    /**
     * Parse a config string to get a {@link IBlockState} with specific {@link IProperty} values. Config strings
     * should be written identically as how they appear from a call to {@link BlockStateBase#toString()}
     * eg: <b>minecraft:stained_glass_pane[color=pink,east=false,north=true,south=true,west=false]</b>
     * The strings can contain spaces, for easier reading, which will be stripped when parsing.
     * The strings must fallow the regular expression in {@link BlockUtil#BLOCKSTATE_PATTERN}
     *
     * @param configString A config string for a IBlockState.
     * @param fallback     A fallback IBlockState to return if the config is invalid, or does not exist.
     * @return             The requested IBlockState from the config string, or the fallback.
     */
    public static IBlockState getBlockStateFromCfgString(@Nullable final String configString, final IBlockState fallback) {
        if (StringUtils.isEmpty(configString)) { return fallback; }
        IBlockState blockState = getBlockStateFromCfgString(configString);
        return (blockState != null) ? blockState : fallback;
    }

    /**
     * Parse a config string to get a {@link IBlockState} with specific {@link IProperty} values. Config strings
     * should be written identically as how they appear from a call to {@link BlockStateBase#toString()}
     * eg: <b>minecraft:stained_glass_pane[color=pink,east=false,north=true,south=true,west=false]</b>
     * The strings can contain spaces, for easier reading, which will be stripped when parsing.
     * The strings must fallow the regular expression in {@link BlockUtil#BLOCKSTATE_PATTERN}
     *
     * @param configString A config string for a IBlockState.
     * @return             The requested IBlockState from the config string, or <b>null</b> if the
     *                     config string is invalid, or the IBlockState doesn't exist.
     */
    @Nullable
    public static IBlockState getBlockStateFromCfgString(@Nullable final String configString) {

        if (StringUtils.isEmpty(configString)) { return null; }

        Matcher m = BLOCKSTATE_MATCHER.matcher(configString.replace(" ",""));
        if (!m.find()) { return null; }

        final ResourceLocation resloc = new ResourceLocation(m.group(GROUP_RESLOC).trim());
        if (!Block.REGISTRY.containsKey(resloc)) { return null; }
        Block block = Block.REGISTRY.getObject(resloc);
        IBlockState ret = block.getDefaultState();

        String proplist = m.group(GROUP_PROPERTIES);
        if (proplist == null) { return ret; }

        for (String entry : proplist.split(",")) {
            String prop = entry.split("=")[0];
            String val = entry.split("=")[1];
            IProperty<?> property = block.getBlockState().getProperty(prop);
            if (property != null) ret = getPossibleState(ret, property, val);
        }
        return ret;
    }

    /**
     * Get a {@link IBlockState}, with a requested {@link IProperty}, of a given {@link Block},
     * if the property exists and return a fallback state if the property lookup fails.
     *
     * @param block        The base Block to get the requested IBlockState of.
     * @param propertyName A name of a possible IProperty of state for {@code block}.
     * @param valueName    A possible valid value name of the IProperty to derive a IBlockState of {@code block} from.
     * @param fallback     A fallback IBlockState to return if the lookup fails.
     * @return             A IBlockState, with the requested IProperty values, of {@code block},
     *                     or null if the property, or values of property, can not be found.
     *
     * @since 1.0.0
     */
    public static IBlockState getPossibleState(final Block block, final String propertyName, final String valueName, IBlockState fallback) {
        if (StringUtils.isEmpty(propertyName) || StringUtils.isEmpty(valueName)) { return fallback; }
        IBlockState bs = getPossibleState(block, propertyName, valueName);
        return bs != null ? bs : fallback;
    }

    /**
     * Get a {@link IBlockState}, with a requested {@link IProperty}, of a given {@link Block}, if the property exists.
     *
     * @param block        The base Block to get the requested IBlockState of.
     * @param propertyName A name of a possible IProperty of state for {@code block}.
     * @param valueName    A possible valid valueName of the IProperty to derive a IBlockState of {@code block} from.
     * @return             A IBlockState, with the requested IProperty values, of {@code block},
     *                     or null if the {@code propertyName}, or {@code valueName} of property, can not be found.
     *
     * @since 1.0.0
     */
    @Nullable
    public static IBlockState getPossibleState(final Block block, final String propertyName, final String valueName) {
        if (StringUtils.isEmpty(propertyName) || StringUtils.isEmpty(valueName)) { return null; }
        IProperty<?> property = block.getBlockState().getProperty(propertyName);
        return (property != null) ? getPossibleState(block.getDefaultState(), property, valueName) : null;
    }

    /**
     * Get a possible new {@link IBlockState}, with a requested {@link IProperty}, for a given IBlockState.
     *
     * @param blockState   The original IBlockState to get a possibly new IBlockState from.
     * @param propertyName A name of a possible IProperty of state for {@code blockState}.
     * @param valueName    A possible valid value name of the IProperty to derive a new IBlockState from.
     * @return             A IBlockState, with the requested Property values,
     *                     or <b>null</b> if the propertyName, or valueName of property, can not be found.
     */
    @Nullable
    public static IBlockState getPossibleState(final IBlockState blockState, final String propertyName, final String valueName) {
        if (StringUtils.isEmpty(propertyName) || StringUtils.isEmpty(valueName)) { return null; }
        IProperty<?> property = blockState.getBlock().getBlockState().getProperty(propertyName);
        return (property != null) ? getPossibleState(blockState, property, valueName) : null;
    }

    /**
     * Get a {@link IBlockState} with a requested {@link IProperty} value if the value exists.
     * Useful for recursive lookup of several IProperty values of a {@link Block}
     *
     * @param <T>        The captured type of IProperty.
     * @param blockstate The original IBlockState to try and get a new state of.
     * @param propname   The name of the IProperty to get a new state from.
     * @param valueName  The possible name a IProperty value.
     * @return           A new IBlockState with the requested IProperty values,
     *                   or the original state if the property is not found.
     *
     * @since 1.0.0
     */
    public static <T extends Comparable<T>> IBlockState getPossibleState(final IBlockState blockstate, final IProperty<T> propname, final String valueName) {
        if (StringUtils.isEmpty(valueName)) { return blockstate; }
        Optional<T> value = propname.parseValue(valueName);
        return value.isPresent() ? blockstate.withProperty(propname, value.get()) : blockstate;
    }


    // Common blockstate getters

    /**
     * A shortcut to get a specific variant of {@code minecraft:stone}.
     *
     * @param variant An enum used for the {@link IProperty} variant.
     * @return        The requested variant of {@link IBlockState}.
     *
     * @since 1.0.0
     */
    public static IBlockState getStateStone(final BlockStone.EnumType variant) {
        switch (variant) {
            case STONE          : return Blocks.STONE.getDefaultState();
            case GRANITE        : return Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE);
            case GRANITE_SMOOTH : return Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE_SMOOTH);
            case DIORITE        : return Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE);
            case DIORITE_SMOOTH : return Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE_SMOOTH);
            case ANDESITE       : return Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE);
            case ANDESITE_SMOOTH: return Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE_SMOOTH);
        }
        return Blocks.STONE.getDefaultState();
    }

    /**
     * A shortcut to get a specific variant of {@code minecraft:dirt}.
     *
     * @param variant An enum used for the {@link IProperty} variant.
     * @return        The requested variant of {@link IBlockState}.
     *
     * @since 1.0.0
     */
    public static IBlockState getStateDirt(final BlockDirt.DirtType variant) {
        switch(variant) {
            case DIRT       : return Blocks.DIRT.getDefaultState();
            case COARSE_DIRT: return Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT);
            case PODZOL     : return Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.PODZOL);
        }
        return Blocks.DIRT.getDefaultState();
    }

    /**
     * A shortcut to get a specific variant of {@code minecraft:sand}.
     *
     * @param variant An enum used for the {@link IProperty} variant.
     * @return        The requested variant of {@link IBlockState}.
     *
     * @since 1.0.0
     */
    public static IBlockState getStateSand(final BlockSand.EnumType variant) {
        switch (variant) {
            case SAND    : return Blocks.SAND.getDefaultState();
            case RED_SAND: return Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND);
        }
        return Blocks.SAND.getDefaultState();
    }

    /**
     * A shortcut to get a specific variant of {@code minecraft:clay}.
     *
     * @param color An enum used for the {@link IProperty} color.
     * @return      The requested variant of {@link IBlockState}.
     *
     * @since 1.0.0
     */
    public static IBlockState getStateClay(final EnumDyeColor color) {
        switch (color) {
            case WHITE     : return Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.WHITE);
            case ORANGE    : return Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.ORANGE);
            case MAGENTA   : return Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.MAGENTA);
            case LIGHT_BLUE: return Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.LIGHT_BLUE);
            case YELLOW    : return Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.YELLOW);
            case LIME      : return Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.LIME);
            case PINK      : return Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.PINK);
            case GRAY      : return Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.GRAY);
            case SILVER    : return Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.SILVER);
            case CYAN      : return Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.CYAN);
            case PURPLE    : return Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.PURPLE);
            case BLUE      : return Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.BLUE);
            case BROWN     : return Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.BROWN);
            case GREEN     : return Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.GREEN);
            case RED       : return Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.RED);
            case BLACK     : return Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.BLACK);
        }
        return Blocks.HARDENED_CLAY.getDefaultState();
    }

    /**
     * A shortcut to get a specific variant of {@code minecraft:log} or {@code minecraft:log2}.
     *
     * @param variant An enum used for the {@link IProperty} variant.
     * @return        The requested variant of {@link IBlockState}.
     *
     * @since 1.0.0
     */
    public static IBlockState getStateLog(final BlockPlanks.EnumType variant) {
        switch (variant) {
            case OAK     : return Blocks.LOG.getDefaultState();
            case SPRUCE  : return Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE);
            case BIRCH   : return Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.BIRCH);
            case JUNGLE  : return Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE);
            case ACACIA  : return Blocks.LOG2.getDefaultState();
            case DARK_OAK: return Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK);
        }
        return Blocks.LOG.getDefaultState();
    }

    /**
     * A shortcut to get a specific variant of {@code minecraft:leaves} or {@code minecraft:leaves2}.
     *
     * @param variant An enum used for the {@link IProperty} variant.
     * @return        The requested variant of {@link IBlockState}.
     *
     * @since 1.0.0
     */
    public static IBlockState getStateLeaf(final BlockPlanks.EnumType variant) {
        switch (variant) {
            case OAK     : return Blocks.LEAVES.getDefaultState();
            case SPRUCE  : return Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.SPRUCE);
            case BIRCH   : return Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.BIRCH);
            case JUNGLE  : return Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.JUNGLE);
            case ACACIA  : return Blocks.LEAVES2.getDefaultState();
            case DARK_OAK: return Blocks.LEAVES2.getDefaultState().withProperty(BlockNewLeaf.VARIANT, BlockPlanks.EnumType.DARK_OAK);
        }
        return Blocks.LEAVES.getDefaultState();
    }

    /**
     * A shortcut to get a specific variant of {@code minecraft:sapling}.
     *
     * @param type An enum used for the {@link IProperty} type.
     * @return     The requested variant of {@link IBlockState}.
     *
     * @since 1.0.0
     */
    public static IBlockState getStateSapling (final BlockPlanks.EnumType type) {
        switch (type) {
            case OAK     : return Blocks.SAPLING.getDefaultState();
            case SPRUCE  : return Blocks.SAPLING.getDefaultState().withProperty(BlockSapling.TYPE, BlockPlanks.EnumType.SPRUCE);
            case BIRCH   : return Blocks.SAPLING.getDefaultState().withProperty(BlockSapling.TYPE, BlockPlanks.EnumType.BIRCH);
            case JUNGLE  : return Blocks.SAPLING.getDefaultState().withProperty(BlockSapling.TYPE, BlockPlanks.EnumType.JUNGLE);
            case ACACIA  : return Blocks.SAPLING.getDefaultState().withProperty(BlockSapling.TYPE, BlockPlanks.EnumType.ACACIA);
            case DARK_OAK: return Blocks.SAPLING.getDefaultState().withProperty(BlockSapling.TYPE, BlockPlanks.EnumType.DARK_OAK);
        }
        return Blocks.SAPLING.getDefaultState();
    }

    /**
     * A shortcut to get a specific variant of {@code minecraft:sponge}.
     *
     * @param wet A boolean used to get either a dry or wet sponge.
     * @return    The requested variant of {@link IBlockState}.
     *
     * @since 1.0.0
     */
    public static IBlockState getSponge (final boolean wet) {
        return wet ? Blocks.SPONGE.getDefaultState().withProperty(BlockSponge.WET, true) : Blocks.SPONGE.getDefaultState();
    }

    /**
     * A shortcut to get a specific variant of {@code minecraft:yellow_flower} or {@code minecraft:red_flower}.
     *
     * @param type An enum used for the {@link IProperty} type.
     * @return     The requested variant of {@link IBlockState}.
     *
     * @since 1.0.0
     */
    public static IBlockState getStateFlower (final BlockFlower.EnumFlowerType type) {
        switch (type) {
            case DANDELION   : return Blocks.YELLOW_FLOWER.getDefaultState().withProperty(Blocks.YELLOW_FLOWER.getTypeProperty(), type);
            case POPPY       :
            case BLUE_ORCHID :
            case ALLIUM      :
            case HOUSTONIA   :
            case RED_TULIP   :
            case ORANGE_TULIP:
            case WHITE_TULIP :
            case PINK_TULIP  :
            case OXEYE_DAISY : return Blocks.RED_FLOWER.getDefaultState().withProperty(Blocks.RED_FLOWER.getTypeProperty(), type);
        }
        return Blocks.RED_FLOWER.getDefaultState();// unreachable
    }

    /**
     * A shortcut to get a specific variant of {@code minecraft:double_plant}.
     *
     * @param variant An enum used for the {@link IProperty} variant.
     * @return        The requested variant of {@link IBlockState}.
     *
     * @since 1.0.0
     */
    public static IBlockState getStateDoublePlant (final BlockDoublePlant.EnumPlantType variant) {
        switch (variant) {
            case SUNFLOWER: return Blocks.DOUBLE_PLANT.getDefaultState().withProperty(BlockDoublePlant.VARIANT, BlockDoublePlant.EnumPlantType.SUNFLOWER);
            case SYRINGA  : return Blocks.DOUBLE_PLANT.getDefaultState().withProperty(BlockDoublePlant.VARIANT, BlockDoublePlant.EnumPlantType.SYRINGA);
            case GRASS    : return Blocks.DOUBLE_PLANT.getDefaultState().withProperty(BlockDoublePlant.VARIANT, BlockDoublePlant.EnumPlantType.GRASS);
            case FERN     : return Blocks.DOUBLE_PLANT.getDefaultState().withProperty(BlockDoublePlant.VARIANT, BlockDoublePlant.EnumPlantType.FERN);
            case ROSE     : return Blocks.DOUBLE_PLANT.getDefaultState().withProperty(BlockDoublePlant.VARIANT, BlockDoublePlant.EnumPlantType.ROSE);
            case PAEONIA  : return Blocks.DOUBLE_PLANT.getDefaultState().withProperty(BlockDoublePlant.VARIANT, BlockDoublePlant.EnumPlantType.PAEONIA);
        }
        return Blocks.DOUBLE_PLANT.getDefaultState();
    }

    /**
     *  An enumerator to handle vanilla {@link Material}s.
     *
     * @since 1.0.0
     */
    public enum EnumMaterial {
        AIR             (Material.AIR),
        ANVIL           (Material.ANVIL),
        BARRIER         (Material.BARRIER),
        CACTUS          (Material.CACTUS),
        CAKE            (Material.CAKE),
        CARPET          (Material.CARPET),
        CIRCUITS        (Material.CIRCUITS),
        CLAY            (Material.CLAY),
        CLOTH           (Material.CLOTH),
        CORAL           (Material.CORAL),
        CRAFTED_SNOW    (Material.CRAFTED_SNOW),
        DRAGON_EGG      (Material.DRAGON_EGG),
        FIRE            (Material.FIRE),
        GLASS           (Material.GLASS),
        GOURD           (Material.GOURD),
        GRASS           (Material.GRASS),
        GROUND          (Material.GROUND),
        ICE             (Material.ICE),
        IRON            (Material.IRON),
        LAVA            (Material.LAVA),
        LEAVES          (Material.LEAVES),
        PACKED_ICE      (Material.PACKED_ICE),
        PISTON          (Material.PISTON),
        PLANTS          (Material.PLANTS),
        PORTAL          (Material.PORTAL),
        REDSTONE_LIGHT  (Material.REDSTONE_LIGHT),
        ROCK            (Material.ROCK),
        SAND            (Material.SAND),
        SNOW            (Material.SNOW),
        SPONGE          (Material.SPONGE),
        STRUCTURE_VOID  (Material.STRUCTURE_VOID),
        TNT             (Material.TNT),
        VINE            (Material.VINE),
        WATER           (Material.WATER),
        WEB             (Material.WEB),
        WOOD            (Material.WOOD);

        private final Material material;

        EnumMaterial(Material material) {
            this.material = material;
        }

        /**
         * Gets this value's {@link Material}.
         *
         * @return the material
         *
         * @since 1.0.0
         */
        public Material getMaterial() {
            return this.material;
        }

        /**
         * Checks if this value's {@link Material} is replaceable by other blocks,
         * during block placing events.
         *
         * @return <b>true</b> if this Material can be replaced, <b>false</b> if not.
         *
         * @since 1.0.0
         */
        public boolean isReplaceable() {
            return this.material.isReplaceable();
        }

        /**
         * Gets an unmodifiable Collection of all vanilla {@link Material}s.
         *
         * @return The Collection of Materials.
         *
         * @since 1.0.0
         */
        public static Collection<Material> getMaterials() {
            return Collections.unmodifiableCollection(
                Arrays.stream(values())
                    .map(EnumMaterial::getMaterial)
                    .collect(Collectors.toSet())
            );
        }

        /**
         * Gets a {@link Material} by name.
         *
         * @param name the name of the Material
         * @return     the Material, or <b>null</b> if the name doesn't exist.
         *
         * @since 1.0.0
         */
        @Nullable
        public static Material getByName(final String name) {
            return Arrays.stream(values())
                .filter(material -> material.name().equals(name.toUpperCase(Locale.ENGLISH)))
                .findFirst()
                .map(EnumMaterial::getMaterial)
                .orElse(null);
        }

        /**
         * Gets the enum value for a {@link Material}.
         *
         * @param material the material
         * @return         the enum value
         *
         * @since 1.0.0
         */
        @Nullable
        public static EnumMaterial getByMaterial(final Material material) {
            return Arrays.stream(values())
                .filter(value -> value.getMaterial() == material)
                .findFirst()
                .orElse(null);
        }

        /**
         * Gets an unmodifiable Collection of all {@link Material}s that are replaceable.
         *
         * @return the Collection of replaceable Materials
         *
         * @since 1.0.0
         */
        public static Collection<Material> getReplaceable() {
            return Collections.unmodifiableCollection(
                Arrays.stream(values())
                    .filter(EnumMaterial::isReplaceable)
                    .map(EnumMaterial::getMaterial)
                    .collect(Collectors.toList())
            );
        }
    }


    /* World context checkers */
    /* ====================== */

    /* Single position checks */

    /**
     * Check {@link Block} equality at a specific world position.
     *
     * @param world  the world
     * @param origin the origin position
     * @param block  the block to check against
     * @return       <b>true</b> if the blocks match
     *
     * @since 1.0.0
     */
    public static boolean isBlock(final World world, final BlockPos origin, final Block block) {
        return world.getBlockState(origin).getBlock() == block;
    }

    /**
     * A varargs version of {@link #isBlockOneOf(World, BlockPos, Collection)}
     *
     * @since 1.0.0
     */
    public static boolean isBlockOneOf(final World world, final BlockPos origin, final Block... validBlocks) {
        return isBlockOneOf(world, origin, Collections.unmodifiableCollection(Arrays.asList(validBlocks)));
    }

    /**
     * Check {@link Block} equality at a specific world position.
     *
     * @param world       the world
     * @param origin      the origin position
     * @param validBlocks a Collection of blocks to check against
     * @return            <b>true</b> if the block at the world position matches any in the Collection.
     *
     * @since 1.0.0
     */
    public static boolean isBlockOneOf(final World world, final BlockPos origin, final Collection<Block> validBlocks) {
        return validBlocks.contains(world.getBlockState(origin).getBlock());
    }

    /**
     * Check {@link Material} equality at a specific world position.
     *
     * @param world    the world
     * @param origin   the origin position
     * @param material the Material to check against
     * @return         <b>true</b> if the Materials match
     *
     * @since 1.0.0
     */
    public static boolean isMaterial(final World world, final BlockPos origin, final Material material) {
        return world.getBlockState(origin).getMaterial() == material;
    }

    /**
     * a varargs version of {@link #isMaterialOneOf(World, BlockPos, Collection)}
     *
     * @since 1.0.0
     */
    public static boolean isMaterialOneOf(final World world, final BlockPos origin, final Material... validMaterials) {
        return isMaterialOneOf(world, origin, Collections.unmodifiableCollection(Arrays.asList(validMaterials)));
    }

    /**
     * Check {@link Material} equality at a specific world position.
     *
     * @param world          the world
     * @param origin         the origin position
     * @param validMaterials a Collection of Materials to check against
     * @return               <b>true</b> if the Material at the world position matches any in the Collection.
     *
     * @since 1.0.0
     */
    public static boolean isMaterialOneOf(final World world, final BlockPos origin, final Collection<Material> validMaterials) {
        return validMaterials.contains(world.getBlockState(origin).getMaterial());
    }

    /**
     * Check if the {@link Material} at a specific world position is repplaceable.
     *
     * @param world          the world
     * @param origin         the origin position
     * @return               <b>true</b> if the Material at the world position is replaceable.
     *
     * @since 1.0.0
     */
    public static boolean isReplaceable(final World world, final BlockPos origin) {
        return world.getBlockState(origin).getMaterial().isReplaceable();
    }


    /**
     *  {@link #ALL_IGNORE_REPLACEABLE} Match all blocks in the checked area and ignore materials
     *          that return true for {@link Material#isReplaceable}.
     *  {@link #ALL} Match all blocks in the checked area.
     *  {@link #ANY} Match any blocks in the checked area
     *  {@link #NONE} Match no blocks in the checked area
     *
     *  @since 1.0.0
     */
    public enum MatchType {ALL_IGNORE_REPLACEABLE, ALL, ANY, NONE}


    /* Adjacent checks */

    /**
     *  A varargs delegate to {@link #checkAdjacentBlocks(MatchType, World, BlockPos, Collection)}
     *
     *  @since 1.0.0
     */
    public static boolean checkAdjacentBlocks(final MatchType matchType, final World world, final BlockPos origin, final Block... validBlocks) {
        return checkAdjacentBlocks(matchType, world, origin, Collections.unmodifiableCollection(Arrays.asList(validBlocks)));
    }

    /**
     *  Match {@link Block}s at the positions adjacent to an origin position (cardinal directions) to see
     *  if they match those in the provided collection.
     *
     * @param matchType   The {@link MatchType} of matching to perform.
     * @param world       The {@link World} that the check is taking place in.
     * @param origin      The origin {@link BlockPos} where the check is taking place.
     * @param validBlocks A collection of {@link Block}s to check for.
     *
     * @return {@link MatchType#ALL_IGNORE_REPLACEABLE}:
     *              Returns true if ALL checked positions contain a material that is {@link Material#isReplaceable},
     *              OR, that match a block in {@code validBlocks}, otherwise returns false.
     *         {@link MatchType#ALL}:
     *              Returns true if ALL checked positions matches a block in {@code validBlocks}, otherwise returns false.
     *         {@link MatchType#ANY}:
     *              Returns true if ANY checked position matches a block in {@code validBlocks}, otherwise returns false.
     *         {@link MatchType#NONE}:
     *              Returns true if NO checked position matches a block in {@code validBlocks}, otherwise returns false.
     *
     *  @since 1.0.0
     */
    public static boolean checkAdjacentBlocks(final MatchType matchType, final World world, final BlockPos origin, final Collection<Block> validBlocks) {

        final boolean ignoreReplaceable = matchType == MatchType.ALL_IGNORE_REPLACEABLE;

        switch (matchType) {

            case ALL_IGNORE_REPLACEABLE:
            case ALL:
                for (EnumFacing direction : EnumFacing.HORIZONTALS) {
                    IBlockState bs = world.getBlockState(origin.offset(direction));
                    if ((ignoreReplaceable && !bs.getMaterial().isReplaceable()) || !validBlocks.contains(bs.getBlock())) { return false; }
                }
                return true;

            case ANY:
                for (EnumFacing direction : EnumFacing.HORIZONTALS) {
                    if (validBlocks.contains(world.getBlockState(origin.offset(direction)).getBlock())) { return true; }
                }
                return false;

            case NONE:
                for (EnumFacing direction : EnumFacing.HORIZONTALS) {
                    if (validBlocks.contains(world.getBlockState(origin.offset(direction)).getBlock())) { return false; }
                }
                return true;
        }
        return false;// unreachable
    }

    /**
     *  A varargs delegate to {@link #checkAdjacentMaterials(MatchType, World, BlockPos, Collection)}
     *
     *  @since 1.0.0
     */
    public static boolean checkAdjacentMaterials(final MatchType matchType, final World world, final BlockPos origin, final Material... validMaterials) {
        return checkAdjacentMaterials(matchType, world, origin, Collections.unmodifiableCollection(Arrays.asList(validMaterials)));
    }

    /**
     *  Match {@link Material}s at the positions adjacent to an origin position (cardinal directions) to see
     *  if they match those in the provided collection.
     *
     * @param matchType      The {@link MatchType} of matching to perform.
     * @param world          The {@link World} that the check is taking place in.
     * @param origin         The origin {@link BlockPos} where the check is taking place.
     * @param validMaterials A collection of {@link Material}s to check for.
     *
     * @return {@link MatchType#ALL_IGNORE_REPLACEABLE}:
     *              Returns true if ALL checked positions contain a material that is {@link Material#isReplaceable},
     *              OR, that match a {@link Material} in {@code validMaterials}, otherwise returns false.
     *         {@link MatchType#ALL}:
     *              Returns true if ALL checked positions matches a block in {@code validMaterials}, otherwise returns false.
     *         {@link MatchType#ANY}:
     *              Returns true if ANY checked position matches a block in {@code validMaterials}, otherwise returns false.
     *         {@link MatchType#NONE}:
     *              Returns true if NO checked position matches a block in {@code validMaterials}, otherwise returns false.
     *
     *  @since 1.0.0
     */
    public static boolean checkAdjacentMaterials(final MatchType matchType, final World world, final BlockPos origin, final Collection<Material> validMaterials) {

        final boolean ignoreReplaceable = matchType == MatchType.ALL_IGNORE_REPLACEABLE;

        switch (matchType) {

            case ALL_IGNORE_REPLACEABLE:
            case ALL:
                for (EnumFacing direction : EnumFacing.HORIZONTALS) {
                    Material material = world.getBlockState(origin.offset(direction)).getMaterial();
                    if ((ignoreReplaceable && !material.isReplaceable()) || !validMaterials.contains(material)) { return false; }
                }
                return true;

            case ANY:
                for (EnumFacing direction : EnumFacing.HORIZONTALS) {
                    if (validMaterials.contains(world.getBlockState(origin.offset(direction)).getMaterial())) { return true; }
                }
                return false;

            case NONE:
                for (EnumFacing direction : EnumFacing.HORIZONTALS) {
                    if (validMaterials.contains(world.getBlockState(origin.offset(direction)).getMaterial())) { return false; }
                }
                return true;
        }
        return false;// unreachable
    }


    /* Above/Below checks */

    /**
     *  A varargs delegate to {@link #checkVerticalBlocks(MatchType, World, BlockPos, int, Collection)}
     *
     *  @since 1.0.0
     */
    public static boolean checkVerticalBlocks(final MatchType matchType, final World world, final BlockPos origin, final int distance, final Block... validBlocks) {
        return checkVerticalBlocks(matchType, world, origin, distance, Collections.unmodifiableCollection(Arrays.asList(validBlocks)));
    }

    /**
     *  Match {@link Block}s along the vertical axis to an origin position to see
     *  if they match those in the provided collection.
     *
     * @param matchType   The {@link MatchType} of matching to perform.
     * @param world       The {@link World} that the check is taking place in.
     * @param origin      The origin {@link BlockPos} where the check is taking place.
     * @param distance    The distance (in blocks) to check from the origin {@link BlockPos}
     *                    A positive value checks above; A negative value checks below.
     * @param validBlocks A collection of {@link Block}s to check for.
     *
     * @return {@link MatchType#ALL_IGNORE_REPLACEABLE}:
     *              Returns true if ALL checked positions contain a material that is {@link Material#isReplaceable},
     *              OR, that match a block in {@code validBlocks}, otherwise returns false.
     *         {@link MatchType#ALL}:
     *              Returns true if ALL checked positions matches a block in {@code validBlocks}, otherwise returns false.
     *         {@link MatchType#ANY}:
     *              Returns true if ANY checked position matches a block in {@code validBlocks}, otherwise returns false.
     *         {@link MatchType#NONE}:
     *              Returns true if NO checked position matches a block in {@code validBlocks}, otherwise returns false.
     *
     *  @since 1.0.0
     */
    public static boolean checkVerticalBlocks(final MatchType matchType, final World world, final BlockPos origin, final int distance, final Collection<Block> validBlocks) {

        final int
            ox = origin.getX(),
            oy = origin.getY(),
            oz = origin.getZ();
        final int dis = Math.abs(distance);
        // distance is: positive = check above, negative = check below
        final boolean invert = distance < 0;
        final boolean ignoreReplaceable = matchType == MatchType.ALL_IGNORE_REPLACEABLE;
        final MutableBlockPos mpos = new MutableBlockPos();

        switch (matchType) {

            case ALL_IGNORE_REPLACEABLE:
            case ALL :
                for (int i = dis; i > 0; i--) {
                    IBlockState bs = world.getBlockState(mpos.setPos(ox, oy+(invert?-i:i), oz));
                    if ((ignoreReplaceable && !bs.getMaterial().isReplaceable()) || !validBlocks.contains(bs.getBlock())) { return false; }//negated for faster short-circuit
                }
                return true;

            case ANY :
                for (int i = dis; i > 0; i--) {
                    if (validBlocks.contains(world.getBlockState(mpos.setPos(ox, oy + (invert ? -i : i), oz)).getBlock())) { return true; }
                }
                return false;

            case NONE:
                for (int i = dis; i > 0; i--) {
                    if (validBlocks.contains(world.getBlockState(mpos.setPos(ox, oy + (invert ? -i : i), oz)).getBlock())) { return false; }
                }
                return true;
        }
        return false;//unreachable
    }

    /**
     *  A varargs delegate to {@link #checkVerticalMaterials(MatchType, World, BlockPos, int, Collection)}
     *
     *  @since 1.0.0
     */
    public static boolean checkVerticalMaterials(final MatchType matchType, final World world, final BlockPos origin, final int distance, final Material... validMaterials) {
        return checkVerticalMaterials(matchType, world, origin, distance, Collections.unmodifiableCollection(Arrays.asList(validMaterials)));
    }

    /**
     *  Match {@link Material}s at the positions adjacent to an origin position (cardinal directions) to see
     *  if they match those in the provided collection.
     *
     * @param matchType      The {@link MatchType} of matching to perform.
     * @param world          The {@link World} that the check is taking place in.
     * @param origin         The origin {@link BlockPos} where the check is taking place.
     * @param distance       The distance (in blocks) to check from the origin {@link BlockPos}
     *                       A positive value checks above; A negative value checks below.
     * @param validMaterials A collection of {@link Material}s to check for.
     *
     * @return {@link MatchType#ALL_IGNORE_REPLACEABLE}:
     *              Returns true if ALL checked positions contain a material that is {@link Material#isReplaceable},
     *              OR, that match a {@link Material} in {@code validMaterials}, otherwise returns false.
     *         {@link MatchType#ALL}:
     *              Returns true if ALL checked positions matches a block in {@code validMaterials}, otherwise returns false.
     *         {@link MatchType#ANY}:
     *              Returns true if ANY checked position matches a block in {@code validMaterials}, otherwise returns false.
     *         {@link MatchType#NONE}:
     *              Returns true if NO checked position matches a block in {@code validMaterials}, otherwise returns false.
     *
     *  @since 1.0.0
     */
    public static boolean checkVerticalMaterials(final MatchType matchType, final World world, final BlockPos origin, final int distance, final Collection<Material> validMaterials) {

        final int
            ox = origin.getX(),
            oy = origin.getY(),
            oz = origin.getZ();
        final int dis = Math.abs(distance);
        // distance is: positive = check above, negative = check below
        final boolean invert = distance < 0;
        final boolean ignoreReplaceable = matchType == MatchType.ALL_IGNORE_REPLACEABLE;
        MutableBlockPos mpos = new MutableBlockPos();

        switch (matchType) {

            case ALL_IGNORE_REPLACEABLE:
            case ALL :
                for (int i = dis; i > 0; i--) {
                    Material material = world.getBlockState(mpos.setPos(ox, oy + (invert ? -i : i), oz)).getMaterial();
                    if ((ignoreReplaceable && !material.isReplaceable()) || !validMaterials.contains(material)) { return false; }//negated for faster short-circuit}
                }
                return true;

            case ANY :
                for (int i = dis; i > 0; i--) {
                    if (validMaterials.contains(world.getBlockState(mpos.setPos(ox, oy + (invert ? -i : i), oz)).getMaterial())) { return true; }
                }
                return false;

            case NONE:
                for (int i = dis; i > 0; i--) {
                    if (validMaterials.contains(world.getBlockState(mpos.setPos(ox, oy + (invert ? -i : i), oz)).getMaterial())) { return false; }
                }
                return true;
        }
        return false;//unreachable
    }


    /* Area checks */

    /**
     *  A varargs delegate to {@link #checkAreaBlocks(MatchType, World, BlockPos, int, Collection)}
     *
     *  @since 1.0.0
     */
    public static boolean checkAreaBlocks(final MatchType matchType, final World world, final BlockPos origin, final int checkRadius, final Block... validBlocks) {
        return checkAreaBlocks(matchType, world, origin, checkRadius, Collections.unmodifiableCollection(Arrays.asList(validBlocks)));
    }

    /**
     *  Match {@link Block}s in an area around an origin position (spiralling outward from the origin)
     *  to see if they match those in the provided collection.
     *
     * @param matchType   The {@link MatchType} of matching to perform.
     * @param world       The {@link World} that the check is taking place in.
     * @param origin      The origin {@link BlockPos} where the check is taking place.
     * @param checkRadius The distance (in blocks) to check from the origin {@link BlockPos}
     * @param validBlocks A collection of {@link Block}s to check for.
     *
     * @return {@link MatchType#ALL_IGNORE_REPLACEABLE}:
     *              Returns true if ALL checked positions contain a material that is {@link Material#isReplaceable},
     *              OR, that match a block in {@code validBlocks}, otherwise returns false.
     *         {@link MatchType#ALL}:
     *              Returns true if ALL checked positions matches a block in {@code validBlocks}, otherwise returns false.
     *         {@link MatchType#ANY}:
     *              Returns true if ANY checked position matches a block in {@code validBlocks}, otherwise returns false.
     *         {@link MatchType#NONE}:
     *              Returns true if NO checked position matches a block in {@code validBlocks}, otherwise returns false.
     *
     * @since 1.0.0
     */
    public static boolean checkAreaBlocks(final MatchType matchType, final World world, final BlockPos origin, final int checkRadius, final Collection<Block> validBlocks) {

        final int
            ox = origin.getX(),
            oy = origin.getY(),
            oz = origin.getZ();
        final boolean ignoreReplaceable = matchType == MatchType.ALL_IGNORE_REPLACEABLE;
        MutableBlockPos mpos = new MutableBlockPos();
        int x, z, i = 1;

        switch (matchType) {

            case ALL_IGNORE_REPLACEABLE:
            case ALL:
                IBlockState bs;
                while (i <= checkRadius) {
                    x = ox + i;
                    z = oz + i;
                    for (; x > ox - i; ) {
                        bs = world.getBlockState(mpos.setPos(--x, oy, z));
                        if ((ignoreReplaceable && !bs.getMaterial().isReplaceable()) || !validBlocks.contains(bs.getBlock())) { return false; }
                    }
                    for (; z > oz - i; ) {
                        bs = world.getBlockState(mpos.setPos(x, oy, --z));
                        if ((ignoreReplaceable && !bs.getMaterial().isReplaceable()) || !validBlocks.contains(bs.getBlock())) { return false; }
                    }
                    for (; x < ox + i; ) {
                        bs = world.getBlockState(mpos.setPos(++x, oy, z));
                        if ((ignoreReplaceable && !bs.getMaterial().isReplaceable()) || !validBlocks.contains(bs.getBlock())) { return false; }
                    }
                    for (; z < oz + i; ) {
                        bs = world.getBlockState(mpos.setPos(x, oy, ++z));
                        if ((ignoreReplaceable && !bs.getMaterial().isReplaceable()) || !validBlocks.contains(bs.getBlock())) { return false; }
                    }
                    i++;
                }
                return true;

            case ANY:
                while (i <= checkRadius) {
                    x = ox + i;
                    z = oz + i;
                    for (; x > ox - i; ) { if (validBlocks.contains(world.getBlockState(mpos.setPos(--x, oy, z)).getBlock())) { return true; } }
                    for (; z > oz - i; ) { if (validBlocks.contains(world.getBlockState(mpos.setPos(x, oy, --z)).getBlock())) { return true; } }
                    for (; x < ox + i; ) { if (validBlocks.contains(world.getBlockState(mpos.setPos(++x, oy, z)).getBlock())) { return true; } }
                    for (; z < oz + i; ) { if (validBlocks.contains(world.getBlockState(mpos.setPos(x, oy, ++z)).getBlock())) { return true; } }
                    i++;
                }
                return false;

            case NONE:
                while (i <= checkRadius) {
                    x = ox + i;
                    z = oz + i;
                    for (; x > ox - i; ) { if (validBlocks.contains(world.getBlockState(mpos.setPos(--x, oy, z)).getBlock())) { return false; } }
                    for (; z > oz - i; ) { if (validBlocks.contains(world.getBlockState(mpos.setPos(x, oy, --z)).getBlock())) { return false; } }
                    for (; x < ox + i; ) { if (validBlocks.contains(world.getBlockState(mpos.setPos(++x, oy, z)).getBlock())) { return false; } }
                    for (; z < oz + i; ) { if (validBlocks.contains(world.getBlockState(mpos.setPos(x, oy, ++z)).getBlock())) { return false; } }
                    i++;
                }
                return true;
        }
        return false;// unreachable
    }

    /**
     * A varargs delegate to {@link #checkAreaMaterials(MatchType, World, BlockPos, int, Collection)}
     *
     * @since 1.0.0
     */
    public static boolean checkAreaMaterials(final MatchType matchType, final World world, final BlockPos origin, final int checkRadius, final Material... validMaterials) {
        return checkAreaMaterials(matchType, world, origin, checkRadius, Collections.unmodifiableCollection(Arrays.asList(validMaterials)));
    }

    /**
     *  Match {@link Material}s in an area around an origin position (spiralling outward from the origin)
     *  to see if they match those in the provided collection.
     *
     * @param matchType      The {@link MatchType} of matching to perform.
     * @param world          The {@link World} that the check is taking place in.
     * @param origin         The origin {@link BlockPos} where the check is taking place.
     * @param checkRadius    The distance (in blocks) to check from the origin {@link BlockPos}
     * @param validMaterials A collection of {@link Material}s to check for.
     *
     * @return {@link MatchType#ALL_IGNORE_REPLACEABLE}:
     *              Returns true if ALL checked positions contain a material that is {@link Material#isReplaceable},
     *              OR, that match a block in {@code validMaterials}, otherwise returns false.
     *         {@link MatchType#ALL}:
     *              Returns true if ALL checked positions matches a block in {@code validMaterials}, otherwise returns false.
     *         {@link MatchType#ANY}:
     *              Returns true if ANY checked position matches a block in {@code validMaterials}, otherwise returns false.
     *         {@link MatchType#NONE}:
     *              Returns true if NO checked position matches a block in {@code validMaterials}, otherwise returns false.
     *
     * @since               1.0.0
     */
    public static boolean checkAreaMaterials(final MatchType matchType, final World world, final BlockPos origin, final int checkRadius, final Collection<Material> validMaterials) {

        final int
            ox = origin.getX(),
            oy = origin.getY(),
            oz = origin.getZ();
        final boolean ignoreReplaceable = matchType == MatchType.ALL_IGNORE_REPLACEABLE;
        MutableBlockPos mpos = new MutableBlockPos();
        int x, z, i = 1;

        switch (matchType) {

            case ALL_IGNORE_REPLACEABLE:
            case ALL:
                Material material;
                while (i <= checkRadius) {
                    x = ox + i;
                    z = oz + i;
                    for (; x > ox - i; ) {
                        material = world.getBlockState(mpos.setPos(--x, oy, z)).getMaterial();
                        if ((ignoreReplaceable && !material.isReplaceable()) || !validMaterials.contains(material)) { return false; }
                    }
                    for (; z > oz - i; ) {
                        material = world.getBlockState(mpos.setPos(x, oy, --z)).getMaterial();
                        if ((ignoreReplaceable && !material.isReplaceable()) || !validMaterials.contains(material)) { return false; }
                    }
                    for (; x < ox + i; ) {
                        material = world.getBlockState(mpos.setPos(++x, oy, z)).getMaterial();
                        if ((ignoreReplaceable && !material.isReplaceable()) || !validMaterials.contains(material)) { return false; }
                    }
                    for (; z < oz + i; ) {
                        material = world.getBlockState(mpos.setPos(x, oy, ++z)).getMaterial();
                        if ((ignoreReplaceable && !material.isReplaceable()) || !validMaterials.contains(material)) { return false; }
                    }
                    i++;
                }
                return true;

            case ANY:
                while (i <= checkRadius) {
                    x = ox + i;
                    z = oz + i;
                    for (; x > ox - i; ) { if (validMaterials.contains(world.getBlockState(mpos.setPos(--x, oy, z)).getMaterial())) { return true; } }
                    for (; z > oz - i; ) { if (validMaterials.contains(world.getBlockState(mpos.setPos(x, oy, --z)).getMaterial())) { return true; } }
                    for (; x < ox + i; ) { if (validMaterials.contains(world.getBlockState(mpos.setPos(++x, oy, z)).getMaterial())) { return true; } }
                    for (; z < oz + i; ) { if (validMaterials.contains(world.getBlockState(mpos.setPos(x, oy, ++z)).getMaterial())) { return true; } }
                    i++;
                }
                return false;

            case NONE:
                while (i <= checkRadius) {
                    x = ox + i;
                    z = oz + i;
                    for (; x > ox - i; ) { if (validMaterials.contains(world.getBlockState(mpos.setPos(--x, oy, z)).getMaterial())) { return false; } }
                    for (; z > oz - i; ) { if (validMaterials.contains(world.getBlockState(mpos.setPos(x, oy, --z)).getMaterial())) { return false; } }
                    for (; x < ox + i; ) { if (validMaterials.contains(world.getBlockState(mpos.setPos(++x, oy, z)).getMaterial())) { return false; } }
                    for (; z < oz + i; ) { if (validMaterials.contains(world.getBlockState(mpos.setPos(x, oy, ++z)).getMaterial())) { return false; } }
                    i++;
                }
                return true;
        }
        return false;// unreachable
    }


    /* Volumn checks */

    /**
     *  A varargs delegate to {@link #checkVolumeBlocks(MatchType, World, BlockPos, int, int, Collection)}
     *
     *  @since 1.0.0
     */
    public static boolean checkVolumeBlocks(final MatchType matchType, final World world, final BlockPos origin, final int checkRadius, final int checkHeight, final Block... validBlocks) {
        return checkVolumeBlocks(matchType, world, origin, checkRadius, checkHeight, Collections.unmodifiableCollection(Arrays.asList(validBlocks)));
    }

    /**
     *  Match {@link Block}s in a volume above or below (inclusive) to an origin position (spiralling outward
     *  (horizontal) from the origin, and outward (vertical)) to see if they match those in the provided collection.
     *
     * @param matchType   The {@link MatchType} of matching to perform.
     * @param world       The {@link World} that the check is taking place in.
     * @param origin      The origin {@link BlockPos} where the check is taking place.
     * @param checkRadius The distance (in blocks) to check from the origin {@link BlockPos} in the horizontal plane.
     * @param checkHeight The height distance (in blocks) to check from the origin {@link BlockPos}
     *                    A positive value checks above; A negative value checks below.
     * @param validBlocks A collection of {@link Block}s to check for.
     *
     * @return {@link MatchType#ALL_IGNORE_REPLACEABLE}:
     *              Returns true if ALL checked positions contain a material that is {@link Material#isReplaceable},
     *              OR, that match a block in {@code validBlocks}, otherwise returns false.
     *         {@link MatchType#ALL}:
     *              Returns true if ALL checked positions matches a block in {@code validBlocks}, otherwise returns false.
     *         {@link MatchType#ANY}:
     *              Returns true if ANY checked position matches a block in {@code validBlocks}, otherwise returns false.
     *         {@link MatchType#NONE}:
     *              Returns true if NO checked position matches a block in {@code validBlocks}, otherwise returns false.
     *
     * @since 1.0.0
     */
    public static boolean checkVolumeBlocks(final MatchType matchType, final World world, final BlockPos origin, final int checkRadius, final int checkHeight, final Collection<Block> validBlocks) {

        final int
            ox = origin.getX(),
            oy = origin.getY(),
            oz = origin.getZ();
        final int height = Math.abs(checkHeight);
        final boolean invert = checkHeight < 0;
        final MutableBlockPos mpos = new MutableBlockPos();

        if (!checkVerticalBlocks(matchType, world, origin, checkHeight, validBlocks)) { return false; }
        for (int i = 0; i < height; i++) {
            if (!checkAreaBlocks(matchType, world, mpos.setPos(ox, oy + (invert ? -i : i), oz), checkRadius, validBlocks)) { return false; }
        }
        return true;
    }

    /**
     *  A varargs delegate to {@link #checkVolumeMaterials(MatchType, World, BlockPos, int, int, Collection)}
     *
     *  @since 1.0.0
     */
    public static boolean checkVolumeMaterials(final MatchType matchType, final World world, final BlockPos origin, final int checkRadius, final int checkHeight, final Material... validMaterials) {
        return checkVolumeMaterials(matchType, world, origin, checkRadius, checkHeight, Collections.unmodifiableCollection(Arrays.asList(validMaterials)));
    }

    /**
     *  Match {@link Material}s in a volume above or below (inclusive) to an origin position (spiralling outward
     *  (horizontal) from the origin, and outward (vertical)) to see if they match those in the provided collection.
     *
     * @param matchType      The {@link MatchType} of matching to perform.
     * @param world          The {@link World} that the check is taking place in.
     * @param origin         The origin {@link BlockPos} where the check is taking place.
     * @param checkRadius    The distance (in blocks) to check from the origin {@link BlockPos} in the horizontal plane.
     * @param checkHeight    The height distance (in blocks) to check from the origin {@link BlockPos}
     *                       A positive value checks above; A negative value checks below.
     * @param validMaterials A collection of {@link Material}s to check for.
     *
     * @return {@link MatchType#ALL_IGNORE_REPLACEABLE}:
     *              Returns true if ALL checked positions contain a material that is {@link Material#isReplaceable},
     *              OR, that match a block in {@code validMaterials}, otherwise returns false.
     *         {@link MatchType#ALL}:
     *              Returns true if ALL checked positions matches a block in {@code validMaterials}, otherwise returns false.
     *         {@link MatchType#ANY}:
     *              Returns true if ANY checked position matches a block in {@code validMaterials}, otherwise returns false.
     *         {@link MatchType#NONE}:
     *              Returns true if NO checked position matches a block in {@code validMaterials}, otherwise returns false.
     *
     * @since 1.0.0
     */
    public static boolean checkVolumeMaterials(final MatchType matchType, final World world, final BlockPos origin, final int checkRadius, final int checkHeight, final Collection<Material> validMaterials) {

        final int
            ox = origin.getX(),
            oy = origin.getY(),
            oz = origin.getZ();
        final int height = Math.abs(checkHeight);
        final boolean invert = checkHeight < 0;
        final MutableBlockPos mpos = new MutableBlockPos();

        if (!checkVerticalMaterials(matchType, world, origin, checkHeight, validMaterials)) { return false; }
        for (int i = 0; i < height; i++) {
            if (!checkAreaMaterials(matchType, world, mpos.setPos(ox, oy + (invert ? -i : i), oz), checkRadius, validMaterials)) { return false; }
        }
        return true;
    }
}
