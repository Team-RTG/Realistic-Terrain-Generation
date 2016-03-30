package rtg.world.gen.surface;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.BiomeConfigProperty;
import rtg.api.biome.ConfigProperty;
import rtg.config.rtg.ConfigRTG;
import rtg.util.ModPresenceTester;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;

import java.util.Random;

public class SurfaceBase {
    private final static ModPresenceTester undergroundBiomesMod = new ModPresenceTester("UndergroundBiomes");
    private final static ModPresenceTester abyssalCraftMod = new ModPresenceTester("abyssalcraft");
    protected IBlockState topBlock;
    protected IBlockState fillerBlock;
    protected BiomeConfig biomeConfig;

    public SurfaceBase(BiomeConfig config, Block top, byte topByte, Block fill, byte fillByte) {
        this(config, top.getStateFromMeta(topByte), fill.getStateFromMeta(fillByte));
    }

    public SurfaceBase(BiomeConfig config, IBlockState top, IBlockState fill) {
        if (config == null) throw new RuntimeException("Biome config in SurfaceBase is NULL.");

        biomeConfig = config;

        topBlock = top;
        fillerBlock = fill;

        this.assignUserConfigs(config, top, fill);
    }

    private void assignUserConfigs(BiomeConfig config, IBlockState top, IBlockState fill) {
        IBlockState userTopBlock = config._block(BiomeConfigProperty.SURFACE_TOP_BLOCK);
        try {
            topBlock = userTopBlock;
        } catch (Exception e) {
            topBlock = top;
        }

        IBlockState userFillerBlock = config._block(BiomeConfigProperty.SURFACE_FILLER_BLOCK);
        try {
            fillerBlock = userFillerBlock;
        } catch (Exception e) {
            fillerBlock = fill;
        }
    }

    public SurfaceBase(BiomeConfig config, Block top, Block fill) {
        this(config, top.getDefaultState(), fill.getDefaultState());
    }

    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {
    }

    protected IBlockState getShadowStoneBlock(World world, int i, int j, int x, int y, int k) {
        if ((undergroundBiomesMod.present()) && ConfigRTG.enableUBCStoneShadowing) {

            return Blocks.stone.getDefaultState();
        } else {

            return Block.getBlockFromName(ConfigRTG.shadowStoneBlockId).getStateFromMeta(ConfigRTG.shadowStoneBlockByte);
        }
    }

    protected IBlockState getShadowDesertBlock(World world, int i, int j, int x, int y, int k) {
        if ((undergroundBiomesMod.present()) && ConfigRTG.enableUBCDesertShadowing) {

            return Blocks.stone.getDefaultState();
        } else {

            return Block.getBlockFromName(ConfigRTG.shadowDesertBlockId).getStateFromMeta(ConfigRTG.shadowDesertBlockByte);
        }
    }

    protected IBlockState hcStone(World world, int i, int j, int x, int y, int k) {
//        if (abyssalCraftMod.present()) {
//            return ACBlocks.darkstone.getDefaultState();
//        } else {

        return Blocks.stone.getDefaultState();
//        }
    }

    protected IBlockState hcCobble(World world, int worldX, int worldZ, int chunkX, int chunkZ, int worldY) {
//        if (abyssalCraftMod.present()) {
//
//            return ACBlocks.darkstone_cobblestone.getDefaultState();
//        } else {
        return Blocks.cobblestone.getDefaultState();
//        }
    }

    public IBlockState getTopBlock() {
        return this.topBlock;
    }

    public IBlockState getFillerBlock() {
        return this.fillerBlock;
    }

    protected IBlockState getConfigBlock(BiomeConfig config, ConfigProperty.IPropertyEnum id, IBlockState blockDefault) {
        IBlockState blockReturn;

        try {
            blockReturn = config._block(id);
        } catch (Exception e) {
            blockReturn = blockDefault;
        }

        return blockReturn;
    }
}
