package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.config.BiomeConfig;
import rtg.api.util.CliffCalculator;
import rtg.api.world.RTGWorld;
import rtg.api.world.deco.DecoBaseBiomeDecorations;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;
import rtg.api.world.deco.DecoSingleBiomeDecorations;

public class RealisticBiomeBOPSacredSprings extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.sacred_springs.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPSacredSprings() {

        super(biome, river);
    }

    @Override
    public void initConfig() {

        this.getConfig().ALLOW_RIVERS.set(false);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPSacredSprings(150f, 30f, 68f);
    }

    public class TerrainBOPSacredSprings extends TerrainBase {

        private float width;
        private float strength;
        private float lakeDepth;
        private float lakeWidth;
        private float terrainHeight;

        public TerrainBOPSacredSprings(float mountainWidth, float mountainStrength, float height) {

            width = mountainWidth;
            strength = mountainStrength;
            terrainHeight = height;
        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainLonelyMountain(x, y, rtgWorld.simplex, rtgWorld.cell, river, strength, width, terrainHeight);

        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceBOPSacredSprings(config, biome.topBlock, biome.fillerBlock);
    }

    public class SurfaceBOPSacredSprings extends SurfaceBase {

        public SurfaceBOPSacredSprings(BiomeConfig config, IBlockState top, IBlockState filler) {

            super(config, top, filler);
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand;
            float c = CliffCalculator.calc(x, z, noise);
            boolean cliff = c > 1.4f ? true : false;

            for (int k = 255; k > -1; k--) {
                Block b = primer.getBlockState(x, k, z).getBlock();
                if (b == Blocks.AIR) {
                    depth = -1;
                }
                else if (b == Blocks.STONE) {
                    depth++;

                    if (cliff) {
                        if (depth > -1 && depth < 2) {
                            if (rand.nextInt(3) == 0) {

                                primer.setBlockState(x, k, z, hcCobble(rtgWorld, i, j, x, z, k));
                            }
                            else {

                                primer.setBlockState(x, k, z, hcStone(rtgWorld, i, j, x, z, k));
                            }
                        }
                        else if (depth < 10) {
                            primer.setBlockState(x, k, z, hcStone(rtgWorld, i, j, x, z, k));
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

    @Override
    public void initDecos() {

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoSingleBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public int waterSurfaceLakeChance() {
        return 2;
    }
}
