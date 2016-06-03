package teamrtg.rtg.modules.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import teamrtg.rtg.api.tools.deco.*;
import teamrtg.rtg.api.util.BiomeUtils;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.deco.helper.DecoHelper5050;
import teamrtg.rtg.api.world.biome.surface.part.CliffSelector;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.vanilla.RTGBiomeVanilla;

public class RTGBiomeVanillaFlowerForest extends RTGBiomeVanilla {

    public static Biome standardBiome = Biomes.FOREST;
    public static Biome mutationBiome = Biome.getBiome(BiomeUtils.getId(standardBiome) + MUTATION_ADDEND);

    public RTGBiomeVanillaFlowerForest() {

        super(
                mutationBiome,
            Biomes.RIVER
        );
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                return terrainPlains(x, y, rtgWorld.simplex, river, 160f, 10f, 60f, 80f, 65f);
            }
        };
    }

    @Override
    public SurfacePart initSurface() {
        SurfacePart surface = new SurfacePart();
        surface.add(new CliffSelector(1.5f)
            .add(PARTS.selectTopAndFill()
                .add(this.PARTS.SHADOW_STONE)));
        surface.add(new CliffSelector((x, y, z, rtgWorld) -> 1.5f - ((y - 60f) / 65f) + rtgWorld.simplex.noise3(x / 8f, y / 8f, z / 8f) * 0.5f)
            .add(PARTS.selectTop()
                .add(PARTS.STONE_OR_COBBLE)))
            .add(PARTS.selectFill()
                .add(PARTS.STONE));
        surface.add(PARTS.surfaceMix(PARTS.MIX_NOISE));
        surface.add(PARTS.surfaceGeneric());
        return surface;
    }


    @Override
    public void initDecos() {
        // First, let's get a few shrubs in to break things up a bit.
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 110;
        decoShrub.strengthFactor = 4f;
        decoShrub.chance = 3;
        this.addDeco(decoShrub);

        // Flowers are the most aesthetically important feature of this biome, so let's add those next.
        DecoFlowersRTG decoFlowers1 = new DecoFlowersRTG();
        decoFlowers1.flowers = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}; //Only colourful 1-block-tall flowers.
        decoFlowers1.strengthFactor = 12f; // Lots and lots of flowers!
        decoFlowers1.heightType = DecoFlowersRTG.HeightType.GET_HEIGHT_VALUE; // We're only bothered about surface flowers here.
        this.addDeco(decoFlowers1);

        DecoFlowersRTG decoFlowers2 = new DecoFlowersRTG();
        decoFlowers2.flowers = new int[] {10, 11, 14, 15}; //Only 2-block-tall flowers.
        decoFlowers2.strengthFactor = 2f; // Not as many of these.
        decoFlowers2.chance = 3;
        decoFlowers2.heightType = DecoFlowersRTG.HeightType.GET_HEIGHT_VALUE; // We're only bothered about surface flowers here.
        this.addDeco(decoFlowers2);

        // Trees first.
        DecoTree bigPines = new DecoTree();
        bigPines.strengthNoiseFactorForLoops = true;
        bigPines.treeType = DecoTree.TreeType.BIG_PINES;
        bigPines.distribution.noiseDivisor = 80f;
        bigPines.distribution.noiseFactor = 60f;
        bigPines.distribution.noiseAddend = -15f;
        bigPines.treeCondition = DecoTree.TreeCondition.ALWAYS_GENERATE;
        bigPines.maxY = 140;
        this.addDeco(bigPines);

        // More trees.
        DecoTree smallPinesTreesForest = new DecoTree();
        smallPinesTreesForest.strengthFactorForLoops = 3f;
        smallPinesTreesForest.treeType = DecoTree.TreeType.SMALL_PINES_TREES_FORESTS;
        smallPinesTreesForest.treeCondition = DecoTree.TreeCondition.ALWAYS_GENERATE;
        smallPinesTreesForest.maxY = 120;
        this.addDeco(smallPinesTreesForest);

        // Not much free space left, so let's give some space to the base biome.
        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.notEqualsZeroChance = 4;
        this.addDeco(decoBaseBiomeDecorations);

        // Add some fallen trees of the oak and spruce variety (50/50 distribution).
        DecoFallenTree decoFallenOak = new DecoFallenTree();
        decoFallenOak.logCondition = DecoFallenTree.LogCondition.RANDOM_CHANCE;
        decoFallenOak.logConditionChance = 8;
        decoFallenOak.maxY = 100;
        decoFallenOak.logBlock = Blocks.LOG;
        decoFallenOak.logMeta = (byte) 0;
        decoFallenOak.leavesBlock = Blocks.LEAVES;
        decoFallenOak.leavesMeta = (byte) -1;
        decoFallenOak.minSize = 3;
        decoFallenOak.maxSize = 6;
        DecoFallenTree decoFallenSpruce = new DecoFallenTree();
        decoFallenSpruce.logCondition = DecoFallenTree.LogCondition.RANDOM_CHANCE;
        decoFallenSpruce.logConditionChance = 8;
        decoFallenSpruce.maxY = 100;
        decoFallenSpruce.logBlock = Blocks.LOG;
        decoFallenSpruce.logMeta = (byte) 1;
        decoFallenSpruce.leavesBlock = Blocks.LEAVES;
        decoFallenSpruce.leavesMeta = (byte) -1;
        decoFallenSpruce.minSize = 3;
        decoFallenSpruce.maxSize = 6;
        DecoHelper5050 decoFallenTree = new DecoHelper5050(decoFallenOak, decoFallenSpruce);
        this.addDeco(decoFallenTree);

        // Grass filler.
        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 128;
        decoGrass.strengthFactor = 24f;
        this.addDeco(decoGrass);
    }

    @Override
    public void initConfig() {
        config.addBlock(config.MIX_BLOCK_TOP).setDefault(Blocks.GRASS.getDefaultState());
        config.addBlock(config.BEACH_BLOCK).setDefault(Blocks.SAND.getDefaultState());
    }
}
