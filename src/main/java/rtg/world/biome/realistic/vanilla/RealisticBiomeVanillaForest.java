package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaForest;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoFallenTree5050;
import rtg.world.biome.deco.DecoFlowersRTG;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeDistribution;
import rtg.world.biome.deco.DecoTree.TreeType;
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

		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		decoBaseBiomeDecorations.allowed = false;
		this.addDeco(decoBaseBiomeDecorations);
		
		DecoTree bigPines = new DecoTree();
		bigPines.strengthNoiseFactorForLoops = true;
		bigPines.treeType = TreeType.BIG_PINES;
		bigPines.treeDistribution = TreeDistribution.VENUS;
		bigPines.treeCondition = TreeCondition.ALWAYS_GENERATE;
		bigPines.maxY = 140;
		this.addDeco(bigPines);
		
		DecoTree smallPinesTreesForest = new DecoTree();
		smallPinesTreesForest.strengthFactorForLoops = 3f;
		smallPinesTreesForest.treeType = TreeType.SMALL_PINES_TREES_FORESTS;
		smallPinesTreesForest.treeCondition = TreeCondition.ALWAYS_GENERATE;
		smallPinesTreesForest.maxY = 120;
		this.addDeco(smallPinesTreesForest);
		
        if (this.config.getPropertyById(BiomeConfigVanillaForest.decorationLogsId).valueBoolean) {

            DecoFallenTree5050 decoFallenTree5050 = new DecoFallenTree5050();
			decoFallenTree5050.logCondition = LogCondition.RANDOM_CHANCE;
			decoFallenTree5050.logConditionChance = 8; 
			decoFallenTree5050.maxY = 100;
			decoFallenTree5050.logBlock = Blocks.log;
			decoFallenTree5050.logMeta = (byte)0;
			decoFallenTree5050.leavesBlock = Blocks.leaves;
			decoFallenTree5050.leavesMeta = (byte)-1;
			decoFallenTree5050.minSize = 3;
			decoFallenTree5050.maxSize = 6;
			decoFallenTree5050.logBlock2 = Blocks.log;
			decoFallenTree5050.logMeta2 = (byte)1;
			decoFallenTree5050.leavesBlock2 = Blocks.leaves;
			decoFallenTree5050.leavesMeta2 = (byte)-1;
			decoFallenTree5050.minSize2 = 3;
			decoFallenTree5050.maxSize2 = 6;
			this.addDeco(decoFallenTree5050);
        }
        
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 110;
        decoShrub.strengthFactor = 3f;
		this.addDeco(decoShrub);
        
		DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
		decoFlowersRTG.flowers = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
		decoFlowersRTG.maxY = 128;
		decoFlowersRTG.strengthFactor = 8f;
        this.addDeco(decoFlowersRTG);
        
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.strengthFactor = 12f;
        this.addDeco(decoGrass);
    }
}
