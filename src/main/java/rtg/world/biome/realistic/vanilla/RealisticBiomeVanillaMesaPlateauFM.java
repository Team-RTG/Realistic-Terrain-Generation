package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt.DirtType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenTrees;
import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.util.PlateauUtil;
import rtg.api.util.WorldUtil.Terrain;
import rtg.api.world.RTGWorld;
import rtg.api.world.deco.DecoTree;
import rtg.api.world.deco.collection.DecoCollectionDesertRiver;
import rtg.api.world.deco.collection.DecoCollectionMesa;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;
import rtg.api.world.biome.RealisticBiomeBase;


public class RealisticBiomeVanillaMesaPlateauFM extends RealisticBiomeBase {

    public static Biome biome = Biomes.MUTATED_MESA_ROCK;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaMesaPlateauFM() {

        super(biome);
    }

    @Override
    public void initConfig() {
        this.getConfig().SURFACE_WATER_LAKE_MULT.set(0.1f);
        this.getConfig().ALLOW_SCENIC_LAKES.set(false);
        this.getConfig().addProperty(this.getConfig().ALLOW_CACTUS).set(true);
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_BLOCK).set("");
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_2_BLOCK).set("");
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_3_BLOCK).set("");
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_4_BLOCK).set("");
        this.getConfig().addProperty(this.getConfig().ALLOW_PLATEAU_MODIFICATIONS).set(false);
        this.getConfig().addProperty(this.getConfig().PLATEAU_GRADIENT_BLOCK_LIST).set(PlateauUtil.getMesaPlateauBlocks());
    }

    @Override
    public TerrainBase initTerrain() {

        return new RealisticBiomeVanillaMesaPlateau.TerrainRTGMesaPlateau(67);
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaMesaPlateauFM(getConfig(), biome.topBlock, BlockUtil.getStateClay(EnumDyeColor.ORANGE), 0, 0.2f, 0.6f);
    }

    @Override
    public void rReplace(ChunkPrimer primer, int i, int j, int x, int y, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

        this.rReplaceWithRiver(primer, i, j, x, y, depth, rtgWorld, noise, river, base);
    }

    @Override
    public void initDecos() {

        this.addDecoCollection(new DecoCollectionDesertRiver(this.getConfig()));
        this.addDecoCollection(new DecoCollectionMesa(this.getConfig()));

        DecoTree decoTree = new DecoTree(new WorldGenTrees(false));
        decoTree.setLoops(16);
        decoTree.setTreeType(DecoTree.TreeType.WORLDGEN);
        decoTree.setTreeCondition(DecoTree.TreeCondition.X_DIVIDED_BY_STRENGTH);
        decoTree.setDistribution(new DecoTree.Distribution(24f, 1f, 0f));
        decoTree.setTreeConditionChance(1);
        decoTree.setTreeConditionFloat(4f);
        decoTree.setTreeConditionNoise(0f);
        decoTree.setMinY(74);
        addDeco(decoTree);
    }

    public class SurfaceVanillaMesaPlateauFM extends SurfaceBase {

        private int grassRaise = 0;
        private IBlockState mixBlock;
        private IBlockState mix2Block;
        private IBlockState mix3Block;
        private IBlockState mix4Block;
        private float mix3Height;
        private float mix4Height;

        public SurfaceVanillaMesaPlateauFM(BiomeConfig config, IBlockState top, IBlockState fill, int grassHeight, float mix3Height, float mix4Height) {

            super(config, top, fill);
            grassRaise = grassHeight;
            this.mix3Height = mix3Height;
            this.mix4Height = mix4Height;

            this.mixBlock = this.getConfigBlock(config.SURFACE_MIX_BLOCK.get(), BlockUtil.getStateClay(EnumDyeColor.ORANGE));
            this.mix2Block = this.getConfigBlock(config.SURFACE_MIX_2_BLOCK.get(), Blocks.RED_SANDSTONE.getDefaultState());
            this.mix3Block = this.getConfigBlock(config.SURFACE_MIX_3_BLOCK.get(), BlockUtil.getStateDirt(DirtType.COARSE_DIRT));
            this.mix4Block = this.getConfigBlock(config.SURFACE_MIX_4_BLOCK.get(), Blocks.GRASS.getDefaultState());
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            float c = Terrain.calcCliff(x, z, noise);
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
                        primer.setBlockState(x, k, z, PlateauUtil.getPlateauBand(rtgWorld, RealisticBiomeVanillaMesaPlateauFM.this, i, k, j));
                    }
                    else {

                        float mixNoise = rtgWorld.simplexInstance(0).noise2f(i / 12f, j / 12f);

                        if (k > 74 + grassRaise) {
                            if (depth == 0) {
                                if (mixNoise > mix4Height) {
                                    primer.setBlockState(x, k, z, mix4Block);
                                }
                                else if (mixNoise > mix3Height) {
                                    primer.setBlockState(x, k, z, mix3Block);
                                }
                                else {
                                    if (rand.nextInt(5) == 0) {
                                        primer.setBlockState(x, k, z, mix2Block);
                                    }
                                    else {
                                        primer.setBlockState(x, k, z, topBlock);
                                    }
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
