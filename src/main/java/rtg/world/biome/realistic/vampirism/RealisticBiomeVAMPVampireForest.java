package rtg.world.biome.realistic.vampirism;

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
import rtg.world.gen.terrain.TerrainBase;
import rtg.world.gen.terrain.GroundEffect;


public class RealisticBiomeVAMPVampireForest extends RealisticBiomeVAMPBase {

    public static Biome river = Biomes.RIVER;

    RealisticBiomeVAMPVampireForest(Biome biome) { super(biome, river); }

    @Override public void initConfig() { this.getConfig().addProperty(this.getConfig().ALLOW_LOGS).set(true); }

    @Override public TerrainBase initTerrain() { return new TerrainVAMPVampireForest(); }

    public static final class TerrainVAMPVampireForest extends TerrainBase {

        private GroundEffect groundEffect = new GroundEffect(5f);

        private TerrainVAMPVampireForest() { }

        @Override public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) { return riverized(65f + groundEffect.added(rtgWorld, x, y), river); }
    }

    @Override public SurfaceBase initSurface() { return new SurfaceVAMPVampireForest(config, this.baseBiome.topBlock, this.baseBiome.fillerBlock); }

    public static final class SurfaceVAMPVampireForest extends SurfaceBase {

        private SurfaceVAMPVampireForest(BiomeConfig config, IBlockState top, IBlockState filler) { super(config, top, filler); }

        @Override public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            for (int y = 255; y > -1; y--) {

                Block b = primer.getBlockState(x, y, z).getBlock();
                if (b == Blocks.AIR) { depth = -1; }

                else if (b == Blocks.STONE) {

                    depth++;
                    if (CliffCalculator.calc(x, z, noise) > 1.4f) {

                        if (depth > -1 && depth < 2) {
                            if (rtgWorld.rand.nextInt(3) == 0) { primer.setBlockState(x, y, z, hcCobble(rtgWorld, i, j, x, z, y)); }
                            else { primer.setBlockState(x, y, z, hcStone(rtgWorld, i, j, x, z, y)); }
                        }

                        else if (depth < 10) { primer.setBlockState(x, y, z, hcStone(rtgWorld, i, j, x, z, y)); }
                    }

                    else {
                        if (depth == 0 && y > 61) { primer.setBlockState(x, y, z, topBlock); }
                        else if (depth < 4) { primer.setBlockState(x, y, z, fillerBlock); }
                    }
                }
            }
        }
    }

    @Override public void initDecos() { this.addDeco(new DecoBaseBiomeDecorations()); }
}
