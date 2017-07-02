package rtg.world.biome.realistic.atg;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.util.CliffCalculator;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.world.IRTGWorld;
import rtg.api.world.deco.DecoBaseBiomeDecorations;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;
import rtg.api.world.terrain.heighteffect.GroundEffect;

public class RealisticBiomeATGTundra extends RealisticBiomeATGBase {

    public static Biome river = Biomes.RIVER;

    public RealisticBiomeATGTundra(Biome biome) {

        super(biome, river);
    }

    @Override
    public void initConfig() {
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_BLOCK).set("");
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_BLOCK_META).set(0);
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_2_BLOCK).set("");
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_2_BLOCK_META).set(0);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainATGTundra();
    }

    public class TerrainATGTundra extends TerrainBase {

        private GroundEffect groundEffect = new GroundEffect(4f);

        public TerrainATGTundra() {

        }

        @Override
        public float generateNoise(IRTGWorld rtgWorld, int x, int y, float border, float river) {
            //return terrainPlains(x, y, simplex, river, 160f, 10f, 60f, 100f, 66f);
            return riverized(65f + groundEffect.added(rtgWorld, x, y), river);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceATGTundra(
            config, this.baseBiome.topBlock, this.baseBiome.fillerBlock,
            BlockUtil.getStateDirt(1), 12f, -0.6f,
            Blocks.GRAVEL.getDefaultState(), 0.6f
        );
    }

    @Override
    public Biome beachBiome() {
        return this.beachBiome(Biomes.BEACH);
    }

    public class SurfaceATGTundra extends SurfaceBase {

        private IBlockState mixBlock;
        private float mixWidth;
        private float mixHeight;

        private IBlockState mix2Block;
        private float mix2Height;

        public SurfaceATGTundra(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState mixBlock, float mixWidth, float mixHeight, IBlockState mix2Block, float mix2Height) {

            super(config, top, filler);

            this.mixBlock = this.getConfigBlock(config.SURFACE_MIX_BLOCK.get(), config.SURFACE_MIX_BLOCK_META.get(), mixBlock);
            this.mixWidth = mixWidth;
            this.mixHeight = mixHeight;

            this.mix2Block = this.getConfigBlock(config.SURFACE_MIX_2_BLOCK.get(), config.SURFACE_MIX_2_BLOCK_META.get(), mix2Block);
            this.mix2Height = mix2Height;
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, IRTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            OpenSimplexNoise simplex = rtgWorld.simplex();
            float c = CliffCalculator.calc(x, z, noise);
            float mixNoise;
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

                            mixNoise = simplex.noise2(i / mixWidth, j / mixWidth);
                            //Logger.info("" + mixNoise);

                            if (mixNoise > mix2Height)
                            {
                                primer.setBlockState(x, k, z, mix2Block);
                            }
                            else if (mixNoise > mixHeight)
                            {
                                primer.setBlockState(x, k, z, mixBlock);
                            }
                            else {
                                primer.setBlockState(x, k, z, topBlock);
                            }
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
