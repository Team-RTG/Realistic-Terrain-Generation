package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaForest;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoFlowersRTG;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeDistribution;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.biome.deco.helper.DecoHelper5050;
import rtg.world.gen.surface.vanilla.SurfaceVanillaForest;
import rtg.world.gen.terrain.vanilla.TerrainVanillaForest;

public class RealisticBiomeVanillaForest extends RealisticBiomeVanillaBase
{
    
    public static Block topBlock = BiomeGenBase.forest.topBlock;
    public static Block fillerBlock = BiomeGenBase.forest.fillerBlock;
    
    public RealisticBiomeVanillaForest(BiomeConfig config)
    {
    
        super(config, 
            BiomeGenBase.forest,
            BiomeGenBase.river,
            new TerrainVanillaForest(),
            new SurfaceVanillaForest(config, Blocks.grass, Blocks.dirt, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.dirt, (byte)2, 0.10f)
        );

		/**
		 * ##################################################
		 * # DECORATIONS (ORDER MATTERS)
		 * ##################################################
		 */
		
        // Trees first.
		DecoTree bigPines = new DecoTree();
		bigPines.strengthNoiseFactorForLoops = true;
		bigPines.treeType = TreeType.BIG_PINES;
		bigPines.treeDistribution = TreeDistribution.VENUS;
		bigPines.treeCondition = TreeCondition.ALWAYS_GENERATE;
		bigPines.maxY = 140;
		this.addDeco(bigPines);
		
		// More trees.
		DecoTree smallPinesTreesForest = new DecoTree();
		smallPinesTreesForest.strengthFactorForLoops = 3f;
		smallPinesTreesForest.treeType = TreeType.SMALL_PINES_TREES_FORESTS;
		smallPinesTreesForest.treeCondition = TreeCondition.ALWAYS_GENERATE;
		smallPinesTreesForest.maxY = 120;
		this.addDeco(smallPinesTreesForest);
		
		// Add some fallen trees of the oak and spruce variety (50/50 distribution).
        if (this.config.getPropertyById(BiomeConfigVanillaForest.decorationLogsId).valueBoolean) {

            DecoFallenTree decoFallenOak = new DecoFallenTree();
            decoFallenOak.logCondition = LogCondition.RANDOM_CHANCE;
            decoFallenOak.logConditionChance = 8;
            decoFallenOak.maxY = 100;
            decoFallenOak.logBlock = Blocks.log;
            decoFallenOak.logMeta = (byte)0;
            decoFallenOak.leavesBlock = Blocks.leaves;
            decoFallenOak.leavesMeta = (byte)-1;
            decoFallenOak.minSize = 3;
            decoFallenOak.maxSize = 6;
			
            DecoFallenTree decoFallenSpruce = new DecoFallenTree();
            decoFallenSpruce.logCondition = LogCondition.RANDOM_CHANCE;
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
        }
        
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
}
