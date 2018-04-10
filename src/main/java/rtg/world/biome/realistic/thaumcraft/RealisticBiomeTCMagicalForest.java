package rtg.world.biome.realistic.thaumcraft;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.config.BiomeConfig;
import rtg.api.util.CliffCalculator;
import rtg.api.world.RTGWorld;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeTCMagicalForest extends RealisticBiomeTCBase
{
    RealisticBiomeTCMagicalForest(Biome biome) { super(biome, Biomes.RIVER); }

    @Override public void initConfig() { }

    @Override public TerrainBase initTerrain() { return new TerrainTCMagicalForest(); }

    public static final class TerrainTCMagicalForest extends TerrainBase {
        private TerrainTCMagicalForest() { }
        @Override public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) { return terrainForest(x, y, rtgWorld.simplex, river, 70f); }
    }

    @Override public SurfaceBase initSurface() { return new SurfaceTCMagicalForest(this.config, this.baseBiome.topBlock, this.baseBiome.fillerBlock); }

    public static final class SurfaceTCMagicalForest extends SurfaceBase {

        private SurfaceTCMagicalForest(BiomeConfig config, IBlockState top, IBlockState filler) { super(config, top, filler); }

        @Override public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            IBlockState bs;
            for(int y = 255; y >= 0; y--) {

                bs = primer.getBlockState(x, y, z);
                if(bs == Blocks.AIR.getDefaultState()) { depth = -1; }

                else if(bs == Blocks.STONE.getDefaultState()) {

                    depth++;
                    if(CliffCalculator.calc(x, z, noise) > 1.4f) {

                        if(depth > -1 && depth < 2) {
                            if (rtgWorld.rand.nextInt(3) == 0) { primer.setBlockState(x, y, z, this.hcCobble(rtgWorld, i, j, x, z, y)); }
                            else { primer.setBlockState(x, y, z, this.hcStone(rtgWorld, i, j, x, z, y)); }
                        }

                        else if (depth < 10) { primer.setBlockState(x, y, z, this.hcStone(rtgWorld, i, j, x, z, y)); }
                    }

                    else {
                        if(depth == 0 && y > 61) { primer.setBlockState(x, y, z, this.topBlock); }
                        else if(depth < 4) { primer.setBlockState(x, y, z, this.fillerBlock); }
                    }
                }
            }
        }
    }

    @Override public void initDecos() {
        this.decos.clear();
        this.addDeco(new DecoBaseBiomeDecorations());
    }
}
