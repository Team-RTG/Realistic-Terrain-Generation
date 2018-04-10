package rtg.world.biome.realistic.floricraft;

import net.minecraft.block.Block;
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
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeFLORIRoseLand extends RealisticBiomeFLORIBase {

    public static Biome river = Biomes.RIVER;

    RealisticBiomeFLORIRoseLand(Biome biome) { super(biome, river); }

    @Override public void initConfig() {}

    @Override public TerrainBase initTerrain() { return new TerrainFLORIRoseLand(); }

    public static final class TerrainFLORIRoseLand extends TerrainBase {

        private GroundEffect groundEffect = new GroundEffect(6f);

        private TerrainFLORIRoseLand() { }

        @Override public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) { return riverized(65f + groundEffect.added(rtgWorld, x, y), river); }
    }

    @Override public SurfaceBase initSurface() { return new SurfaceFLORIRoseLand(config, this.baseBiome.topBlock, this.baseBiome.fillerBlock); }

    public static final class SurfaceFLORIRoseLand extends SurfaceBase {

        private SurfaceFLORIRoseLand(BiomeConfig config, IBlockState top, IBlockState filler) { super(config, top, filler); }

        @Override public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            for (int k = 255; k > -1; k--) {

                Block b = primer.getBlockState(x, k, z).getBlock();
                if (b == Blocks.AIR) { depth = -1; }

                else if (b == Blocks.STONE) {

                    depth++;
                    if (CliffCalculator.calc(x, z, noise) > 1.4f) {

                        if (depth > -1 && depth < 2) {
                            if (rtgWorld.rand.nextInt(3) == 0) { primer.setBlockState(x, k, z, hcCobble(rtgWorld, i, j, x, z, k)); }
                            else { primer.setBlockState(x, k, z, hcStone(rtgWorld, i, j, x, z, k)); }
                        }

                        else if (depth < 10) { primer.setBlockState(x, k, z, hcStone(rtgWorld, i, j, x, z, k)); }
                    }
                    else {

                        if (depth == 0 && k > 61) { primer.setBlockState(x, k, z, topBlock); }
                        else if (depth < 4) { primer.setBlockState(x, k, z, fillerBlock); }
                    }
                }
            }
        }
    }

    @Override public void initDecos() { this.addDeco(new DecoBaseBiomeDecorations()); }
}
