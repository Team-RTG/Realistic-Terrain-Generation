package teamrtg.rtg.world.biome.surface;

import com.shinoow.abyssalcraft.api.block.ACBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import teamrtg.rtg.api.mods.Mods;
import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.api.biome.RealisticBiomeBase;

import java.util.Random;

public class SurfaceBase {
    protected RealisticBiomeBase biome;

    public SurfaceBase(RealisticBiomeBase biome) {
        this.biome = biome;
    }

    public void paintSurface(ChunkPrimer primer, int i, int j, int x, int z, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, float border, BiomeGenBase[] base) {
    }

    public void paintSurface(ChunkPrimer primer, int i, int j, int x, int z, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {
    }

    public static IBlockState getShadowStoneBlock() {
        return Mods.RTG.config.SHADOW_STONE_BLOCK.get();
    }

    public static IBlockState getShadowDesertBlock() {
        return Mods.RTG.config.SHADOW_DESERT_BLOCK.get();
    }

    public static IBlockState hcStone() {
        if (Mods.ABYSSALCRAFT.isPresent()) {
            return ACBlocks.darkstone.getDefaultState();
        } else {

            return Blocks.STONE.getDefaultState();
        }
    }

    public static IBlockState hcCobble() {
        if (Mods.ABYSSALCRAFT.isPresent()) {

            return ACBlocks.darkstone_cobblestone.getDefaultState();
        } else {
            return Blocks.COBBLESTONE.getDefaultState();
        }
    }
}
