package rtg.api.world.surface;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraftforge.common.MinecraftForge;
import rtg.api.RTGAPI;
import rtg.api.config.BiomeConfig;
import rtg.api.config.RTGConfig;
import rtg.api.event.CustomizeBlockEvent;
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
    protected RTGConfig rtgConfig = RTGAPI.config();
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

    protected IBlockState getShadowStoneBlock(RTGWorld rtgWorld, int i, int j, int x, int y, int k) {

        CustomizeBlockEvent event = new CustomizeBlockEvent(rtgWorld.world(), new BlockPos(i, k, j), shadowStoneBlock);
        MinecraftForge.TERRAIN_GEN_BUS.post(event);
        return event.getBlockState();
    }

    protected IBlockState getShadowDesertBlock(RTGWorld rtgWorld, int i, int j, int x, int y, int k) {

        CustomizeBlockEvent event = new CustomizeBlockEvent(rtgWorld.world(), new BlockPos(i, k, j), shadowDesertBlock);
        MinecraftForge.TERRAIN_GEN_BUS.post(event);
        return event.getBlockState();
    }

    protected IBlockState hcStone(RTGWorld rtgWorld, int i, int j, int x, int y, int k) {

        return cliffStoneBlock;
    }

    protected IBlockState hcCobble(RTGWorld rtgWorld, int worldX, int worldZ, int chunkX, int chunkZ, int worldY) {

        CustomizeBlockEvent event = new CustomizeBlockEvent(rtgWorld.world(), new BlockPos(worldX, worldY, worldZ), cliffCobbleBlock);
        MinecraftForge.TERRAIN_GEN_BUS.post(event);
        return event.getBlockState();
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
        shadowStoneBlock = getConfigBlock(rtgConfig.SHADOW_STONE_BLOCK_ID.get(), BlockUtil.getStateClay(EnumDyeColor.CYAN));
        shadowDesertBlock = getConfigBlock(rtgConfig.SHADOW_DESERT_BLOCK_ID.get(), BlockUtil.getStateClay(EnumDyeColor.WHITE));
    }
}