package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoLargeFernDoubleTallgrass;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaBirchForestM;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaBirchForestM extends RealisticBiomeVanillaBase {

    public static BiomeGenBase standardBiome = Biomes.birchForest;
    public static BiomeGenBase mutationBiome = BiomeGenBase.getBiome(RealisticBiomeBase.getIdForBiome(standardBiome) + MUTATION_ADDEND);

    public RealisticBiomeVanillaBirchForestM() {

        super(
                mutationBiome,
                Biomes.river

        );
        this.noLakes = true;
    }

    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
                return terrainPlains(x, y, simplex, river, 160f, 10f, 60f, 80f, 65f);
            }
        };
    }
    
    @Override
    protected SurfaceBase initSurface() {
        return new SurfaceVanillaBirchForestM(this);
    }

    @Override
    protected void initDecos()
    {
        DecoTree superTallBirch = new DecoTree();
        superTallBirch.strengthFactorForLoops = 16f;
        superTallBirch.strengthNoiseFactorForLoops = true;
        superTallBirch.treeType = TreeType.SUPER_TALL_BIRCH;
        superTallBirch.distribution.noiseDivisor = 80f;
        superTallBirch.distribution.noiseFactor = 60f;
        superTallBirch.distribution.noiseAddend = -15f;
        superTallBirch.treeCondition = TreeCondition.ALWAYS_GENERATE;
        superTallBirch.maxY = 100;
        this.addDeco(superTallBirch);

        DecoLargeFernDoubleTallgrass decoDoublePlants = new DecoLargeFernDoubleTallgrass();
        decoDoublePlants.maxY = 128;
        decoDoublePlants.strengthFactor = 8f;
        this.addDeco(decoDoublePlants);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 128;
        decoGrass.strengthFactor = 24f;
        this.addDeco(decoGrass);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.logCondition = LogCondition.RANDOM_CHANCE;
        decoFallenTree.logConditionChance = 20;
        decoFallenTree.maxY = 100;
        decoFallenTree.logBlock = Blocks.log;
        decoFallenTree.logMeta = (byte)2;
        decoFallenTree.leavesBlock = Blocks.leaves;
        decoFallenTree.leavesMeta = (byte)-1;
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;
        this.addDeco(decoFallenTree);

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 110;
        decoShrub.strengthFactor = 2f;
        this.addDeco(decoShrub);
    }
    
    @Override
    protected void initProperties()
    {

    }
}
