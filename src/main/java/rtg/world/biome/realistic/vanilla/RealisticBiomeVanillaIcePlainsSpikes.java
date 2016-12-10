package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.world.RTGWorld;
import rtg.api.config.BiomeConfig;
import rtg.api.util.CliffCalculator;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaIcePlainsSpikes extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.MUTATED_ICE_FLATS;
    public static Biome river = Biomes.FROZEN_RIVER;

    public RealisticBiomeVanillaIcePlainsSpikes() {

        super(biome, river);

        this.noLakes = true;
    }

    @Override
    public void initConfig() {}

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaIcePlainsSpikes();
    }

    public class TerrainVanillaIcePlainsSpikes extends TerrainBase {

        public TerrainVanillaIcePlainsSpikes() {

        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainPlains(x, y, rtgWorld.simplex, river, 160f, 10f, 60f, 200f, 65f);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaIcePlainsSpikes(config, biome.topBlock, biome.fillerBlock, biome.topBlock, biome.topBlock);
    }

    public class SurfaceVanillaIcePlainsSpikes extends SurfaceBase {

        private IBlockState cliffBlock1;
        private IBlockState cliffBlock2;

        public SurfaceVanillaIcePlainsSpikes(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState cliff1, IBlockState cliff2) {

            super(config, top, filler);

            cliffBlock1 = cliff1;
            cliffBlock2 = cliff2;
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
                            primer.setBlockState(x, k, z, rand.nextInt(3) == 0 ? cliffBlock2 : cliffBlock1);
                        }
                        else if (depth < 10) {
                            primer.setBlockState(x, k, z, cliffBlock1);
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
