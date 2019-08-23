package rtg.world.biome.realistic.biomesyougo;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.util.PlateauUtil;
import rtg.api.util.WorldUtil;
import rtg.api.util.noise.ISimplexData2D;
import rtg.api.util.noise.SimplexData2D;
import rtg.api.util.noise.SimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.api.world.deco.DecoWorldGen;
import rtg.api.world.deco.collection.DecoCollectionDesertRiver;
import rtg.api.world.deco.collection.DecoCollectionMesa;
import rtg.api.world.gen.feature.WorldGenPond;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaMesaBryce;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


public class RealisticBiomeBYGDeadSea extends RealisticBiomeBYGBase {

    private static IBlockState bygMixBlock = BlockUtil.getBlockStateFromCfgString("byg:sodalite", BlockUtil.getStateStone(BlockStone.EnumType.GRANITE));

    public RealisticBiomeBYGDeadSea(Biome biome) {

        super(biome, RiverType.NORMAL, BeachType.NORMAL);
    }

    @Override
    public Biome preferredBeach() {
        return baseBiome();
    }

    @Override
    public double waterLakeMult() {
        return 4d;
    }

    @Override
    public void initConfig() {
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_BLOCK).set("");
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_2_BLOCK).set("");
        this.getConfig().addProperty(this.getConfig().PLATEAU_GRADIENT_BLOCK_LIST).set(Collections.unmodifiableCollection(Arrays.asList(
                "minecraft:stone",
                "minecraft:cobblestone",
                "minecraft:stone",
                "minecraft:stone",
                "minecraft:cobblestone",
                "byg:sodalite"
        )).toArray(new String[0]));
    }

    @Override
    public void initDecos() {
        WorldGenPond pond = new WorldGenPond(Blocks.WATER.getDefaultState());
        DecoWorldGen decoPond = new DecoWorldGen(pond);
        decoPond.setChance(1);
        decoPond.setMinY(62);
        decoPond.setMaxY(66);
        decoPond.setEventType(DecorateBiomeEvent.Decorate.EventType.LAKE_WATER);
        this.addDeco(decoPond);
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainRTGMesaBryce();
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaMesaBryce(getConfig(), baseBiome().topBlock, baseBiome().topBlock, 0);
    }

    public class TerrainRTGMesaBryce extends TerrainBase {
        private static final float height = 20f;
        TerrainRTGMesaBryce() { }
        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {
            SimplexNoise simplex = rtgWorld.simplexInstance(0);
            float sn = simplex.noise2f(x / 2f, y / 2f) * 0.5f + 0.5f;
            sn += simplex.noise2f(x, y) * 0.2 + 0.2;
            sn += simplex.noise2f(x / 4f, y / 4f) * 4f + 4f;
            sn += simplex.noise2f(x / 8f, y / 8f) * 2f + 2f;
            float n = height / sn * 2;
            n += simplex.noise2f(x / 64f, y / 64f) * 4f;
            n = (sn < 6) ? n : 0f;
            return riverized(getTerrainBase() + n, river);
        }

    }

    public class SurfaceVanillaMesaBryce extends SurfaceBase {

        private int grassRaise = 0;
        private IBlockState mixBlock;
        private IBlockState mix2Block;

        public SurfaceVanillaMesaBryce(BiomeConfig config, IBlockState top, IBlockState fill, int grassHeight) {

            super(config, top, fill);
            grassRaise = grassHeight;

            this.mixBlock = this.getConfigBlock(config.SURFACE_MIX_BLOCK.get(), top);
            this.mix2Block = this.getConfigBlock(config.SURFACE_MIX_2_BLOCK.get(), top);
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            float c = WorldUtil.Terrain.calcCliff(x, z, noise);
            boolean cliff = c > 1.3f;
            Block b;

            for (int k = 255; k > -1; k--) {
                b = primer.getBlockState(x, k, z).getBlock();
                if (b == Blocks.AIR) {
                    depth = -1;
                }
                else if (b == Blocks.STONE) {
                    depth++;

                    if (cliff) {
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

                        if (k > 74 + grassRaise) {
                            if (depth == 0) {
                                if (rand.nextInt(5) == 0) {
                                    primer.setBlockState(x, k, z, mix2Block);
                                }
                                else {
                                    primer.setBlockState(x, k, z, topBlock);
                                }
                            }
                            else if (depth < 4) {
                                primer.setBlockState(x, k, z, fillerBlock);
                            }
                        }
                        else if (depth == 0 && k > 61) {
                            int r = (int) ((k - (62 + grassRaise)) / 2f);
                            if (rand.nextInt(r + 2) == 0) {
                                primer.setBlockState(x, k, z, mixBlock);
                            }
                            else if (rand.nextInt((int) (r / 2f) + 2) == 0) {
                                primer.setBlockState(x, k, z, mix2Block);
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
}
