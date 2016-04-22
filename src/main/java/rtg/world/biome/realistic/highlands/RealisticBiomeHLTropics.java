package rtg.world.biome.realistic.highlands;

import highlands.Highlands;
import highlands.api.HighlandsBiomes;
import highlands.api.HighlandsBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.highlands.config.BiomeConfigHLTropics;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoFlowersRTG;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoHLTree;
import rtg.world.biome.deco.DecoJungleGrassVines;
import rtg.world.biome.deco.DecoJungleLilypadVines;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.gen.surface.highlands.SurfaceHLTropics;
import rtg.world.gen.terrain.highlands.TerrainHLTropics;

public class RealisticBiomeHLTropics extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.tropics;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLTropics(BiomeConfig config)
    {
    
        super(config, 
            hlBiome, BiomeGenBase.river,
            new TerrainHLTropics(),
            new SurfaceHLTropics(config, topBlock, fillerBlock)
        );
        
        this.waterSurfaceLakeChance = 3;
        
        DecoHLTree highlandsShrubs = new DecoHLTree();
        highlandsShrubs.logMeta = 3;
        highlandsShrubs.leavesMeta = 0;
		highlandsShrubs.strengthFactorForLoops = 4f;
		highlandsShrubs.treeType = rtg.world.biome.deco.DecoHLTree.TreeType.HIGHLANDS_SHRUB;
		highlandsShrubs.treeCondition = TreeCondition.ALWAYS_GENERATE;
		highlandsShrubs.maxY = 90;
		this.addDeco(highlandsShrubs);
		
        DecoHLTree thinCanopyTrees = new DecoHLTree();
        thinCanopyTrees.thickTrunk = false;
        thinCanopyTrees.minSize = 13;
        thinCanopyTrees.maxSize = 24;
		thinCanopyTrees.strengthFactorForLoops = 2f;
		thinCanopyTrees.treeType = rtg.world.biome.deco.DecoHLTree.TreeType.CANOPY;
		thinCanopyTrees.treeCondition = TreeCondition.RANDOM_CHANCE;
		thinCanopyTrees.treeConditionChance = 3;
		thinCanopyTrees.maxY = 120;
		this.addDeco(thinCanopyTrees);
		
//		DecoHLTree thickCanopyTrees = new DecoHLTree();
//		thickCanopyTrees.thickTrunk = true;
//		thickCanopyTrees.minSize = 13;
//		thickCanopyTrees.maxSize = 24;
//		thickCanopyTrees.loops = 1;
//		thickCanopyTrees.treeType = rtg.world.biome.deco.DecoHLTree.TreeType.CANOPY;
//		thickCanopyTrees.treeCondition = TreeCondition.RANDOM_CHANCE;
//		thickCanopyTrees.treeConditionChance = 4;
//		thickCanopyTrees.maxY = 120;
//		this.addDeco(thickCanopyTrees);
		
		DecoFallenTree decoFallenTree = new DecoFallenTree();
		decoFallenTree.loops = 1;
		decoFallenTree.distribution.noiseDivisor = 100f;
		decoFallenTree.distribution.noiseFactor = 5f;
		decoFallenTree.distribution.noiseAddend = 0.8f;
		decoFallenTree.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		decoFallenTree.logConditionNoise = 0f;
		decoFallenTree.logConditionChance = 8;
		decoFallenTree.maxY = 90;
		decoFallenTree.logBlock = Highlands.vanillaBlocksFlag ? Blocks.log : HighlandsBlocks.canopyWood;
		decoFallenTree.logMeta = (byte)0;
		decoFallenTree.leavesBlock = Highlands.vanillaBlocksFlag ? Blocks.leaves : HighlandsBlocks.canopyLeaves;
		decoFallenTree.leavesMeta = (byte)-1;
		decoFallenTree.minSize = 4;
		decoFallenTree.maxSize = 9;
		this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigHLTropics.decorationLogsId));

		DecoBoulder decoBoulder = new DecoBoulder();
		decoBoulder.boulderBlock = Blocks.cobblestone;
		decoBoulder.chance = 16;
		decoBoulder.maxY = 95;
		decoBoulder.strengthFactor = 2f;
		this.addDeco(decoBoulder);
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		decoBaseBiomeDecorations.equalsZeroChance = 4;
		this.addDeco(decoBaseBiomeDecorations);
		
		DecoJungleLilypadVines decoJungleLilypadVines = new DecoJungleLilypadVines();
		this.addDeco(decoJungleLilypadVines);
		
		DecoJungleGrassVines decoJungleGrassVines = new DecoJungleGrassVines();
		this.addDeco(decoJungleLilypadVines);
		
		DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
        decoFlowersRTG.flowers = new int[]{2, 3, 8};
        decoFlowersRTG.chance = 4;
        decoFlowersRTG.maxY = 120;
        decoFlowersRTG.strengthFactor = 2f;
        this.addDeco(decoFlowersRTG);
        
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.loops = 16;
		decoGrass.randomGrassBlocks = new Block[]{Blocks.tallgrass, HighlandsBlocks.leafyFern};
		decoGrass.randomGrassMetas = new byte[]{(byte)2, (byte)0};
        this.addDeco(decoGrass);
    }
}
