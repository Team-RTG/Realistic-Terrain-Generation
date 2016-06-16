package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGPiceaPungens;
import rtg.world.gen.surface.highlands.SurfaceHLWindyIsland;
import rtg.world.gen.terrain.highlands.TerrainHLWindyIsland;

public class RealisticBiomeHLWindyIsland extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.windyIsland;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLWindyIsland(BiomeConfig config)
    {
    
        super(config, 
            hlBiome, BiomeGenBase.river,
            new TerrainHLWindyIsland(),
            new SurfaceHLWindyIsland(config, topBlock, fillerBlock, Blocks.gravel, Blocks.dirt, 60f, -0.14f, 14f, 0.25f)
        );
        noLakes = true;
        noWaterFeatures = true;
        
        // These are Extreme Hills decorations, with plain boulders, smaller trees, no pumpkins and no flowers.
        
		DecoBoulder decoBoulder = new DecoBoulder();
		decoBoulder.boulderBlock = Blocks.cobblestone;
		decoBoulder.chance = 16;
		decoBoulder.maxY = 95;
		decoBoulder.strengthFactor = 3f;
		this.addDeco(decoBoulder);
		
		TreeRTG pungensTree = new TreeRTGPiceaPungens();
		pungensTree.logBlock = Blocks.log;
		pungensTree.logMeta = (byte)1;
		pungensTree.leavesBlock = Blocks.leaves;
		pungensTree.leavesMeta = (byte)1;
		pungensTree.minTrunkSize = 1;
		pungensTree.maxTrunkSize = 2;
		pungensTree.minCrownSize = 3;
		pungensTree.maxCrownSize = 3;
		this.addTree(pungensTree);

		DecoTree smallSpruce = new DecoTree(pungensTree);
		smallSpruce.strengthNoiseFactorForLoops = true;
		smallSpruce.strengthFactorForLoops = 4f;
		smallSpruce.treeType = TreeType.RTG_TREE;
		smallSpruce.distribution.noiseDivisor = 100f;
		smallSpruce.distribution.noiseFactor = 6f;
		smallSpruce.distribution.noiseAddend = 0.8f;
		smallSpruce.treeCondition = TreeCondition.RANDOM_CHANCE;
		smallSpruce.treeConditionChance = 24;
		smallSpruce.maxY = 110;
		smallSpruce.minSize = 0;
		smallSpruce.maxSize = 1;
		this.addDeco(smallSpruce);
        
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 110;
        decoShrub.chance = 10;
        decoShrub.strengthFactor = 3f;
		this.addDeco(decoShrub);
        
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.strengthFactor = 6f;
        this.addDeco(decoGrass);
    }

}
