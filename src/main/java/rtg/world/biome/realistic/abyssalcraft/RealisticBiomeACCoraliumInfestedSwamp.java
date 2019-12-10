package rtg.world.biome.realistic.abyssalcraft;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.config.BiomeConfig;
import rtg.api.world.RTGWorld;
import rtg.api.world.biome.RealisticBiomeBase;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;


public class RealisticBiomeACCoraliumInfestedSwamp extends RealisticBiomeBase {

    public RealisticBiomeACCoraliumInfestedSwamp(final Biome biome) { super(biome); }

    @Override
    public void initConfig() {
        this.getConfig().SURFACE_CLIFF_STONE_BLOCK.set("abyssalcraft:stone");
        this.getConfig().SURFACE_CLIFF_COBBLE_BLOCK.set("abyssalcraft:cobblestone");
    }

    @Override
    public void initDecos() {}

    @Override
    public TerrainBase initTerrain() {

        return new TerrainACCoraliumInfestedSwamp();
    }

    public static class TerrainACCoraliumInfestedSwamp extends TerrainBase {

        public TerrainACCoraliumInfestedSwamp() {

        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainMarsh(x, y, rtgWorld, 61.5f, river);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceACCoraliumInfestedSwamp(getConfig(), baseBiome().topBlock, baseBiome().fillerBlock);
    }

    public static class SurfaceACCoraliumInfestedSwamp extends SurfaceBase {

        public SurfaceACCoraliumInfestedSwamp(BiomeConfig config, IBlockState top, IBlockState filler) {

            super(config, top, filler);
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            float c = TerrainBase.calcCliff(x, z, noise);
            boolean cliff = c > 1.4f;

            for (int k = 255; k > -1; k--) {
                Block b = primer.getBlockState(x, k, z).getBlock();
                if (b == Blocks.AIR) {
                    depth = -1;
                }
                else if (b == Blocks.STONE) {
                    depth++;

                    if (cliff && k > 64) {
                        if (depth > -1 && depth < 2) {
                            if (rand.nextInt(3) == 0) {

                                primer.setBlockState(x, k, z, hcCobble());
                            }
                            else {

                                primer.setBlockState(x, k, z, hcStone());
                            }
                        }
                        else if (depth < 10) {
                            primer.setBlockState(x, k, z, hcStone());
                        }
                    }
                    else {
                        if (depth == 0 && k > 61) {
                            primer.setBlockState(x, k, z, topBlock);
                        }
                        else if (depth < 4) {
                            primer.setBlockState(x, k, z, fillerBlock);
                        }
                    }
                }
            }
        }
    }
}
