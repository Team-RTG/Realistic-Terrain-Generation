package rtg.world.gen.surface;

import com.shinoow.abyssalcraft.api.block.ACBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.config.BiomeConfig;
import rtg.api.config.BiomeConfigProperty;
import rtg.api.config.ConfigProperty;
import rtg.config.ConfigRTG;
import rtg.util.SupportedMod;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;

import java.util.Random;

public class SurfaceBase {
    protected IBlockState topBlock;
    protected IBlockState fillerBlock;
    protected BiomeConfig biomeConfig;

    public SurfaceBase(BiomeConfig config, Block top, byte topByte, Block fill, byte fillByte) {
        this(config, top.getStateFromMeta(topByte), fill.getStateFromMeta(fillByte));
    }

    public SurfaceBase(BiomeConfig config, IBlockState top, IBlockState fill) {

        this.biomeConfig = config;

        topBlock = top;
        fillerBlock = fill;

        this.assignUserConfigs(top, fill);
    }

    private void assignUserConfigs(IBlockState top, IBlockState fill) {
        IBlockState userTopBlock = biomeConfig
                ._block(BiomeConfigProperty.SURFACE_TOP_BLOCK);
        try {
            topBlock = userTopBlock;
        } catch (Exception e) {
            topBlock = top;
        }

        IBlockState userFillerBlock = biomeConfig._block(BiomeConfigProperty.SURFACE_FILLER_BLOCK);
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

    public static IBlockState getShadowStoneBlock(World world, int i, int j, int x, int y, int k) {
        //if ((SupportedMod.UBC.present()) && ConfigRTG.enableUBCStoneShadowing) {

        //    return Blocks.stone.getDefaultState();
        //} else {

            return Block.getBlockFromName(ConfigRTG.shadowStoneBlockId).getStateFromMeta(ConfigRTG.shadowStoneBlockByte);
        //}
    }

    public static IBlockState getShadowDesertBlock(World world, int i, int j, int x, int y, int k) {
        //if ((undergroundBiomesMod.present()) && ConfigRTG.enableUBCDesertShadowing) {

        //    return Blocks.stone.getDefaultState();
        //} else {

            return Block.getBlockFromName(ConfigRTG.shadowDesertBlockId).getStateFromMeta(ConfigRTG.shadowDesertBlockByte);
        //}
    }

    public static IBlockState hcStone(World world, int i, int j, int x, int y, int k) {
        if (SupportedMod.ABYSSALCRAFT.isPresent()) {
            return ACBlocks.darkstone.getDefaultState();
        } else {

        return Blocks.stone.getDefaultState();
        }
    }

    public static IBlockState hcCobble(World world, int worldX, int worldZ, int chunkX, int chunkZ, int worldY) {
        if (SupportedMod.ABYSSALCRAFT.isPresent()) {

            return ACBlocks.darkstone_cobblestone.getDefaultState();
        } else {
            return Blocks.cobblestone.getDefaultState();
        }
    }

    public IBlockState getTopBlock() {
        return this.topBlock;
    }

    public IBlockState getFillerBlock() {
        return this.fillerBlock;
    }

    protected IBlockState getConfigBlock(ConfigProperty.IPropertyID id, IBlockState blockDefault) {
        IBlockState blockReturn;

        try {
            blockReturn = biomeConfig._block(id);
        } catch (Exception e) {
            blockReturn = blockDefault;
        }

        return blockReturn;
    }
}
