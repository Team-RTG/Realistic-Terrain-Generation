package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.config.BiomeConfig;
import rtg.api.util.WorldUtil;
import rtg.api.util.noise.SimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.api.world.deco.DecoBaseBiomeDecorations;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;
import rtg.api.world.terrain.heighteffect.BumpyHillsEffect;
import rtg.api.world.terrain.heighteffect.JitterEffect;
import rtg.world.biome.realistic.RealisticBiomeBase;


public class RealisticBiomeBOPHighland extends RealisticBiomeBase {

    public static Biome biome = BOPBiomes.highland.get();

    public RealisticBiomeBOPHighland() {

        super(biome, RiverType.NORMAL, BeachType.NORMAL);
    }

    @Override
    public void initConfig() {
        this.getConfig().ALLOW_RIVERS.set(false);
    }

    @Override
    public boolean generatesEmeralds() {
        return true;
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPHighland();
    }

    public class TerrainBOPHighland extends TerrainBase {

        private float baseHeight = 90f;
        private BumpyHillsEffect onTop = new BumpyHillsEffect();
        private JitterEffect withJitter;

        public TerrainBOPHighland() {

            onTop.hillHeight = 30;
            onTop.hillWavelength = 60;
            onTop.spikeHeight = 20;
            onTop.spikeWavelength = 10;

            withJitter = new JitterEffect();
            withJitter.amplitude = 2;
            withJitter.wavelength = 5;
            withJitter.jittered = onTop;
        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return riverized(baseHeight + withJitter.added(rtgWorld, x, y) + this.groundNoise(x, y, 6, rtgWorld), river);
            //return terrainGrasslandMountains(x, y, simplex, cell, river, 4f, 80f, 68f);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceBOPHighland(getConfig(), biome.topBlock, biome.fillerBlock);
    }

    public class SurfaceBOPHighland extends SurfaceBase {

        public SurfaceBOPHighland(BiomeConfig config, IBlockState top, IBlockState filler) {

            super(config, top, filler);
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            SimplexNoise simplex = rtgWorld.simplexInstance(0);
            float c = WorldUtil.Terrain.calcCliff(x, z, noise);
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

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
