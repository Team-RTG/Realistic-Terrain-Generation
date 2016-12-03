package rtg.world.gen.surface;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.RTG;
import rtg.api.util.ModPresenceTester;
import rtg.api.world.RTGWorld;
import rtg.config.BiomeConfig;
import rtg.util.UBColumnCache;

public abstract class SurfaceBase {

    private final static ModPresenceTester undergroundBiomesMod = new ModPresenceTester("UndergroundBiomes");
    // create UBColumnCache only if UB is present
    private static UBColumnCache ubColumnCache = undergroundBiomesMod.present() ? new UBColumnCache() : null;
    protected IBlockState topBlock;
    protected IBlockState fillerBlock;
    protected BiomeConfig biomeConfig;

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
        this.initShadowBlocks();
        this.assignUserConfigs(config, top, fill);
    }

    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

    }

    protected IBlockState getShadowStoneBlock(RTGWorld rtgWorld, int i, int j, int x, int y, int k) {

        if ((undergroundBiomesMod.present()) && RTG.instance.getConfig().ENABLE_UBC_STONE_SHADOWING.get()) {

            return Blocks.STONE.getDefaultState();
        }
        else {

            return this.shadowStoneBlock;
        }
    }

    protected IBlockState getShadowDesertBlock(RTGWorld rtgWorld, int i, int j, int x, int y, int k) {

        if ((undergroundBiomesMod.present()) && RTG.instance.getConfig().ENABLE_UBC_DESERT_SHADOWING.get()) {

            return Blocks.STONE.getDefaultState();
        }
        else {

            return this.shadowDesertBlock;
        }
    }

    protected IBlockState hcStone(RTGWorld rtgWorld, int i, int j, int x, int y, int k) {

        return Blocks.STONE.getDefaultState();
    }

    protected IBlockState hcCobble(RTGWorld rtgWorld, int worldX, int worldZ, int chunkX, int chunkZ, int worldY) {

        return Blocks.COBBLESTONE.getDefaultState();
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

    protected void initShadowBlocks() {

        try {
            this.shadowStoneBlock = Block.getBlockFromName(RTG.instance.getConfig().SHADOW_STONE_BLOCK_ID.get()).getStateFromMeta(RTG.instance.getConfig().SHADOW_STONE_BLOCK_META.get());
        }
        catch (Exception e) {
            this.shadowStoneBlock = Block.getBlockFromName(RTG.instance.getConfig().DEFAULT_SHADOW_STONE_BLOCK_ID).getStateFromMeta(RTG.instance.getConfig().DEFAULT_SHADOW_STONE_BLOCK_META);
        }

        try {
            this.shadowDesertBlock = Block.getBlockFromName(RTG.instance.getConfig().SHADOW_DESERT_BLOCK_ID.get()).getStateFromMeta(RTG.instance.getConfig().SHADOW_DESERT_BLOCK_META.get());
        }
        catch (Exception e) {
            this.shadowDesertBlock = Block.getBlockFromName(RTG.instance.getConfig().DEFAULT_SHADOW_DESERT_BLOCK_ID).getStateFromMeta(RTG.instance.getConfig().DEFAULT_SHADOW_DESERT_BLOCK_META);
        }
    }
}