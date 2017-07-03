package rtg.api.world.surface;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import net.minecraftforge.common.MinecraftForge;

import rtg.api.RTGAPI;
import rtg.api.config.BiomeConfig;
import rtg.api.config.RTGConfig;
import rtg.api.event.SurfaceEvent;
import rtg.api.util.BlockUtil;
import rtg.api.util.PlateauBand;
import rtg.api.world.IRTGWorld;

public abstract class SurfaceBase {

    protected IBlockState topBlock;
    protected IBlockState fillerBlock;
    protected IBlockState cliffStoneBlock;
    protected IBlockState cliffCobbleBlock;
    protected RTGConfig rtgConfig = RTGAPI.config();
    protected BiomeConfig biomeConfig;
    protected PlateauBand plateauBand;

    public IBlockState shadowStoneBlock;
    public IBlockState shadowDesertBlock;

    public SurfaceBase(BiomeConfig config, Block top, byte topByte, Block fill, byte fillByte) {

        this(config, top.getStateFromMeta(topByte), fill.getStateFromMeta(fillByte));
    }

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
        this.initCliffBlocks();
        this.initShadowBlocks();
        this.assignUserConfigs(config, top, fill);
        plateauBand = PlateauBand.getInstance();
    }

    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, IRTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

    }

    protected IBlockState getShadowStoneBlock(IRTGWorld rtgWorld, int i, int j, int x, int y, int k) {

        SurfaceEvent.HardcodedBlock event = new SurfaceEvent.HardcodedBlock(
            rtgWorld, i, j, x, y, k, shadowStoneBlock
        );
        MinecraftForge.TERRAIN_GEN_BUS.post(event);

        return event.getBlock();
    }

    protected IBlockState getShadowDesertBlock(IRTGWorld rtgWorld, int i, int j, int x, int y, int k) {

        SurfaceEvent.HardcodedBlock event = new SurfaceEvent.HardcodedBlock(
            rtgWorld, i, j, x, y, k, shadowDesertBlock
        );
        MinecraftForge.TERRAIN_GEN_BUS.post(event);

        return event.getBlock();
    }

    protected IBlockState hcStone(IRTGWorld rtgWorld, int i, int j, int x, int y, int k) {

        return cliffStoneBlock;
    }

    protected IBlockState hcCobble(IRTGWorld rtgWorld, int worldX, int worldZ, int chunkX, int chunkZ, int worldY) {

        SurfaceEvent.HardcodedBlock event = new SurfaceEvent.HardcodedBlock(
            rtgWorld, worldX, worldZ, chunkX, chunkZ, worldY, cliffCobbleBlock
        );
        MinecraftForge.TERRAIN_GEN_BUS.post(event);

        return event.getBlock();
    }

    public IBlockState getTopBlock() {

        return this.topBlock;
    }

    public IBlockState getFillerBlock() {

        return this.fillerBlock;
    }

    private void assignUserConfigs(BiomeConfig config, IBlockState top, IBlockState fill) {

        topBlock = getConfigBlock(config.SURFACE_TOP_BLOCK.get(), config.SURFACE_TOP_BLOCK_META.get(), top);
        fillerBlock = getConfigBlock(config.SURFACE_FILLER_BLOCK.get(), config.SURFACE_FILLER_BLOCK_META.get(), fill);
    }

    protected IBlockState getConfigBlock(String userBlockId, int userBlockMeta, IBlockState blockDefault) {

        IBlockState blockReturn;

        try {

            Block blockConfig = Block.getBlockFromName(userBlockId);

            if (blockConfig != null) {
                if (userBlockMeta == 0) {
                    blockReturn = blockConfig.getDefaultState();
                }
                else {
                    blockReturn = blockConfig.getStateFromMeta(userBlockMeta);
                }
            }
            else {
                blockReturn = blockDefault;
            }
        }
        catch (Exception e) {
            blockReturn = blockDefault;
        }

        return blockReturn;
    }

    protected void initCliffBlocks() {

        cliffStoneBlock = getConfigBlock(
            biomeConfig.SURFACE_CLIFF_STONE_BLOCK.get(),
            biomeConfig.SURFACE_CLIFF_STONE_BLOCK_META.get(),
            Blocks.STONE.getDefaultState()
        );

        cliffCobbleBlock = getConfigBlock(
            biomeConfig.SURFACE_CLIFF_COBBLE_BLOCK.get(),
            biomeConfig.SURFACE_CLIFF_COBBLE_BLOCK_META.get(),
            Blocks.COBBLESTONE.getDefaultState()
        );
    }

    protected void initShadowBlocks() {

        shadowStoneBlock = getConfigBlock(
            rtgConfig.SHADOW_STONE_BLOCK_ID.get(),
            rtgConfig.SHADOW_STONE_BLOCK_META.get(),
            BlockUtil.getStateClay(9)
        );

        shadowDesertBlock = getConfigBlock(
            rtgConfig.SHADOW_DESERT_BLOCK_ID.get(),
            rtgConfig.SHADOW_DESERT_BLOCK_META.get(),
            BlockUtil.getStateClay(0)
        );
    }
}