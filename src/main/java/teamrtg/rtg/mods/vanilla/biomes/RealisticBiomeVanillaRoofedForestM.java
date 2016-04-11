package teamrtg.rtg.mods.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.world.gen.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.world.gen.deco.DecoBoulder;
import teamrtg.rtg.world.gen.deco.DecoDeadBush;
import teamrtg.rtg.world.gen.deco.DecoFallenTree;
import teamrtg.rtg.world.gen.deco.DecoGrass;
import teamrtg.rtg.world.gen.deco.DecoGrassDoubleTallgrass;
import teamrtg.rtg.world.gen.deco.DecoMushrooms;
import teamrtg.rtg.world.gen.deco.DecoShrub;
import teamrtg.rtg.world.gen.deco.DecoTree;
import teamrtg.rtg.api.biome.RealisticBiomeBase;
import teamrtg.rtg.world.biome.surface.SurfaceBase;
import teamrtg.rtg.mods.vanilla.surfaces.SurfaceVanillaRoofedForestM;
import teamrtg.rtg.world.biome.terrain.TerrainBase;

public class RealisticBiomeVanillaRoofedForestM extends RealisticBiomeVanillaBase {
    public static BiomeGenBase standardBiome = Biomes.ROOFED_FOREST;
    public static BiomeGenBase mutationBiome = BiomeGenBase.getBiome(RealisticBiomeBase.getIdForBiome(standardBiome) + MUTATION_ADDEND);

    public RealisticBiomeVanillaRoofedForestM() {

        super(
                mutationBiome,
                Biomes.RIVER
        );
        this.noLakes = true;
    }

    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
                return terrainGrasslandMountains(x, y, simplex, cell, river, 4f, 50f, 68f);
            }
        };
    }

    @Override
    protected SurfaceBase initSurface() {
        return new SurfaceVanillaRoofedForestM(this);
    }

    @Override
    protected void initDecos() {
        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.MOSSY_COBBLESTONE;
        decoBoulder.chance = 20;
        decoBoulder.maxY = 80;
        decoBoulder.strengthFactor = 2f;
        this.addDeco(decoBoulder);

        DecoTree decoTrees = new DecoTree();
        decoTrees.strengthFactorForLoops = 24f;
        decoTrees.distribution.noiseDivisor = 80f;
        decoTrees.distribution.noiseFactor = 60f;
        decoTrees.distribution.noiseAddend = -15f;
        decoTrees.treeType = DecoTree.TreeType.MANGROVE;
        decoTrees.treeCondition = DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoTrees.treeConditionNoise = 0f;
        decoTrees.treeConditionChance = 1;
        decoTrees.maxY = 120;
        this.addDeco(decoTrees);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.ALWAYS_GENERATE;
        decoFallenTree.logConditionChance = 1;
        decoFallenTree.loops = 4;
        decoFallenTree.maxY = 100;
        decoFallenTree.logBlock = Blocks.LOG2;
        decoFallenTree.logMeta = (byte) 1;
        decoFallenTree.leavesBlock = Blocks.LEAVES2;
        decoFallenTree.leavesMeta = (byte) -1;
        decoFallenTree.minSize = 4;
        decoFallenTree.maxSize = 9;
        this.addDeco(decoFallenTree);

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 110;
        decoShrub.strengthFactor = 1f;
        this.addDeco(decoShrub);

        DecoGrassDoubleTallgrass decoGrassDoubleTallgrass = new DecoGrassDoubleTallgrass();
        decoGrassDoubleTallgrass.maxY = 128;
        decoGrassDoubleTallgrass.strengthFactor = 8f;
        decoGrassDoubleTallgrass.doubleGrassChance = 6;
        this.addDeco(decoGrassDoubleTallgrass);

        DecoDeadBush decoDeadBush = new DecoDeadBush();
        decoDeadBush.maxY = 128;
        decoDeadBush.chance = 16;
        decoDeadBush.strengthFactor = 1f;
        this.addDeco(decoDeadBush);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 128;
        decoGrass.strengthFactor = 4f;
        decoGrass.chance = 2;
        this.addDeco(decoGrass);

        DecoGrass decoFern = new DecoGrass();
        decoFern.maxY = 128;
        decoFern.strengthFactor = 4f;
        decoFern.chance = 2;
        decoFern.meta = 2;
        this.addDeco(decoFern);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);

        DecoMushrooms decoMushrooms = new DecoMushrooms();
        decoMushrooms.maxY = 90;
        decoMushrooms.randomType = teamrtg.rtg.world.gen.deco.DecoMushrooms.RandomType.ALWAYS_GENERATE;
        this.addDeco(decoMushrooms);
    }

    @Override
    protected void initProperties() {

    }
}
