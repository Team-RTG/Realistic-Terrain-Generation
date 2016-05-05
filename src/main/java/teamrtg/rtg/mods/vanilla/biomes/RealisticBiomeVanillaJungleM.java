package teamrtg.rtg.mods.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import teamrtg.rtg.api.util.BiomeUtils;
import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.world.biome.surface.part.CliffSelector;
import teamrtg.rtg.world.biome.surface.part.DepthSelector;
import teamrtg.rtg.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.world.biome.terrain.TerrainBase;
import teamrtg.rtg.world.gen.ChunkProviderRTG;
import teamrtg.rtg.world.gen.deco.*;
import teamrtg.rtg.world.gen.deco.DecoFallenTree.LogCondition;
import teamrtg.rtg.world.gen.deco.DecoTree.TreeCondition;
import teamrtg.rtg.world.gen.deco.DecoTree.TreeType;
import teamrtg.rtg.world.gen.structure.MapGenScatteredFeatureRTG;

public class RealisticBiomeVanillaJungleM extends RealisticBiomeVanillaBase {
    public static BiomeGenBase standardBiome = Biomes.JUNGLE;
    public static BiomeGenBase mutationBiome = BiomeGenBase.getBiome(BiomeUtils.getIdForBiome(standardBiome) + MUTATION_ADDEND);

    public RealisticBiomeVanillaJungleM(ChunkProviderRTG chunkProvider) {
        super(
                mutationBiome,
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
                return terrainGrasslandMountains(x, y, simplex, cell, river, 4f, 80f, 68f);
            }
        };
    }

    @Override
    protected SurfacePart initSurface() {
        SurfacePart surface = new SurfacePart();
        surface.add(new CliffSelector(1.4f)
            .add(new DepthSelector(0, 1)
                .add(PARTS.STONE_OR_COBBLE)))
            .add(PARTS.STONE);
        surface.add(PARTS.surfaceGeneric());
        return surface;
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
        this.config.SCATTERED_FEATURE.setDefault(MapGenScatteredFeatureRTG.FeatureType.JUNGLE_TEMPLE.name());
        this.config.WATER_POND_CHANCE.setDefault(3);
    }
}
