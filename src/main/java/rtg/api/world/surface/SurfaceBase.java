package rtg.api.world.surface;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.RTGAPI;
import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.world.RTGWorld;


// TODO: [1.12] Fix parameter names in this class, and their origins
public abstract class SurfaceBase {

    public IBlockState shadowStoneBlock;
    public IBlockState shadowDesertBlock;
    protected IBlockState topBlock;
    protected IBlockState fillerBlock;
    protected IBlockState cliffStoneBlock;
    protected IBlockState cliffCobbleBlock;
    protected BiomeConfig biomeConfig;

    public SurfaceBase(BiomeConfig config, Block top, Block fill) {

        this(config, top.getDefaultState(), fill.getDefaultState());
    }

    public SurfaceBase(BiomeConfig config, IBlockState top, IBlockState fill) {

        if (config == null) {
            throw new RuntimeException("Biome config in SurfaceBase is NULL.");
        }

        biomeConfig = config;
        topBlock = top;
        fillerBlock = fill;
        this.initCustomBlocks();
        this.assignUserConfigs(config, top, fill);
    }

    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

    }

    protected IBlockState getShadowStoneBlock() {

        return shadowStoneBlock;
    }

    protected IBlockState getShadowDesertBlock() {

        return shadowDesertBlock;
    }

    protected IBlockState hcStone() {

        return cliffStoneBlock;
    }

    protected IBlockState hcCobble() {

        return cliffCobbleBlock;
    }

    public IBlockState getTopBlock() {

        return this.topBlock;
    }

    public IBlockState getFillerBlock() {

        return this.fillerBlock;
    }

    private void assignUserConfigs(BiomeConfig config, IBlockState top, IBlockState fill) {

        this.topBlock = getConfigBlock(config.SURFACE_TOP_BLOCK.get(), top);
        this.fillerBlock = getConfigBlock(config.SURFACE_FILLER_BLOCK.get(), fill);
    }

    protected IBlockState getConfigBlock(String configString, IBlockState defaultState) {
        return BlockUtil.getBlockStateFromCfgString(configString, defaultState);
    }

    private void initCustomBlocks() {
        cliffStoneBlock = getConfigBlock(biomeConfig.SURFACE_CLIFF_STONE_BLOCK.get(), Blocks.STONE.getDefaultState());
        cliffCobbleBlock = getConfigBlock(biomeConfig.SURFACE_CLIFF_COBBLE_BLOCK.get(), Blocks.COBBLESTONE.getDefaultState());
        shadowStoneBlock = RTGAPI.getShadowStoneBlock();
        shadowDesertBlock = RTGAPI.getShadowDesertBlock();
    }
}