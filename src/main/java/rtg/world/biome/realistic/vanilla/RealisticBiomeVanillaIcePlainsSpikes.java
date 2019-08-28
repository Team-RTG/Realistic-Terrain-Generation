package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.config.BiomeConfig;
import rtg.api.world.RTGWorld;
import rtg.api.world.deco.collection.DecoCollectionIceTrees;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;
import rtg.api.world.biome.RealisticBiomeBase;


public class RealisticBiomeVanillaIcePlainsSpikes extends RealisticBiomeBase {

    public static Biome biome = Biomes.MUTATED_ICE_FLATS;
    public static Biome river = Biomes.FROZEN_RIVER;

    public RealisticBiomeVanillaIcePlainsSpikes() {

        super(biome, RiverType.FROZEN, BeachType.COLD);
    }

    @Override
    public void initConfig() {
        this.getConfig().ALLOW_SCENIC_LAKES.set(false);
        this.getConfig().addProperty(this.getConfig().ALLOW_ICE_TREES).set(true);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaIcePlainsSpikes();
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaIcePlainsSpikes(getConfig(), biome.topBlock, biome.fillerBlock, biome.topBlock, biome.topBlock);
    }

    @Override
    public void initDecos() {

        if (this.getConfig().ALLOW_ICE_TREES.get()) {
            this.addDecoCollection(new DecoCollectionIceTrees(this.getConfig()));
        }
    }

    public static class TerrainVanillaIcePlainsSpikes extends TerrainBase {

        public TerrainVanillaIcePlainsSpikes() {

        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainPlains(x, y, rtgWorld, river, 160f, 10f, 60f, 200f, 65f);
        }
    }

    public static class SurfaceVanillaIcePlainsSpikes extends SurfaceBase {

        private IBlockState cliffBlock1;
        private IBlockState cliffBlock2;

        public SurfaceVanillaIcePlainsSpikes(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState cliff1, IBlockState cliff2) {

            super(config, top, filler);

            cliffBlock1 = cliff1;
            cliffBlock2 = cliff2;
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
}
