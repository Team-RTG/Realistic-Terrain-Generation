package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaBirchForestHills;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoFlowersRTG;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.gen.surface.vanilla.SurfaceVanillaBirchForestHills;
import rtg.world.gen.terrain.vanilla.TerrainVanillaBirchForestHills;

public class RealisticBiomeVanillaBirchForestHills extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.birchForestHills.topBlock;
    public static Block fillerBlock = BiomeGenBase.birchForestHills.fillerBlock;
	
	public RealisticBiomeVanillaBirchForestHills(BiomeConfig config)
	{
		super(config, 
			BiomeGenBase.birchForestHills,
			BiomeGenBase.river,
			new TerrainVanillaBirchForestHills(),
			new SurfaceVanillaBirchForestHills(config, Blocks.grass, Blocks.dirt, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.dirt, (byte)2, 0.10f)
		);
        this.noLakes=true;
        
		/**
		 * ##################################################
		 * # DECORATIONS (ORDER MATTERS)
		 * ##################################################
		 */
        
		DecoTree smallBirch = new DecoTree();
		smallBirch.strengthNoiseFactorForLoops = true;
		smallBirch.treeType = TreeType.SMALL_BIRCH;
		smallBirch.distribution.noiseDivisor = 80f;
		smallBirch.distribution.noiseFactor = 60f;
		smallBirch.distribution.noiseAddend = -15f;
		smallBirch.treeCondition = TreeCondition.ALWAYS_GENERATE;
		smallBirch.maxY = 120;
		this.addDeco(smallBirch);
        
		DecoTree birchTreesForest = new DecoTree();
		birchTreesForest.strengthFactorForLoops = 3f;
		birchTreesForest.treeType = TreeType.BIRCH_TREES_FOREST;
		birchTreesForest.treeCondition = TreeCondition.ALWAYS_GENERATE;
		birchTreesForest.maxY = 100;
		this.addDeco(birchTreesForest);
        
        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.logCondition = LogCondition.RANDOM_CHANCE;
        decoFallenTree.logConditionChance = 8;
        decoFallenTree.maxY = 100;
        decoFallenTree.logBlock = Blocks.log;
        decoFallenTree.logMeta = (byte)2;
        decoFallenTree.leavesBlock = Blocks.leaves;
        decoFallenTree.leavesMeta = (byte)-1;
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;        
		this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigVanillaBirchForestHills.decorationLogsId));
        
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 120;
        decoShrub.strengthFactor = 3f;
		this.addDeco(decoShrub);

		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		decoBaseBiomeDecorations.notEqualsZeroChance = 3;
		this.addDeco(decoBaseBiomeDecorations);
		
		DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
		decoFlowersRTG.flowers = new int[] {3, 6};
		decoFlowersRTG.maxY = 128;
		decoFlowersRTG.strengthFactor = 12f;
        this.addDeco(decoFlowersRTG);
        
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.strengthFactor = 20f;
        this.addDeco(decoGrass);
	}
}
