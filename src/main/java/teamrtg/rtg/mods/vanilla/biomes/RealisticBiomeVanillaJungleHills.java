package teamrtg.rtg.mods.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.mods.vanilla.surfaces.SurfaceVanillaJungleHills;
import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.world.biome.surface.SurfaceBase;
import teamrtg.rtg.world.biome.terrain.TerrainBase;
import teamrtg.rtg.world.gen.ChunkProviderRTG;
import teamrtg.rtg.world.gen.deco.*;
import teamrtg.rtg.world.gen.deco.DecoFallenTree.LogCondition;
import teamrtg.rtg.world.gen.deco.DecoTree.TreeCondition;
import teamrtg.rtg.world.gen.deco.DecoTree.TreeType;
import teamrtg.rtg.world.gen.structure.MapGenScatteredFeatureRTG;

public class RealisticBiomeVanillaJungleHills extends RealisticBiomeVanillaBase {

    public RealisticBiomeVanillaJungleHills(ChunkProviderRTG chunkProvider) {

        super(
                Biomes.JUNGLE_HILLS,
                Biomes.RIVER,
                chunkProvider
        );
        this.noLakes = true;
    }

    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
                return terrainHighland(x, y, simplex, cell, river, 10f, 68f, 55f, 10f);
            }
        };
    }

    @Override
    protected SurfaceBase initSurface() {
        return new SurfaceVanillaJungleHills(this, false, 1f, 1.5f, 60f, 65f, 1.5f);
    }

    @Override
    protected void initDecos() {
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
    protected void initProperties() {
        config.addBlock(config.BEACH_BLOCK).setDefault(Blocks.SAND.getDefaultState());
        this.config.SCATTERED_FEATURE.setDefault(MapGenScatteredFeatureRTG.Type.JUNGLE_TEMPLE.name());
        this.config.SURFACE_WATER_LAKE_CHANCE.setDefault(3);
    }
}
