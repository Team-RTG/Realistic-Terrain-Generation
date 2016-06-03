package teamrtg.rtg.modules.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import teamrtg.rtg.api.tools.deco.*;
import teamrtg.rtg.api.tools.deco.DecoFallenTree.LogCondition;
import teamrtg.rtg.api.tools.deco.DecoTree.TreeCondition;
import teamrtg.rtg.api.tools.deco.DecoTree.TreeType;
import teamrtg.rtg.api.util.BiomeUtils;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.surface.part.CliffSelector;
import teamrtg.rtg.api.world.biome.surface.part.DepthSelector;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.vanilla.RTGBiomeVanilla;

public class RTGBiomeVanillaSavannaM extends RTGBiomeVanilla {
    public static Biome standardBiome = Biomes.SAVANNA;
    public static Biome mutationBiome = Biome.getBiome(BiomeUtils.getId(standardBiome) + MUTATION_ADDEND);

    public RTGBiomeVanillaSavannaM() {

        super(
                mutationBiome,
            Biomes.RIVER
        );
        this.noLakes = true;
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                return terrainGrasslandMountains(x, y, rtgWorld.simplex, rtgWorld.cell, river, 4f, 90f, 67f);
            }
        };
    }

    @Override
    public SurfacePart initSurface() {
        SurfacePart surface = new SurfacePart();
        surface.add(new CliffSelector(1.5f)
            .add(PARTS.selectTop()
                .add(PARTS.STONE_OR_COBBLE))
            .add(new DepthSelector(0, 10)
                .add(PARTS.STONE)));
        surface.add(PARTS.surfaceMix(PARTS.MIX_NOISE));
        surface.add(PARTS.surfaceGeneric());
        return surface;
    }

    @Override
    public void initDecos() {
        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.loops = 1;
        decoFallenTree.distribution.noiseDivisor = 100f;
        decoFallenTree.distribution.noiseFactor = 6f;
        decoFallenTree.distribution.noiseAddend = 0.8f;
        decoFallenTree.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logConditionChance = 24;
        decoFallenTree.maxY = 120;
        decoFallenTree.logBlock = Blocks.LOG2;
        decoFallenTree.logMeta = (byte) 0;
        decoFallenTree.leavesBlock = Blocks.LEAVES2;
        decoFallenTree.leavesMeta = (byte) -1;
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;
        this.addDeco(decoFallenTree);

        DecoTree riverTrees = new DecoTree();
        riverTrees.checkRiver = true;
        riverTrees.minRiver = 0.8f;
        riverTrees.strengthNoiseFactorForLoops = false;
        riverTrees.strengthFactorForLoops = 15f;
        riverTrees.treeType = TreeType.SAVANNA_RIVER;
        riverTrees.treeCondition = TreeCondition.ALWAYS_GENERATE;
        riverTrees.maxY = 100;
        this.addDeco(riverTrees);

        DecoReed decoReed = new DecoReed();
        decoReed.checkRiver = true;
        decoReed.minRiver = 0.8f;
        decoReed.maxY = 68;
        decoReed.strengthFactor = 2f;
        this.addDeco(decoReed);

        DecoTree savannaTrees = new DecoTree();
        savannaTrees.strengthFactorForLoops = 3f;
        savannaTrees.treeType = TreeType.SAVANNA;
        savannaTrees.distribution.noiseDivisor = 180f;
        savannaTrees.distribution.noiseFactor = 1f;
        savannaTrees.distribution.noiseAddend = 0f;
        savannaTrees.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        savannaTrees.treeConditionNoise = 0.20f;
        savannaTrees.maxY = 100;
        this.addDeco(savannaTrees);

        DecoTree savannaTrees2 = new DecoTree();
        savannaTrees2.strengthFactorForLoops = 2f;
        savannaTrees2.treeType = TreeType.SAVANNA;
        savannaTrees2.treeCondition = TreeCondition.RANDOM_CHANCE;
        savannaTrees2.treeConditionChance = 3;
        savannaTrees2.maxY = 100;
        this.addDeco(savannaTrees2);

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.COBBLESTONE;
        decoBoulder.chance = 24;
        decoBoulder.maxY = 95;
        this.addDeco(decoBoulder);

        DecoDoubleGrass decoDoubleGrass = new DecoDoubleGrass();
        decoDoubleGrass.maxY = 128;
        decoDoubleGrass.strengthFactor = 3f;
        this.addDeco(decoDoubleGrass);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 128;
        decoGrass.strengthFactor = 10f;
        this.addDeco(decoGrass);
    }

    @Override
    public void initConfig() {

    }
}
