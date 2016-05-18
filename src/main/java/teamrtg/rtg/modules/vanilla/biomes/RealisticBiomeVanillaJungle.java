package teamrtg.rtg.modules.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.api.config.BiomeConfig;
import teamrtg.rtg.api.tools.deco.*;
import teamrtg.rtg.api.tools.deco.DecoFallenTree.LogCondition;
import teamrtg.rtg.api.tools.deco.DecoTree.TreeCondition;
import teamrtg.rtg.api.tools.deco.DecoTree.TreeType;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.CliffSelector;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.vanilla.RealisticBiomeVanillaBase;

public class RealisticBiomeVanillaJungle extends RealisticBiomeVanillaBase {

    public RealisticBiomeVanillaJungle() {
        super(
                Biomes.JUNGLE,
            Biomes.RIVER
        );
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {
                return terrainFlatLakes(x, y, rtgWorld.simplex, river, 3f, 66f);
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
        // Blend of the WorldGenMegaJungle collection and some tall RTG Mangrove trees.
        DecoTree megaJungleMangrove = new DecoTree();
        megaJungleMangrove.loops = 3;
        megaJungleMangrove.treeType = TreeType.MEGA_JUNGLE_MANGROVE;
        megaJungleMangrove.treeCondition = TreeCondition.RANDOM_CHANCE;
        megaJungleMangrove.treeConditionChance = 2;
        megaJungleMangrove.maxY = 160;
        this.addDeco(megaJungleMangrove);

        // Add some palm trees for variety.
        DecoTree palmCustom = new DecoTree();
        palmCustom.loops = 1;
        palmCustom.treeType = TreeType.PALM_CUSTOM;
        palmCustom.treeCondition = TreeCondition.RANDOM_CHANCE;
        palmCustom.treeConditionChance = 3;
        palmCustom.maxY = 160;
        palmCustom.minSize = 10;
        palmCustom.maxSize = 20;
        this.addDeco(palmCustom);

        // Another pass of the WorldGenMegaJungle collection for extra jungleness.
        DecoTree megaJungle = new DecoTree();
        megaJungle.loops = 3;
        megaJungle.treeType = TreeType.MEGA_JUNGLE;
        megaJungle.treeCondition = TreeCondition.RANDOM_CHANCE;
        megaJungle.treeConditionChance = 3;
        megaJungle.maxY = 160;
        megaJungle.minSize = 20;
        megaJungle.maxSize = 40;
        this.addDeco(megaJungle);

        // Jungle logs.
        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.loops = 1;
        decoFallenTree.distribution.noiseDivisor = 100f;
        decoFallenTree.distribution.noiseFactor = 5f;
        decoFallenTree.distribution.noiseAddend = 0.8f;
        decoFallenTree.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logConditionChance = 3;
        decoFallenTree.maxY = 120;
        decoFallenTree.logBlock = Blocks.LOG;
        decoFallenTree.logMeta = (byte) 3;
        decoFallenTree.leavesBlock = Blocks.LEAVES;
        decoFallenTree.leavesMeta = (byte) -1;
        decoFallenTree.minSize = 4;
        decoFallenTree.maxSize = 9;
        this.addDeco(decoFallenTree);

        // At this point, let's hand over some of the decoration to the base biome, but only about 85% of the time.
        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.notEqualsZeroChance = 6;
        decoBaseBiomeDecorations.loops = 1;
        this.addDeco(decoBaseBiomeDecorations);

        // A combo-deal of lilypads and vines. (This could probably be pulled out into individual decos.)
        DecoJungleLilypadVines decoJungleLilypadVines = new DecoJungleLilypadVines();
        this.addDeco(decoJungleLilypadVines);

        // A combo-deal of grass and vines. (This could probably be pulled out into individual decos.)
        DecoJungleGrassVines decoJungleGrassVines = new DecoJungleGrassVines();
        this.addDeco(decoJungleLilypadVines);

        // Flowers.
        DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
        decoFlowersRTG.flowers = new int[] {5}; // Only orange tulips fit in with the colour scheme.
        decoFlowersRTG.chance = 4;
        decoFlowersRTG.maxY = 120;
        decoFlowersRTG.strengthFactor = 2f;
        this.addDeco(decoFlowersRTG);

        // Tall cacti on red sand - matches the colour scheme nicely.
        DecoJungleCacti decoJungleCacti = new DecoJungleCacti();
        decoJungleCacti.strengthFactor = 8f;
        decoJungleCacti.maxY = 120;
        decoJungleCacti.sandOnly = false;
        decoJungleCacti.extraHeight = 7;
        decoJungleCacti.sandMeta = (byte) 1;
        this.addDeco(decoJungleCacti);

        // Mossy boulders for the green.
        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.MOSSY_COBBLESTONE;
        decoBoulder.chance = 16;
        decoBoulder.maxY = 95;
        decoBoulder.strengthFactor = 2f;
        this.addDeco(decoBoulder);

        // Grass filler.
        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 128;
        decoGrass.strengthFactor = 12f;
        this.addDeco(decoGrass);
    }

    @Override
    public void initConfig() {
        config.addBlock(config.MIX_BLOCK_TOP).setDefault(Blocks.DIRT.getStateFromMeta(2));
        config.addBlock(config.BEACH_BLOCK).setDefault(Blocks.SAND.getDefaultState());
        this.config.SCATTERED_FEATURE.setDefault(BiomeConfig.FeatureType.JUNGLE_TEMPLE.name());
        this.config.WATER_POND_CHANCE.setDefault(3);
    }
}
