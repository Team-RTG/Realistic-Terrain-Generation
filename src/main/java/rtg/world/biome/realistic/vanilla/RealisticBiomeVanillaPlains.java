package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.RTGAPI;
import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.util.CliffCalculator;
import rtg.api.world.RTGWorld;
import rtg.api.world.deco.*;
import rtg.api.world.deco.helper.DecoHelperThisOrThat;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGQuercusRobur;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;
import rtg.api.world.terrain.heighteffect.GroundEffect;

import java.util.Random;

public class RealisticBiomeVanillaPlains extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.PLAINS;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaPlains() {

        super(biome, river);
    }

    @Override
    public void initConfig() {

        this.getConfig().addProperty(this.getConfig().ALLOW_WHEAT).set(true);
        this.getConfig().addProperty(this.getConfig().WHEAT_CHANCE).set(50);
        this.getConfig().addProperty(this.getConfig().WHEAT_MIN_Y).set(RTGAPI.config().SEA_LEVEL_MODIFIER.get());
        this.getConfig().addProperty(this.getConfig().WHEAT_MAX_Y).set(255);
    }
    //Added Sea LVL Customization Support SDF_Brambe
    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaPlains();
    }

    public class TerrainVanillaPlains extends TerrainBase {

        private GroundEffect groundEffect = new GroundEffect(4f);

        public TerrainVanillaPlains() {

        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {
            //return terrainPlains(x, y, simplex, river, 160f, 10f, 60f, 200f, 66f);
            return riverized((RTGAPI.config().SEA_LEVEL_MODIFIER.get()) + 2f + groundEffect.added(rtgWorld, x, y), river);
            //Added Sea LVL Customization Suppourt SDF_Bramble
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaPlains(config, biome.topBlock, biome.fillerBlock);
    }

    public class SurfaceVanillaPlains extends SurfaceBase {

        public SurfaceVanillaPlains(BiomeConfig config, IBlockState top, IBlockState filler) {

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

        //Sparse wheat
        DecoCrop decoCropWheat = new DecoCrop();
        decoCropWheat.setSize(8);
        decoCropWheat.setDensity(5);
        decoCropWheat.setChance(this.getConfig().WHEAT_CHANCE.get());
        decoCropWheat.setType(3);
        decoCropWheat.setWater(false);
        decoCropWheat.setMinY(this.getConfig().WHEAT_MIN_Y.get());
        decoCropWheat.setMaxY(this.getConfig().WHEAT_MAX_Y.get());
        this.addDeco(decoCropWheat, this.getConfig().ALLOW_WHEAT.get());

        // Very sparse shrubs.
        DecoShrub decoShrubOak = new DecoShrub();
        decoShrubOak.setLogBlock(Blocks.LOG.getDefaultState());
        decoShrubOak.setLeavesBlock(Blocks.LEAVES.getDefaultState());
        decoShrubOak.setMaxY(110);
        decoShrubOak.setLoops(1);
        decoShrubOak.setChance(36);
        this.addDeco(decoShrubOak);

        // The occasional flower.
        DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
        decoFlowersRTG.setFlowers(new int[]{0, 2, 3, 4, 5, 6, 7, 8, 9});
        decoFlowersRTG.setMaxY(128);
        decoFlowersRTG.setStrengthFactor(2f);
        this.addDeco(decoFlowersRTG);

        // Lots of grass, but not as much as vanilla.
        DecoGrass decoGrass = new DecoGrass();
        decoGrass.setMinY((RTGAPI.config().SEA_LEVEL_MODIFIER.get()) - 3);
        decoGrass.setLoops(6);
        this.addDeco(decoGrass);
//Added Sea LVL Customization Support SDF_Bramble
        // Very rare fat oak/birch trees.

        TreeRTG roburTree1 = new TreeRTGQuercusRobur();
        roburTree1.setLogBlock(Blocks.LOG.getDefaultState());
        roburTree1.setLeavesBlock(Blocks.LEAVES.getDefaultState());
        roburTree1.setMinTrunkSize(3);
        roburTree1.setMaxTrunkSize(5);
        roburTree1.setMinCrownSize(7);
        roburTree1.setMaxCrownSize(9);
        this.addTree(roburTree1);

        DecoTree oakTrees = new DecoTree(roburTree1);
        oakTrees.setTreeType(DecoTree.TreeType.RTG_TREE);
        oakTrees.setTreeCondition(DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE);
        oakTrees.setDistribution(new DecoTree.Distribution(100f, 6f, 0.8f));
        oakTrees.setTreeConditionNoise(0.4f);
        oakTrees.setTreeConditionChance(48);

        TreeRTG roburTree2 = new TreeRTGQuercusRobur();
        roburTree2.setLogBlock(BlockUtil.getStateLog(2));
        roburTree2.setLeavesBlock(BlockUtil.getStateLeaf(2));
        roburTree2.setMinTrunkSize(3);
        roburTree2.setMaxTrunkSize(5);
        roburTree2.setMinCrownSize(7);
        roburTree2.setMaxCrownSize(9);
        this.addTree(roburTree2);

        DecoTree birchTrees = new DecoTree(roburTree2);
        birchTrees.setTreeType(DecoTree.TreeType.RTG_TREE);
        birchTrees.setTreeCondition(DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE);
        birchTrees.setDistribution(new DecoTree.Distribution(100f, 6f, 0.8f));
        birchTrees.setTreeConditionNoise(0.4f);
        birchTrees.setTreeConditionChance(48);

        this.addDeco(new DecoHelperThisOrThat(4, DecoHelperThisOrThat.ChanceType.NOT_EQUALS_ZERO, oakTrees, birchTrees));

        // Vanilla trees look awful in this biome, so let's make sure they don't generate.
        //DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        //this.addDeco(decoBaseBiomeDecorations);
    }
}
