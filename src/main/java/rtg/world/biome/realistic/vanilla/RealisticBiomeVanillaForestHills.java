package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFlowersRTG;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.helper.DecoHelper5050;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaForestHills;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaForestHills extends RealisticBiomeVanillaBase {

    public RealisticBiomeVanillaForestHills() {

        super(
                Biomes.forestHills,
                Biomes.river
        );
        this.noLakes = true;
    }

    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
                return terrainHighland(x, y, simplex, cell, river, 10f, 68f, 45f, 10f);
            }
        };
    }
    
    @Override
    protected SurfaceBase initSurface() {
    	return new SurfaceVanillaForestHills(this, false, 0f, 1.5f, 60f, 65f, 1.5f, 0.15f);
    }

    @Override
    protected void initDecos()
    {
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

        // Add some fallen trees of the oak and spruce variety (50/50 distribution).
        DecoFallenTree decoFallenOak = new DecoFallenTree();
        decoFallenOak.logCondition = DecoFallenTree.LogCondition.RANDOM_CHANCE;
        decoFallenOak.logConditionChance = 8;
        decoFallenOak.maxY = 100;
        decoFallenOak.logBlock = Blocks.log;
        decoFallenOak.logMeta = (byte)0;
        decoFallenOak.leavesBlock = Blocks.leaves;
        decoFallenOak.leavesMeta = (byte)-1;
        decoFallenOak.minSize = 3;
        decoFallenOak.maxSize = 6;

        DecoFallenTree decoFallenSpruce = new DecoFallenTree();
        decoFallenSpruce.logCondition = DecoFallenTree.LogCondition.RANDOM_CHANCE;
        decoFallenSpruce.logConditionChance = 8;
        decoFallenSpruce.maxY = 100;
        decoFallenSpruce.logBlock = Blocks.log;
        decoFallenSpruce.logMeta = (byte)1;
        decoFallenSpruce.leavesBlock = Blocks.leaves;
        decoFallenSpruce.leavesMeta = (byte)-1;
        decoFallenSpruce.minSize = 3;
        decoFallenSpruce.maxSize = 6;

        DecoHelper5050 decoFallenTree = new DecoHelper5050(decoFallenOak, decoFallenSpruce);
        this.addDeco(decoFallenTree);

        // Shrubs to fill in the blanks.
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 110;
        decoShrub.strengthFactor = 3f;
        this.addDeco(decoShrub);

        // Only 1-block tall flowers so we can see the trees better.
        DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
        decoFlowersRTG.flowers = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        decoFlowersRTG.maxY = 128;
        decoFlowersRTG.strengthFactor = 8f;
        this.addDeco(decoFlowersRTG);

        // Grass filler.
        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 128;
        decoGrass.strengthFactor = 12f;
        this.addDeco(decoGrass);
    }
    
    @Override
    protected void initProperties()
    {
        config.addBlock(config.MIX_BLOCK).setDefault(Blocks.dirt.getStateFromMeta(2));
    	config.addBlock(config.BEACH_BLOCK).setDefault(Blocks.sand.getDefaultState());
    }
}
